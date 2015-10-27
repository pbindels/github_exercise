package test_scenario_1 

import Headers._
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class CommitNewFile extends Simulation {

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
    val pre_auth_token = regex(""".*?js-new-blob-form.*?<input name="authenticity_token" type="hidden" value="(.*?)" /></div>""").saveAs("pre_auth_token")
     val sha = regex("""<input type="hidden" name="commit" class="js-commit-oid" value="(.*?)">""").saveAs("sha")

    val user_feeder = csv("users.csv").random
    val branch_feeder = csv("branches.csv").random
    val str = "perf_files/" + getRandomAlphaNumeric(20)
    val testDuration = Integer.getInteger("testDuration", 1)

	val scn = scenario("CommitNewFile")

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

		.pause(1)

		.exec(http("POST_NEW")
			.post("/first-org/docker/new/${branchName}")
			//.post("/first-org/docker/new/master")
            .check(sha)
            .check(post_auth_token)
			.formParam("utf8", "✓")
            .formParam("authenticity_token", session => session("pre_auth_token").validate[String]))
		.pause(1)

         .exec((session: Session) => { // use a simple action
             val fileName = "perf_files/" + getRandomAlphaNumeric(20) 
             session.set("fileName_1", fileName)
         })

		.exec(http("POST_CREATE")
			.post("/first-org/docker/create/${branchName}")
			.formParam("utf8", "✓")
            .formParam("authenticity_token", session => session("post_auth_token").validate[String])
			.formParam("filename", "${fileName_1}")
			.formParam("new_filename", "${fileName_1}")
			.formParam("commit", session => session("sha").validate[String])
			.formParam("same_repo", "1")
			.formParam("pr", "")
			.formParam("content_changed", "false")
			.formParam("value", "")
			.formParam("message", "")
			.formParam("placeholder_message", "Create: ${fileName_1}")
			.formParam("description", "")
			.formParam("commit-choice", "direct")
			.formParam("target_branch", "${branchName}")
			.formParam("quick_pull", ""))
       .exec(
              session => {
                println("THIS -- userName is: " + session("userName").validate[String])
                println("sha is : " + session("sha").validate[String])
                println("pre_auth_token is : " + session("pre_auth_token").validate[String])
                println("post_auth_token is : " + session("post_auth_token").validate[String])
                println("auth_token is : " + session("auth_token").validate[String])
                println("fileName is : " + session("fileName_1").validate[String])
                session
       })

	//setUp(scn.inject(rampUsers(5) over(30 seconds)).protocols(httpProtocol))
	//setUp(scn.inject(atOnceUsers(5))).protocols(httpProtocol)
	setUp(scn.inject(splitUsers(100) into(rampUsers(30) over(60 seconds)) separatedBy(atOnceUsers(10))).protocols(httpProtocol))
}
