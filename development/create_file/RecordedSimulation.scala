package create_file

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {

	val httpProtocol = http
		.baseURL("http://52.88.81.144")
		.inferHtmlResources()

	val headers_4 = Map(
		"Accept" -> "application/json",
		"Origin" -> "http://52.88.81.144",
		"x-requested-with" -> "XMLHttpRequest")

	val headers_5 = Map("Accept" -> "image/png,image/*;q=0.8,*/*;q=0.5")

	val headers_8 = Map("Accept" -> "text/html")

    val uri1 = "https://godoc.org/github.com/docker/docker"
    val uri2 = "https://img.shields.io/github/release/docker/docker.svg"
    val uri3 = "http://52.88.81.144"

    val auth_token = regex("""<input name="authenticity_token" type="hidden" value="(.*?)" />""").saveAs("auth_token")
    //.formParam("authenticity_token", session => session("auth_token").validate[String])


	val scn = scenario("RecordedSimulation")
		.exec(http("request_0")
			.get("/login?return_to=http%3A%2F%2F52.88.81.144%2F"))
		.pause(8)
		.exec(http("request_1")
			.get("/")
            .check(auth_token))
		.pause(5)
		.exec(http("request_2")
			.post("/session")
			.formParam("utf8", "✓")
            .formParam("authenticity_token", session => session("auth_token").validate[String])
			//.formParam("authenticity_token", "s6cTXtc6tFXYakWfhZZQ38UFq0vZxb6agKSpE5+50BJbnWjNPc2vwuH2UQmRs14zq7AUee3bfGuffd8xTHKvHQ==")
			.formParam("login", "user1")
			.formParam("password", "passworD1")
			.formParam("return_to", "http://52.88.81.144/"))
		.pause(2)
		.exec(http("request_3")
			.get("/first-org/docker")
			.resources(http("request_4")
			.get(uri3 + "/first-org/docker/issues/counts")
			.headers(headers_4),
            http("request_5")
			.get(uri3 + "/raw/first-org/docker/master/docs/static_files/docker-logo-compressed.png?token=AAAABL2O9pVv1gRe2wj5ch3lth0UNUxPks5WLxD8wA%3D%3D")
			.headers(headers_5)))
		.pause(1)
		.exec(http("request_6")
			.post("/first-org/docker/new/master")
            .check(auth_token)
			.formParam("utf8", "✓")
            .formParam("authenticity_token", session => session("auth_token").validate[String]))
			//.formParam("authenticity_token", "lwH0HwHniJe1yo8ejt9XENmxz8Iv29M8imWfHNRiMIJEdMNAOK6Tji/WsscSwW1+3lfJtaW1E1Occh4Rnk9GcQ=="))
        .exec(
            session => {
                 println("SESSION before login: " + session)
                 println("authenticity_token: " + session("auth_token"))
                 println("AUTH TOKEN : " + session("auth_token").validate[String])
                 session
        })
		.pause(16)
		.exec(http("request_7")
			.post("/first-org/docker/create/master")
			.formParam("utf8", "✓")
            .formParam("authenticity_token", session => session("auth_token").validate[String])
			//.formParam("authenticity_token", "wWxhm9b7KdRCKPCfQoWmfL58DcH4LBmntZOY8gTqx8DNYSxVOUYycq/ToGRRcsdGxlCsBPWgyHxDFhBa4N4zZA==")
			.formParam("filename", "zzz_perf2.txt")
			.formParam("new_filename", "zzz_perf2.txt")
			.formParam("commit", "2a6b16aff4fc6a0e0c516e741dd6c3d8b5dd8cf0")
			.formParam("same_repo", "1")
			.formParam("pr", "")
			.formParam("content_changed", "true")
			.formParam("value", "test")
			.formParam("message", "")
			.formParam("placeholder_message", "Create zzz_perf2.txt")
			.formParam("description", "")
			.formParam("commit-choice", "direct")
			.formParam("target_branch", "master")
			.formParam("quick_pull", "")
			.resources(http("request_8")
			.get(uri3 + "/first-org/docker/tree-commit/29c01d0f6425dd658da79ff4237a14e82caf4a9b")
			.headers(headers_8),
            http("request_9")
			.get(uri3 + "/raw/first-org/docker/master/docs/static_files/docker-logo-compressed.png?token=AAAABB2sk6VE1w0r1tkZ7hq1Xn3PRY9_ks5WLxEQwA%3D%3D")
			.headers(headers_5),
            http("request_10")
			.get(uri3 + "/first-org/docker/issues/counts")
			.headers(headers_4),
            http("request_11")
			.get(uri3 + "/first-org/docker/file-list/master")
			.headers(headers_8)))
		.pause(3)
		.exec(http("request_12")
			.post("/logout")
			.formParam("utf8", "✓")
			.formParam("authenticity_token", "IVp482G9HSEZWN+toqFHq/EalFMTbijaKZugRotroskZy6Qaj1O6hEuECQG7ztxsrHDH8BTS+ED38JKHb2R0dg=="))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
