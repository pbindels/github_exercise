package test_scenario_2

import Headers._
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class BranchChangePullRequest extends Simulation {

    //Get cmd args
    val endpoint = System.getProperty("endpoint")
    val users = Integer.getInteger("users",1)
    val rampusers = Integer.getInteger("rampusers",1)
    val ramptime = Integer.getInteger("ramptime",1)
    val pwd = System.getProperty("pass")
    println("Testing endpoint: " + endpoint)
    println("Testing rampusers: " + rampusers)
    println("Testing ramptime: " + ramptime)

	val httpProtocol = http
		.baseURL(endpoint)
		.inferHtmlResources()

    val headers_0 = Map("Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
                       "Accept-Encoding" -> "identity")

	val headers_3 = Map(
		"Accept" -> "application/json",
		"Origin" -> endpoint, 
		"x-requested-with" -> "XMLHttpRequest")

	val headers_4 = Map("Accept" -> "image/png,image/*;q=0.8,*/*;q=0.5")

    val uri1 = "https://godoc.org/github.com/docker/docker"
    val uri2 = "https://img.shields.io/github/release/docker/docker.svg"
    val uri3 = endpoint 

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
		.exec(http("GET_LOGIN")
			.get("/login")
            .check(auth_token))

        .feed(user_feeder)
        .feed(branch_feeder)

		.pause(1)
		.exec(http("POST_SESSION_LOGIN")
			.post("/session")
			.formParam("utf8", "✓")
            .formParam("authenticity_token", session => session("auth_token").validate[String])
			//.formParam("login", "user10")
			.formParam("login", "${userName}")
			.formParam("password", pwd)) 

        .exec((session: Session) => { // use a simple action
            val fileName = "perf_files/" + getRandomAlphaNumeric(20)
            session.set("fileName_1", fileName)
        })

        .exec((session: Session) => { // use a simple action
            var newBranchName = "branch_" + getRandomAlphaNumeric(20)
            session.set("newBranchName_1", newBranchName)
        })

        .exec(http("GET_DOCKER_REPO")
            .get("/first-org/docker")
            .check(branches_token))

        .exec(http("POST_BRANCHES")
            .post(uri3 + "/first-org/docker/branches")
            .headers(headers_0)
            .formParam("utf8", "✓")
            .formParam("authenticity_token", session => session("branches_token").validate[String])
           .formParam("name", "${newBranchName_1}")
           .formParam("branch", "master")
           .formParam("path", ""))

		.pause(1)
		.exec(http("GET_DOCKER_TREE_NEW_BRANCH")
			.get("/first-org/docker/tree/${newBranchName_1}")
            .check(pre_auth_token))

		.pause(1)
		.exec(http("POST_NEW_BRANCH")
			.post("/first-org/docker/new/${newBranchName_1}")
            .check(sha)
            .check(post_auth_token)
			.formParam("utf8", "✓")
            .formParam("authenticity_token", session => session("pre_auth_token").validate[String]))

		.pause(2)
		.exec(http("POST_CREATE_NEW_BRANCH")
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

		.exec(http("POST_PULL_CREATE")
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
                 println("pre_auth_token is : " + session("pre_auth_token").validate[String])
                 println("branches_token is : " + session("branches_token").validate[String])
                 println("post_auth_token is : " + session("post_auth_token").validate[String])
                 println("sha is : " + session("sha").validate[String])
                 println("pull_auth_token is : " + session("pull_auth_token").validate[String])
                 println("newBranchName is : " + session("newBranchName_1").validate[String])
                 println("fileName_1 is : " + session("fileName_1").validate[String])
                 session
        })

    setUp(scn.inject(rampUsers(rampusers) over(ramptime seconds)).protocols(httpProtocol))
    //setUp(scn.inject(rampUsers(5) over(30 seconds)).protocols(httpProtocol))
	//setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
    //setUp(scn.inject(atOnceUsers(5))).protocols(httpProtocol)
    //setUp(scn.inject(splitUsers(100) into(rampUsers(30) over(60 seconds)) separatedBy(atOnceUsers(10))).protocols(httpProtocol))
}
