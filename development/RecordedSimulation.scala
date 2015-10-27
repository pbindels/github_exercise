
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {

	val httpProtocol = http
		.baseURL("http://sac-ops-deploy-web-01.unix.newokl.com:8445")
		.inferHtmlResources()

	val headers_3 = Map(
		"Accept" -> "application/font-woff2;q=1.0,application/font-woff;q=0.9,*/*;q=0.8",
		"Accept-Encoding" -> "identity")

	val headers_5 = Map("Accept" -> "image/png,image/*;q=0.8,*/*;q=0.5")

	val headers_6 = Map(
		"Accept" -> "application/json, text/javascript, */*; q=0.01",
		"X-CSRF-Token" -> "SC6VgDgowyGWEfYvxYuRnv3JMPNzWikFL+bpF+IkY4g=",
		"X-Requested-With" -> "XMLHttpRequest")

    val uri1 = "http://sac-ops-deploy-web-01.unix.newokl.com:8445"
    val uri2 = "http://qdeploy.newokl.com"

	val scn = scenario("RecordedSimulation")
		.exec(http("request_0")
			.get(uri2 + "/")
			.basicAuth("pbindels","Akira0816"))
		.pause(18)
		.exec(http("request_1")
			.get("/")
			.check(status.is(401)))
		.pause(4)
		.exec(http("request_2")
			.get("/")
			.basicAuth("pbindels","Akira0816")
			.resources(http("request_3")
			.get(uri1 + "/assets/fontawesome-webfont-c4adb9424c8b6a6b1b9b0d2627528c4c.woff")
			.headers(headers_3)
			.basicAuth("pbindels","Akira0816")))
		.pause(19)
		.exec(http("request_4")
			.get("/tokens")
			.basicAuth("pbindels","Akira0816"))
		.pause(13)
		.exec(http("request_5")
			.get("/assets/loading-cb8487450db48298c3c829c1f874f798.gif")
			.headers(headers_5)
			.basicAuth("pbindels","Akira0816"))
		.pause(4)
		.exec(http("request_6")
			.patch("/tokens/10351")
			.headers(headers_6)
			.formParam("name", "")
			.formParam("value", "aaabb")
			.formParam("pk", "10351")
			.formParam("token[value]", "aaabb")
			.basicAuth("pbindels","Akira0816"))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}