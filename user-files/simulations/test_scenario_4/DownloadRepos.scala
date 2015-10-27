package test_scenario_4 

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class DownloadRepos extends Simulation {

	val httpProtocol = http
		.baseURL("http://52.88.81.144")
		.inferHtmlResources()

	val headers_3 = Map("Accept" -> "image/png,image/*;q=0.8,*/*;q=0.5")

	val headers_4 = Map(
		"Accept" -> "application/json",
		"Origin" -> "http://52.88.81.144",
		"x-requested-with" -> "XMLHttpRequest")

    val uri1 = "http://52.88.81.144"
    val auth_token = regex("""<input name="authenticity_token" type="hidden" value="(.*?)" />""").saveAs("auth_token")
    val user_feeder= csv("users.csv").random
    val repo_feeder= csv("repos.csv").random

	val scn = scenario("DownloadRepos")
		.exec(http("request_0")
			.get("/login")
            .check(auth_token))
		.pause(4)

        .feed(user_feeder)
        .feed(repo_feeder)

        .exec(
           session => {
             println("repo is " + session("repoName").validate[String])
             session
        })

		.exec(http("request_1")
			.post("/session")
			.formParam("utf8", "✓")
            .formParam("authenticity_token", session => session("auth_token").validate[String])
			.formParam("login", "${userName}")
			.formParam("password", "passworD1"))
		.pause(2)
		.exec(http("request_6")
			.get("/first-org/${repoName}/archive/master.zip")
			.check(md5.is("${md5}")))

	setUp(scn.inject(atOnceUsers(20))).protocols(httpProtocol)
}
