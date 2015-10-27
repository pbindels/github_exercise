package create_branch2

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class Short extends Simulation {

	val httpProtocol = http
		.baseURL("http://52.88.81.144")
		.inferHtmlResources()

	val headers_0 = Map("Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
                   "Accept-Encoding" -> "identity")

	val headers_30 = Map(
		"Accept" -> "application/json",
		"Origin" -> "http://52.88.81.144",
		"x-requested-with" -> "XMLHttpRequest")

	val headers_31 = Map("Accept" -> "text/html")

    val uri1 = "http://52.88.81.144"
    val auth_token = regex("""<input name="authenticity_token" type="hidden" value="(.*?)" />""").saveAs("auth_token")
    val branches_token = regex(""".*?branches.*?<input name="authenticity_token" type="hidden" value="(.*?)" /></div>""").saveAs("branches_token")

	val scn = scenario("RecordedSimulation")
		.exec(http("GET_LOGIN")
			.get("/login")
			//.resources(http("GET_SESSION")
			//.headers(headers_0))
            //.get(uri1 + "/assets/octicons/octicons/octicons-d1f6e5a79e67d6de3908e18e13796b707592b476d1d770     c1dea0d3b30ae9bbc6.woff")
            .check(auth_token))
        .exec(http("GET_SESSION")
			.post("/session")
			//.post(uri1 + "/session")
			.headers(headers_0)
			.formParam("utf8", "✓")
            .formParam("authenticity_token", session => session("auth_token").validate[String])
			//.formParam("authenticity_token", "b30op85DWXlmuBb3TCUjzJwfFCOxT+jqiIUbIFW3yqHDgBsOBgLhQ22+noEQ0E1MHpCeihS1PhdtLj5i3cGPdw==")
			.formParam("login", "user1")
			.formParam("password", "passworD1")
			.formParam("return_to", "http://52.88.81.144/"))
        .exec(http("GET_DOCKER")
            .get("/first-org/docker")
            .check(branches_token))
        .exec(http("POST_BRANCH")
            .post(uri1 + "/first-org/docker/branches")
            .headers(headers_0)
            .formParam("utf8", "✓")
            .formParam("authenticity_token", session => session("branches_token").validate[String])
            //.formParam("authenticity_token", session => session("branches_token").validate[String])
            //.formParam("authenticity_token", "+uCmG2wewXwnqluKSokQn+C1bp5Hgb7BmDMBY2GcBQ1KI1CEVXCHhePpk4qHxCDim92Q6pgBuDKsRXmdFMwqCQ==")
            .formParam("name", "test55566ssssdfs")
            .formParam("branch", "master")
            .formParam("path", ""))
        .exec(
            session => {
            println("TOKEN is : " + session("branches_token").as[String])
            session
        })
            setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
