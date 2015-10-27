package commit_file

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {

	val httpProtocol = http
		.baseURL("http://52.88.81.144")
		.inferHtmlResources()

	val headers_3 = Map("Accept" -> "image/png,image/*;q=0.8,*/*;q=0.5")

	val headers_5 = Map(
		"Accept" -> "application/json",
		"Origin" -> "http://52.88.81.144",
		"x-requested-with" -> "XMLHttpRequest")

	val headers_6 = Map(
		"Accept" -> "text/html, */*; q=0.01",
		"Content-Type" -> "application/x-www-form-urlencoded; charset=UTF-8",
		"X-PJAX" -> "true",
		"X-PJAX-Container" -> "#js-repo-pjax-container",
		"x-requested-with" -> "XMLHttpRequest")

	val headers_7 = Map("Accept" -> "text/html")

    val uri1 = "http://52.88.81.144"
    val auth_token = regex("""<input name="authenticity_token" type="hidden" value="(.*?)" />""").saveAs("auth_token")
    //.formParam("authenticity_token", session => session("auth_token").validate[String])

	val scn = scenario("RecordedSimulation")
		.exec(http("request_0")
			.get("/")
            .check(auth_token)
			.resources(http("request_1")
			.post(uri1 + "/session")
			.formParam("utf8", "✓")
            .formParam("authenticity_token", session => session("auth_token").validate[String])
			.formParam("login", "user1")
			.formParam("password", "passworD1")
			.formParam("return_to", "http://52.88.81.144/"),
            http("request_2")
			.get(uri1 + "/first-org/docker")
            .check(auth_token),
            http("request_3")
			.get(uri1 + "/first-org/docker/raw/master/docs/static_files/docker-logo-compressed.png")
			.headers(headers_3),
            //http("request_4")
			//.get(uri1 + "/raw/first-org/docker/master/docs/static_files/docker-logo-compressed.png?token=AAAABCMX7PqVPwsThN3BTMkB4b0nrDmpks5WLrn3wA%3D%3D")
			//.headers(headers_3),
            http("request_5")
			.get(uri1 + "/first-org/docker/issues/counts")
            .check(auth_token)
			.headers(headers_5),
            http("request_6")
			.get(uri1 + "/first-org/docker/blob/master/zzz_perf.txt?_pjax=%23js-repo-pjax-container")
			.headers(headers_6),
            http("request_7")
			.get(uri1 + "/first-org/docker/contributors/master/zzz_perf.txt")
            .check(auth_token)
			.headers(headers_7),
            http("request_8")
			.get(uri1 + "/avatars/u/4?s=48")
			.headers(headers_3),
            http("request_9")
			.post(uri1 + "/first-org/docker/edit/master/zzz_perf.txt")
			.formParam("utf8", "✓")
            .formParam("authenticity_token", session => session("auth_token").validate[String]),
			//.formParam("authenticity_token", "O7d+kk8HZu0j0XZxCAbixLFRs4NUpBV+eERPXoKhojdw2xKFylH33OW3kM3qpmDpauR/TmD/6zE9rPS5BVk70Q=="),
            http("request_10")
			.post(uri1 + "/first-org/docker/tree-save/master/zzz_perf.txt")
            .check(auth_token)
			.formParam("utf8", "✓")
            .formParam("authenticity_token", session => session("auth_token").validate[String])
			//.formParam("authenticity_token", "a3++uJtv0x1FechhtRYvkaM4ft+TqUG5ZTe3I4n9DqET9Gwz947LskeOnnhTAsUgIT3+L6dnK083m1IYUPosug==")
			.formParam("filename", "zzz_perf.txt")
			.formParam("new_filename", "zzz_perf.txt")
			.formParam("commit", "72215ca23c0fee49a18c87c66c751be7c81b9cd9")
			.formParam("same_repo", "1")
			.formParam("pr", "")
			.formParam("content_changed", "true")
			.formParam("value", "testfileasdf")
			.formParam("message", "")
			.formParam("placeholder_message", "Update zzz_perf.txt")
			.formParam("description", "")
			.formParam("commit-choice", "direct")
			.formParam("target_branch", "master")
			.formParam("quick_pull", ""),
            http("request_11")
			.get(uri1 + "/first-org/docker/contributors/master/zzz_perf.txt")
			.headers(headers_7),
            http("request_12")
			.post(uri1 + "/logout")
            .check(auth_token)
			.formParam("utf8", "✓")
            .formParam("authenticity_token", session => session("auth_token").validate[String])))
			//.formParam("authenticity_token", "8ZdE0D8nGtb+85k7boD28dKFwAANuUDs+hANXwfFMlW1TsFpAbxkwCT6DZGkBYqBkUI5aZUnKogrhL4ji+d83A==")))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
