package test_scenario_4 

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class DownloadRepos extends Simulation {

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

	val headers_3 = Map("Accept" -> "image/png,image/*;q=0.8,*/*;q=0.5")

	val headers_4 = Map(
		"Accept" -> "application/json",
		"Origin" -> endpoint, 
		"x-requested-with" -> "XMLHttpRequest")

    val uri1 = endpoint 
    val auth_token = regex("""<input name="authenticity_token" type="hidden" value="(.*?)" />""").saveAs("auth_token")
    val user_feeder= csv("users.csv").random
    val repo_feeder= csv("repos.csv").random

	val scn = scenario("DownloadRepos")
		.exec(http("GET_LOGIN")
			.get("/login")
            .check(auth_token))
		.pause(2)

        .feed(user_feeder)
        .feed(repo_feeder)

        .exec(
           session => {
             println("repo is " + session("repoName").validate[String])
             session
        })

		.exec(http("POST_SESSION_LOGIN")
			.post("/session")
			.formParam("utf8", "✓")
            .formParam("authenticity_token", session => session("auth_token").validate[String])
			.formParam("login", "${userName}")
			.formParam("password", pwd)) 
		.pause(1)
		.exec(http("GET_DOWNLOAD")
			.get("/first-org/${repoName}/archive/master.zip")
			.check(md5.is("${md5}")))

    setUp(scn.inject(rampUsers(rampusers) over(ramptime seconds)).protocols(httpProtocol))
    //setUp(scn.inject(rampUsers(5) over(30 seconds)).protocols(httpProtocol))
	//setUp(scn.inject(atOnceUsers(20))).protocols(httpProtocol)
    //setUp(scn.inject(atOnceUsers(5))).protocols(httpProtocol)
    //setUp(scn.inject(splitUsers(100) into(rampUsers(30) over(60 seconds)) separatedBy(atOnceUsers(10))).protocols(httpProtocol))
}
