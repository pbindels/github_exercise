package new_file

import Headers._
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {

	val httpProtocol = http
		.baseURL("http://52.88.81.144")
		.inferHtmlResources()

	val headers_4 = Map("Accept" -> "image/png,image/*;q=0.8,*/*;q=0.5")

	val headers_5 = Map(
		"Accept" -> "application/json",
		"Origin" -> "http://52.88.81.144",
		"x-requested-with" -> "XMLHttpRequest")

	val headers_8 = Map("Accept" -> "text/html")

    val uri1 = "https://godoc.org/github.com/docker/docker"
    val uri2 = "https://img.shields.io/github/release/docker/docker.svg"
    val uri3 = "http://52.88.81.144"

    val auth_token = regex("""<input name="authenticity_token" type="hidden" value="(.*?)" />""").saveAs("auth_token")
    val post_auth_token = regex(""".*?docker\/create\/.*?<input name="authenticity_token" type="hidden" value="(.*?)" /></div>""").saveAs("post_auth_token")
    //val post_auth_token = regex(""".*?docker\/create\/master.*?<input name="authenticity_token" type="hidden" value="(.*?)" /></div>""").saveAs("post_auth_token")
    val pre_auth_token = regex(""".*?js-new-blob-form.*?<input name="authenticity_token" type="hidden" value="(.*?)" /></div>""").saveAs("pre_auth_token")
    //.formParam("authenticity_token", session => session("auth_token").validate[String])
     val sha = regex("""<input type="hidden" name="commit" class="js-commit-oid" value="(.*?)">""").saveAs("sha")

    val user_feeder = csv("users.csv").random
    val branch_feeder = csv("branches.csv").random
    val str = "perf_files/" + getRandomAlphaNumeric(20)

	val scn = scenario("RecordedSimulation")
		.exec(http("GET_LOGIN")
			.get("/login")
            .check(auth_token))

        .feed(user_feeder)
        .feed(branch_feeder)

		.exec(http("POST_SESSION")
			.post("/session")
			.formParam("utf8", "✓")
            .formParam("authenticity_token", session => session("auth_token").validate[String])
			.formParam("login", "${userName}")
			.formParam("password", "passworD1")
			.formParam("return_to", "http://52.88.81.144/"))

		.exec(http("GET_DOCKER")
			.get("/first-org/docker")
            .resources(http("request_3")
            .get(uri3 + "/first-org/docker/issues/counts")
            .headers(headers_5))
            .check(pre_auth_token))

       .exec(
              session => {
                 println("THIS -- pre_auth_token is: " + session("pre_auth_token").validate[String])
                 session
        })
		.pause(1)

		.exec(http("POST_NEW")
			.post("/first-org/docker/new/${branchName}")
			//.post("/first-org/docker/new/master")
            .check(sha)
            .check(post_auth_token)
			.formParam("utf8", "✓")
            .formParam("authenticity_token", session => session("pre_auth_token").validate[String]))
		.pause(1)

		.exec(http("POST_CREATE")
			.post("/first-org/docker/create/${branchName}")
			.formParam("utf8", "✓")
            .formParam("authenticity_token", session => session("post_auth_token").validate[String])
			.formParam("filename", str)
			.formParam("new_filename", str)
			.formParam("commit", session => session("sha").validate[String])
			.formParam("same_repo", "1")
			.formParam("pr", "")
			.formParam("content_changed", "false")
			.formParam("value", "")
			.formParam("message", "")
			.formParam("placeholder_message", "Create: " + str)
			.formParam("description", "")
			.formParam("commit-choice", "direct")
			.formParam("target_branch", "${branchName}")
			.formParam("quick_pull", ""))
       .exec(
              session => {
                println("sha is : " + session("sha").validate[String])
                println("pre_auth_token is : " + session("pre_auth_token").validate[String])
                println("post_auth_token is : " + session("post_auth_token").validate[String])
                println("auth_token is : " + session("auth_token").validate[String])
                session
       })

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
