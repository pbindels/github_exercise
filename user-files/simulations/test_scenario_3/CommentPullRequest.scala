package test_scenario_3

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
import scala.util.Random

class CommentPullRequest extends Simulation {

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

	val headers_1 = Map(
		"Accept" -> "application/font-woff2;q=1.0,application/font-woff;q=0.9,*/*;q=0.8",
		"Accept-Encoding" -> "identity")

	val headers_6 = Map(
		"Accept" -> "*/*;q=0.5, text/javascript, application/javascript, application/ecmascript, application/x-ecmascript",
		"X-Requested-With" -> "XMLHttpRequest",
		"X-Timeline-Last-Modified" -> "Fri, 16 Oct 2015 22:54:07 GMT")

	val headers_7 = Map(
		"Origin" -> endpoint, 
		"X-Requested-With" -> "XMLHttpRequest")

	val headers_8 = Map(
		"Accept" -> "application/json, text/javascript, */*; q=0.01",
		"Pragma" -> "no-cache",
		"X-CSRF-Token" -> "pSpOkXlKefTTTcCg16OY+1UjDJAvpYmuJKhsDm+gcIguM85WpXhkebKrujA4Xx95+jMihn9RX/pR0wcgKAXUNg==",
		"X-Requested-With" -> "XMLHttpRequest",
		"X-Timeline-Last-Modified" -> "Fri, 16 Oct 2015 22:54:07 GMT")

    val uri1 = endpoint 
    val auth_token = regex("""<input name="authenticity_token" type="hidden" value="(.*?)" />""").saveAs("auth_token")
    val user_feeder = csv("users.csv").random

    def randomString(length: Int) = {
        val r = new scala.util.Random
        val sb = new StringBuilder
        for (i <- 1 to length) {
            sb.append(r.nextPrintableChar)
        }
        sb.toString
    }


	val scn = scenario("CommentPullRequest")
		.exec(http("GET_LOGIN")
			.get("/login")
            .check(auth_token))

		.pause(1)
        .feed(user_feeder)

        .exec( 
            session => {
                println("feeder is : " + session("userName").validate[String])
                session
        })

		.exec(http("POST_SESSION_LOGIN")
			.post("/session")
			.formParam("utf8", "✓")
			.formParam("authenticity_token", session => session("auth_token").validate[String]) 
			.formParam("login", session => session("userName").validate[String])
			.formParam("password", pwd) 
			.formParam("return_to", endpoint))
		.pause(1)

		.exec(http("GET_PULLS")
			.get("/pulls")
            .check(auth_token))
		.pause(1)

		.exec(http("GET_ONE_PULL")
			.get("/first-org/rails/pull/1")
            .check(auth_token))
		.pause(1)

		.exec(http("GET_SUGGESTIONS")
			.get("/first-org/rails/suggestions/pull_request/2")
			.headers(headers_7))
		.pause(1)

		.exec(http("POST_PULL_COMMENT")
			.post("/first-org/rails/pull/1/comment")
			.headers(headers_8)
			.formParam("utf8", "✓")
			.formParam("authenticity_token", session => session("auth_token").validate[String]) 
			.formParam("issue", "1")
			.formParam("comment[body]", randomString(20)))

    setUp(scn.inject(rampUsers(rampusers) over(ramptime seconds)).protocols(httpProtocol))
    //setUp(scn.inject(rampUsers(5) over(30 seconds)).protocols(httpProtocol))
	//setUp(scn.inject(atOnceUsers(40))).protocols(httpProtocol)
    //setUp(scn.inject(atOnceUsers(5))).protocols(httpProtocol)
    //setUp(scn.inject(splitUsers(100) into(rampUsers(30) over(60 seconds)) separatedBy(atOnceUsers(10))).protocols(httpProtocol))
}
