package test_scenario_2

import Headers._
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class BranchChangePullRequest extends Simulation {

	val httpProtocol = http
		.baseURL("http://52.88.81.144")
		.inferHtmlResources()

    val headers_0 = Map("Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
                       "Accept-Encoding" -> "identity")

	val headers_3 = Map(
		"Accept" -> "application/json",
		"Origin" -> "http://52.88.81.144",
		"x-requested-with" -> "XMLHttpRequest")

	val headers_4 = Map("Accept" -> "image/png,image/*;q=0.8,*/*;q=0.5")

    val uri1 = "https://godoc.org/github.com/docker/docker"
    val uri2 = "https://img.shields.io/github/release/docker/docker.svg"
    val uri3 = "http://52.88.81.144"

    val user_feeder = csv("users.csv").random
    val branch_feeder = csv("branches.csv").random
    val str = "perf_files/" + getRandomAlphaNumeric(20)

    val auth_token = regex("""<input name="authenticity_token" type="hidden" value="(.*?)" />""").saveAs("auth_token")
    val post_auth_token = regex(""".*js-blob-form js-new-blob-form.*?<input name="authenticity_token" type="hidden" value="(.*?)" /></div>""").saveAs("post_auth_token")
    val pre_auth_token = regex(""".*?js-new-blob-form inline-form.*?<input name="authenticity_token" type="hidden" value="(.*?)" /></div>""").saveAs("pre_auth_token")
    val pull_auth_token = regex(""".*?new-pr-form.*?<input name="authenticity_token" type="hidden" value="(.*?)" /></div>""").saveAs("pull_auth_token")
    val branches_token = regex(""".*?branches.*?<input name="authenticity_token" type="hidden" value="(.*?)" /></div>""").saveAs("branches_token")
    val sha = regex("""<input type="hidden" name="commit" class="js-commit-oid" value="(.*?)">""").saveAs("sha")

	val scn = scenario("BranchChangePullRequest")
		.exec(http("request_0")
			.get("/login")
            .check(auth_token))

        .feed(user_feeder)
        .feed(branch_feeder)

		.pause(1)
		.exec(http("request_1")
			.post("/session")
			.formParam("utf8", "✓")
            .formParam("authenticity_token", session => session("auth_token").validate[String])
			.formParam("login", "user10")
			//.formParam("login", "${userName}")
			.formParam("password", "passworD1"))

        .exec((session: Session) => { // use a simple action
            val fileName = "perf_files/" + getRandomAlphaNumeric(20)
            session.set("fileName_1", fileName)
        })

        .exec((session: Session) => { // use a simple action
            var newBranchName = "branch_" + getRandomAlphaNumeric(20)
            session.set("newBranchName_1", newBranchName)
        })

        .exec(http("GET_DOCKER")
            .get("/first-org/docker")
            .check(branches_token))

        .exec(http("POST_BRANCH")
            .post(uri3 + "/first-org/docker/branches")
            .headers(headers_0)
            .formParam("utf8", "✓")
            .formParam("authenticity_token", session => session("branches_token").validate[String])
           .formParam("name", "${newBranchName_1}")
           .formParam("branch", "master")
           .formParam("path", ""))

		.pause(1)
		.exec(http("GET_DOCKER_TREE_XXX")
			.get("/first-org/docker/tree/${newBranchName_1}")
            .check(pre_auth_token))

		.pause(1)
		.exec(http("request_8")
			.post("/first-org/docker/new/${newBranchName_1}")
            .check(sha)
            .check(post_auth_token)
			.formParam("utf8", "✓")
            .formParam("authenticity_token", session => session("pre_auth_token").validate[String]))

		.pause(2)
		.exec(http("request_9")
			.post("/first-org/docker/create/${newBranchName_1}")
            .check(pull_auth_token)
			.formParam("utf8", "✓")
            .formParam("authenticity_token", session => session("post_auth_token").validate[String])
			.formParam("filename", "${fileName_1}") 
			.formParam("new_filename", "${fileName_1}")
			.formParam("commit", "${sha}")
			.formParam("same_repo", "1")
			.formParam("pr", "")
			.formParam("content_changed", "true")
			.formParam("value", "New File Create ${fileName_1} on ${newBranchName_1} for pull request")
			.formParam("message", "")
			.formParam("placeholder_message", "Create ${fileName_1}")
			.formParam("description", "")
			//.formParam("commit-choice", "direct")
			.formParam("commit-choice", "quick-pull")
			.formParam("target_branch", "${newBranchName_1}")
			.formParam("quick_pull", "${newBranchName_1}"))

		.exec(http("request_11")
			.post("/first-org/docker/pull/create")
			.formParam("utf8", "✓")
            .formParam("authenticity_token", session => session("pull_auth_token").validate[String])
			.formParam("pull_request[title]", "Create ${fileName_1}")
			.formParam("pull_request[body]", "")
			.formParam("quick_pull", "")
			//.formParam("base", "first-org:master}")
			//.formParam("head", "first-org:${newBranchName_1}-1"))
			.formParam("base", "master")
			.formParam("head", "${newBranchName_1}-1"))

        .exec(
              session => {
                 println("THIS -- pre_auth_token is : " + session("pre_auth_token").validate[String])
                 println("THIS -- branches_token is : " + session("branches_token").validate[String])
                 println("THIS -- post_auth_token is : " + session("post_auth_token").validate[String])
                 println("THIS -- sha is : " + session("sha").validate[String])
                 println("THIS -- pull_auth_token is : " + session("pull_auth_token").validate[String])
                 println("THIS -- newBranchName is : " + session("newBranchName_1").validate[String])
                 println("THIS -- fileName_1 is : " + session("fileName_1").validate[String])
                 session
        })

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
