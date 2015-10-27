package pull_request

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {

	val httpProtocol = http
		.baseURL("http://52.88.81.144")
		.inferHtmlResources()

	val headers_3 = Map(
		"Accept" -> "application/json",
		"Origin" -> "http://52.88.81.144",
		"x-requested-with" -> "XMLHttpRequest")

	val headers_4 = Map("Accept" -> "image/png,image/*;q=0.8,*/*;q=0.5")

    val uri1 = "https://godoc.org/github.com/docker/docker"
    val uri2 = "https://img.shields.io/github/release/docker/docker.svg"
    val uri3 = "http://52.88.81.144"

    val auth_token = regex("""<input name="authenticity_token" type="hidden" value="(.*?)" />""").saveAs("auth_token")
    val post_auth_token = regex(""".*js-blob-form js-new-blob-form.*?<input name="authenticity_token" type="hidden" value="(.*?)" /></div>""").saveAs("post_auth_token")
    val pre_auth_token = regex(""".*?js-new-blob-form inline-form.*?<input name="authenticity_token" type="hidden" value="(.*?)" /></div>""").saveAs("pre_auth_token")
    val pull_auth_token = regex(""".*?new-pr-form.*?<input name="authenticity_token" type="hidden" value="(.*?)" /></div>""").saveAs("pull_auth_token")
    val sha = regex("""<input type="hidden" name="commit" class="js-commit-oid" value="(.*?)">""").saveAs("sha")

	val scn = scenario("RecordedSimulation")
		.exec(http("request_0")
			.get("/login")
            .check(auth_token))

        .exec(
              session => {
                 println("THIS -- auth_token is : " + session("auth_token").validate[String])
                 session
        })
		.pause(6)
		.exec(http("request_1")
			.post("/session")
			.formParam("utf8", "✓")
            .formParam("authenticity_token", session => session("auth_token").validate[String])
			//.formParam("authenticity_token", "ZaXKLo7k/jRGP4ZoWyH7H458C9KupBaA885KbI+tFGDNpaTiXKywWH8pMpx/VZgu0m+8JReQkmvtMkkbVBuKMg==")
			.formParam("login", "user1")
			.formParam("password", "passworD1"))

		//.pause(2)
		//.exec(http("request_2")
	//		.get("/first-org/docker"))

		.pause(6)
		.exec(http("GET_DOCKER_TREE_XXX")
			.get("/first-org/docker/tree/xxx")
            .resources(http("request_5")
            .get(uri3 + "/raw/first-org/docker/xxx/docs/static_files/docker-logo-compressed.png?token=AAAABK0yE5KobUSWHtr414DlDn_VzWzHks5WMcIfwA%3D%3D")
            .headers(headers_4),
            http("request_6")
            .get(uri3 + "/first-org/docker/issues/counts")
            .headers(headers_3))
            .check(pre_auth_token))

        .exec(
              session => {
                 println("THIS -- pre_auth_token is : " + session("pre_auth_token").validate[String])
                 session
        })
		.pause(3)
		.exec(http("request_8")
			.post("/first-org/docker/new/xxx")
            .check(post_auth_token)
			.formParam("utf8", "✓")
            .formParam("authenticity_token", session => session("pre_auth_token").validate[String]))
			//.formParam("authenticity_token", "ViDnMI1SZfMMpEgwoxUy/+kIp84LpX/zzRy15obBQ9ElhROrulEbNn2i6t8jbcZvBcgkGpb1697nJM7XIq+RLw=="))

        .exec(
              session => {
                 println("THIS -- post_auth_token is : " + session("post_auth_token").validate[String])
                 session
        })
		.pause(2)
		.exec(http("request_9")
			.post("/first-org/docker/create/xxx")
            .check(pull_auth_token)
			.formParam("utf8", "✓")
            .formParam("authenticity_token", session => session("post_auth_token").validate[String])
			//.formParam("authenticity_token", "why7e6HDjx0HKz4vpd2sw1y8IhWYa8Idn4DsrdPis+xDYY8Gog7Giu46xlQVhiXN0yFiX+8TYYmmem4Bms0V8g==")
			.formParam("filename", "zzz_test44.txt")
			.formParam("new_filename", "zzz_test44.txt")
			.formParam("commit", "5bc99ac58395e59bd38a8e92ab8f01de4de15c07")
			.formParam("same_repo", "1")
			.formParam("pr", "")
			.formParam("content_changed", "true")
			.formParam("value", "new pull req")
			.formParam("message", "")
			.formParam("placeholder_message", "Create zzz_testi44.txt")
			.formParam("description", "")
			.formParam("commit-choice", "quick-pull")
			.formParam("target_branch", "user1-patch-4")
			.formParam("quick_pull", "xxx"))

        .exec(
              session => {
                 println("THIS -- pull_auth_token is : " + session("pull_auth_token").validate[String])
                 session
        })
		.pause(3)
		.exec(http("request_11")
			.post("/first-org/docker/pull/create")
			.formParam("utf8", "✓")
            .formParam("authenticity_token", session => session("pull_auth_token").validate[String])
			//.formParam("authenticity_token", "AxtgTQSNRoWeM+42Q5RloCddmy9KPpB4mpAB1UMCGQC0oIZeVHY08OtpJpmQf7wy8ciPtBCI7zXkJE1AVtAETQ==")
			.formParam("pull_request[title]", "Create zzz_test44.txt")
			.formParam("pull_request[body]", "")
			.formParam("quick_pull", "1")
			.formParam("base", "first-org:xxx")
			.formParam("head", "first-org:user1-patch-4"))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
