package pr_comment

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class create extends Simulation {

	val httpProtocol = http
		.baseURL("http://52.88.81.144")
		.inferHtmlResources()

	val headers_2 = Map(
		"Accept" -> "*/*;q=0.5, text/javascript, application/javascript, application/ecmascript, application/x-ecmascript",
		"X-Requested-With" -> "XMLHttpRequest")

	val headers_4 = Map("Accept" -> "image/png,image/*;q=0.8,*/*;q=0.5")

	val headers_7 = Map(
		"Origin" -> "http://52.88.81.144",
		"X-Requested-With" -> "XMLHttpRequest")

	val headers_8 = Map(
		"Accept" -> "application/json, text/javascript, */*; q=0.01",
		"Pragma" -> "no-cache",
		"X-CSRF-Token" -> "uDy/+pcGeC9V5lxQGHUn3lb/uZfYtBfuC1INNh8z9B/23QCKM0ZsET/3p6xSKAEWzXnf1U83BYQg7xejk2HUoA==",
		"X-Requested-With" -> "XMLHttpRequest",
		"X-Timeline-Last-Modified" -> "Fri, 09 Oct 2015 14:49:54 GMT")

    val uri1 = "http://52.88.81.144"

	val scn = scenario("create")
		.exec(http("request_0")
			.get("/"))
		.pause(11)
		.exec(http("request_1")
			.get("/pulls"))
		.pause(4)
		.exec(http("request_2")
			.get("/issues/show_menu_content?partial=issues%2Ffilters%2Fvisibility_content&pulls_only=true&q=is%3Aopen+is%3Apr+author%3Auser1")
			.headers(headers_2)
			.resources(http("request_3")
			.get(uri1 + "/issues/show_menu_content?partial=issues%2Ffilters%2Forgs_content&pulls_only=true&q=is%3Aopen+is%3Apr+author%3Auser1")
			.headers(headers_2),
            http("request_4")
			.get(uri1 + "/avatars/u/4?s=60")
			.headers(headers_4),
            http("request_5")
			.get(uri1 + "/avatars/u/38?s=60")
			.headers(headers_4)))
		.pause(3)
		.exec(http("request_6")
			.get("/first-org/rails/pull/1"))
		.pause(5)
		.exec(http("request_7")
			.get("/first-org/rails/suggestions/pull_request/2")
			.headers(headers_7))
		.pause(13)
		.exec(http("request_8")
			.post("/first-org/rails/pull/1/comment")
			.headers(headers_8)
			.formParam("utf8", "✓")
			.formParam("authenticity_token", "snX+mKsNXcaHiem1e7Oh5blR2+WpetvB0Djpappi7+JLUvaEqo1t4xUuw1ax2KcbtBd7gabRAOErSV32prKUhg==")
			.formParam("issue", "1")
			.formParam("comment[body]", "Add comment X"))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
