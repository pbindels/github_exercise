package create_file2

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {

	val httpProtocol = http
		.baseURL("http://52.88.81.144")
		.inferHtmlResources()

	val headers_2 = Map("Accept" -> "image/png,image/*;q=0.8,*/*;q=0.5")

	val headers_4 = Map(
		"Accept" -> "application/json",
		"Origin" -> "http://52.88.81.144",
		"x-requested-with" -> "XMLHttpRequest")

	val headers_5 = Map(
		"Accept" -> "text/html, */*; q=0.01",
		"Content-Type" -> "application/x-www-form-urlencoded; charset=UTF-8",
		"X-PJAX" -> "true",
		"X-PJAX-Container" -> "#js-repo-pjax-container",
		"x-requested-with" -> "XMLHttpRequest")

	val headers_8 = Map("Accept" -> "text/html")

    val uri1 = "http://52.88.81.144"
     val auth_token = regex("""<input name="authenticity_token" type="hidden" value="(.*?)" />""").saveAs("auth_token")
     val sha = regex("""<input type="hidden" name="commit" class="js-commit-oid" value="(.*?)">""").saveAs("sha")
         //.formParam("authenticity_token", session => session("auth_token").validate[String])

	val scn = scenario("RecordedSimulation")
           .exec(http("request_0")
          .get("/login")
          .check(auth_token))
		.exec(http("request_0")
			.post("/session")
            .check(auth_token)
			.formParam("utf8", "✓")
         .formParam("authenticity_token", session => session("auth_token").validate[String])
		//	.formParam("authenticity_token", "e9QSofaAV7HBfDk4NSskjnbrAX39LzXR459HSI74H04oTvfeMIlrYSxvcm64FQfWlAboLQEcrgz/fN4RdMVONQ==")
			.formParam("login", "user1")
			.formParam("password", "passworD1")
			.formParam("return_to", "http://52.88.81.144/")
			.resources(http("request_1")
			.get(uri1 + "/first-org/docker"),
            http("request_2")
			.get(uri1 + "/first-org/docker/raw/master/docs/static_files/docker-logo-compressed.png")
			.headers(headers_2),
            http("request_3")
			.get(uri1 + "/raw/first-org/docker/master/docs/static_files/docker-logo-compressed.png?token=AAAABG3AaUEddKJic_zR1S6Yay62v2xdks5WL8W6wA%3D%3D")
			.headers(headers_2),
            http("request_4")
			.get(uri1 + "/first-org/docker/issues/counts")
			.headers(headers_4),
            http("request_5")
			//.get(uri1 + "/first-org/docker/blob/master/zzz_perf.txt?_pjax=%23js-repo-pjax-container")
			//.get(uri1 + "/first-org/docker/edit/master/zzz_perf.txt")
			.get(uri1 + "/first-org/docker/tree-save/master/zzz_perf.txt")
            //.check(auth_token)
            .check(sha)
			.headers(headers_5),
            http("request_6")
			.post(uri1 + "/first-org/docker/edit/master/zzz_perf.txt")
			.formParam("utf8", "✓")
         .formParam("authenticity_token", session => session("auth_token").validate[String]),
		//	.formParam("authenticity_token", "a+pmXKQPBOq/SojmmMBpe99c/M42sWVLP617w4TSclia8qVNQ0tbA6VbCuLG7pGCr0FS1eGWjrt62wqI2gMXUg=="),
            http("request_7")
			.post(uri1 + "/first-org/docker/tree-save/master/zzz_perf.txt")
            //.check(sha)
			.formParam("utf8", "✓")
         .formParam("authenticity_token", session => session("auth_token").validate[String])
		//	.formParam("authenticity_token", "mejQYn5zyCPcOVk72GZIsl7vmRIReFWRoS7agVfyncmpe15A6Ipc1ZMPsIhD2r455O+U2T02A0i1pfVWO3e8Tg==")
			.formParam("filename", "zzz_perf.txt")
			.formParam("new_filename", "zzz_perf.txt")
			//.formParam("commit", "cdc0dfe6274eab4fbc686e215077aea4c0987c17")
			.formParam("commit", session => session("sha").validate[String]) 
			.formParam("same_repo", "1")
			.formParam("pr", "")
			.formParam("content_changed", "true")
			.formParam("value", "TTTTTT")
			.formParam("message", "")
			.formParam("placeholder_message", "Update zzz_perf.txt")
			.formParam("description", "")
			.formParam("commit-choice", "direct")
			.formParam("target_branch", "master")
			.formParam("quick_pull", ""),
            //http("request_8")
			//.get(uri1 + "/first-org/docker/contributors/master/zzz_perf.txt")
			//.headers(headers_8),
            http("request_9")
			.post(uri1 + "/logout")
			.formParam("utf8", "✓")
         .formParam("authenticity_token", session => session("auth_token").validate[String])))
		//	.formParam("authenticity_token", "Y1qSgUb3ni1wRwqgx+8obQbLNC8ZdDzftnXXeyTSbw+hF+cytx3y15q88N2CBHqkjzX0FgELV4v3mgaF7kfxFg==")))

        .exec( 
            session => { 
              println("sha is : " + session("sha").validate[String]) 
              session
        })

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
