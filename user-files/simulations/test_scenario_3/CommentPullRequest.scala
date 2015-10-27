package test_scenario_3

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
import scala.util.Random

class CommentPullRequest extends Simulation {

	val httpProtocol = http
		.baseURL("http://52.88.81.144")
		.inferHtmlResources()

	val headers_1 = Map(
		"Accept" -> "application/font-woff2;q=1.0,application/font-woff;q=0.9,*/*;q=0.8",
		"Accept-Encoding" -> "identity")

	val headers_6 = Map(
		"Accept" -> "*/*;q=0.5, text/javascript, application/javascript, application/ecmascript, application/x-ecmascript",
		"X-Requested-With" -> "XMLHttpRequest",
		"X-Timeline-Last-Modified" -> "Fri, 16 Oct 2015 22:54:07 GMT")

	val headers_7 = Map(
		"Origin" -> "http://52.88.81.144",
		"X-Requested-With" -> "XMLHttpRequest")

	val headers_8 = Map(
		"Accept" -> "application/json, text/javascript, */*; q=0.01",
		"Pragma" -> "no-cache",
		"X-CSRF-Token" -> "pSpOkXlKefTTTcCg16OY+1UjDJAvpYmuJKhsDm+gcIguM85WpXhkebKrujA4Xx95+jMihn9RX/pR0wcgKAXUNg==",
		"X-Requested-With" -> "XMLHttpRequest",
		"X-Timeline-Last-Modified" -> "Fri, 16 Oct 2015 22:54:07 GMT")

    val uri1 = "http://52.88.81.144"
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
		.exec(http("request_0")
			.get("/login")
            .check(auth_token))

		.pause(6)
        .feed(user_feeder)

        //val comment = randomString(20)

        .exec( 
            session => {
                println("feeder is : " + session("userName").validate[String])
                session
        })

		.exec(http("request_2")
			.post("/session")
			.formParam("utf8", "✓")
			.formParam("authenticity_token", session => session("auth_token").validate[String]) 
			.formParam("login", session => session("userName").validate[String])
			.formParam("password", "passworD1")
			.formParam("return_to", "http://52.88.81.144/"))
		.pause(3)

		.exec(http("request_3")
			.get("/pulls")
            .check(auth_token))
		.pause(1)

		.exec(http("request_4")
			.get("/first-org/rails/pull/1")
            .check(auth_token))
		.pause(1)

		.exec(http("request_7")
			.get("/first-org/rails/suggestions/pull_request/2")
			.headers(headers_7))
		.pause(5)

		.exec(http("request_8")
			.post("/first-org/rails/pull/1/comment")
			.headers(headers_8)
			.formParam("utf8", "✓")
			.formParam("authenticity_token", session => session("auth_token").validate[String]) 
			.formParam("issue", "1")
			.formParam("comment[body]", randomString(20)))

	setUp(scn.inject(atOnceUsers(40))).protocols(httpProtocol)
}
