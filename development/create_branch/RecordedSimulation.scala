package create_branch

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {

	val httpProtocol = http
		.baseURL("http://52.88.81.144")
		.inferHtmlResources()

	val headers_0 = Map("Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")

	val headers_28 = Map("Accept" -> "text/html")

	val headers_29 = Map(
		"Accept" -> "application/json",
		"Origin" -> "http://52.88.81.144",
		"x-requested-with" -> "XMLHttpRequest")

    val uri1 = "http://52.88.81.144"
    val auth_token = regex("""<input name="authenticity_token" type="hidden" value="(.*?)" />""").saveAs("auth_token")
    val branches_token = regex(""".*branch.*<input name="authenticity_token" type="hidden" value="(.*?)" /></div>""").saveAs("branches_token")
    //val sha = regex("""<input type="hidden" name="commit" class="js-commit-oid" value="(.*?)">""").saveAs("sha")
    //.formParam("authenticity_token", session => session("auth_token").validate[String])
   //action="/first-org/rails/branches

	val scn = scenario("RecordedSimulation")
		.exec(http("request_0")
			.get("/login")
            .check(status.is(200))
            .check(auth_token)
			.headers(headers_0)

			.resources(http("request_1")
			.post(uri1 + "/session")
			.headers(headers_0)
			.formParam("utf8", "✓")
            .formParam("authenticity_token", session => session("auth_token").validate[String])
			//.formParam("authenticity_token", "xDk6Uj8jJcBLQkHV84a7yZNDUvu3NcflNFghtRacT1xEJeY5bi7QVnyiaKxHgEVlq4VpdCCEje/eokA43kSfbA==")
			.formParam("login", "user1")
			.formParam("password", "passworD1")
			.formParam("return_to", "http://52.88.81.144/"),

            //http("GET_BRANCHES_TREE")
			//.get(uri1 + "/first-org/rails/tree")

            http("request_27")
			.get(uri1 + "/first-org/rails/")

            //.disableFollowRedirect
            .check(css("""#js-repo-pjax-container > div.file-navigation.in-mid-page > div.select-menu.js-menu-container.js-select-menu.left > div > div > div.select-menu-list.select-menu-tab-bucket.js-select-menu-tab-bucket.selected > form > div:nth-child(1) > input[type="hidden"]:nth-child(2)""").saveAs("mytoken"))

            //.check(xpath("""#js-repo-pjax-container > div.file-navigation.in-mid-page > div.select-menu.js-menu-container.js-select-menu.left > div > div > div.select-menu-list.select-menu-tab-bucket.js-select-menu-tab-bucket.selected > form > div:nth-child(1) > input[type="hidden"]:nth-child(2)""").saveAs("xpath_token"))
            .check(xpath("""//*[@id="js-repo-pjax-container"]/div[5]/div[1]/div/div/div[3]/form/div[1]/input[2]"""))
            .check(branches_token)
			.headers(headers_0),

            http("request_28")
			.get(uri1 + "/first-org/rails/show_partial?partial=tree%2Frecently_touched_branches_list")
			.headers(headers_28),

            //http("request_29")
			//.get(uri1 + "/first-org/rails/issues/counts")
			//.headers(headers_29),

            http("request_30")
			.post(uri1 + "/first-org/rails/branches")
			.headers(headers_0)
			.formParam("utf8", "✓")
            .formParam("authenticity_token", session => session("branches_token").validate[String])
            //.formParam("authenticity_token", session => session("branches_token").validate[String])
			//.formParam("authenticity_token", "+uCmG2wewXwnqluKSokQn+C1bp5Hgb7BmDMBY2GcBQ1KI1CEVXCHhePpk4qHxCDim92Q6pgBuDKsRXmdFMwqCQ==")
			.formParam("name", "test55")
			.formParam("branch", "master")
			.formParam("path", ""),

            http("request_31")
			.get(uri1 + "/first-org/rails/show_partial?partial=tree%2Frecently_touched_branches_list")
			.headers(headers_28),

            http("request_32")
			.get(uri1 + "/first-org/rails/issues/counts")
			.headers(headers_29)))

            //http("request_33")
			//.post(uri1 + "/logout")
			//.headers(headers_0)
            //.check(auth_token)
			//.formParam("utf8", "✓")
            //.formParam("authenticity_token", session => session("auth_token").validate[String])))
			//.formParam("authenticity_token", "YDNNQCvLR2rYFFHD/RX4kCC/fIDnlg/XTH06Ab0xJ4niH651ST3fOgZSBkiltOgdPViUPCpmmovGLfoXGCk4aA==")))
            .exec(
                 session => {
                 println("session before login: " + session)
                 println("------branches authenticity_token: " + session("branches_token").as[String])
                 println("------authenticity_token: " + session("auth_token").as[String])
                 println("------css_token: " + session("mytoken").as[String])
                 println("------xpath_token: " + session("xpath_token").as[String])
                 session
             })
	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
