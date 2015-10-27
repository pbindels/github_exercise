package create_file3

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {

	val httpProtocol = http
		.baseURL("http://52.88.81.144")
		.inferHtmlResources()

	val headers_3 = Map("Accept" -> "image/png,image/*;q=0.8,*/*;q=0.5")

	val headers_4 = Map(
		"Accept" -> "application/json",
		"Origin" -> "http://52.88.81.144",
		"x-requested-with" -> "XMLHttpRequest")

	val headers_6 = Map(
		"Accept" -> "text/html, */*; q=0.01",
		"Content-Type" -> "application/x-www-form-urlencoded; charset=UTF-8",
		"X-PJAX" -> "true",
		"X-PJAX-Container" -> "#js-repo-pjax-container",
		"x-requested-with" -> "XMLHttpRequest")

    val uri1 = "http://52.88.81.144"
    val auth_token = regex("""<input name="authenticity_token" type="hidden" value="(.*?)" />""").saveAs("auth_token")
    val sha = regex("""<input type="hidden" name="commit" class="js-commit-oid" value="(.*?)">""").saveAs("sha")
    //.formParam("authenticity_token", session => session("auth_token").validate[String])


	val scn = scenario("RecordedSimulation")
		.exec(http("request_0")
			.get("/login")
            .check(auth_token))
        .exec(http("GET_SESSION")
			.post(uri1 + "/session")
			.formParam("utf8", "✓")
            .formParam("authenticity_token", session => session("auth_token").validate[String])
			//.formParam("authenticity_token", "f1P1e1EW5fqS9BfDA5GVoHmeCZKXBsnnN/xuCxtGYUceT+EpaRqR4bPu6au/ok2XMcx7N886PpMn1gzMyOJSxA==")
			.formParam("login", "user1")
			.formParam("password", "passworD1")
			.formParam("return_to", "http://52.88.81.144/"))
        .exec(http("GET_DOCKER")
			//.get(uri1 + "/first-org/docker"),
            //http("request_6")
            .get(uri1 + "/first-org/docker/edit/master/zzz_perf.txt")
			//.get(uri1 + "/first-org/docker/blob/master/zzz_perf.txt?_pjax=%23js-repo-pjax-container")
            .check(sha)
            //.check(auth_token)
			.headers(headers_6))
        .exec(http("POST_FILE")
			.post(uri1 + "/first-org/docker/edit/master/zzz_perf.txt")
			.formParam("utf8", "✓")
            .formParam("authenticity_token", session => session("auth_token").validate[String])
			//.formParam("authenticity_token", "1FXhhRyZ1jewE1XU1tB7H8ySSOwUCrmJolGSgRmfXh4+QmJhjiT/SFWLvfh7JjE8cVFGzCAdRs6mJX2ehIGrFQ=="),
			//.headers(headers_3)
            .check(auth_token))
        .exec(http("POST_EDIT")
			.post(uri1 + "/first-org/docker/tree-save/master/zzz_perf.txt")
			.formParam("utf8", "✓")
            .formParam("authenticity_token", session => session("auth_token").validate[String])
			//.formParam("authenticity_token", "HyDYGul7A1ajwCfYZYekpUaCopa0O70XngVkpGvWn/lHuFR8Rqb5KAzCWP6aKBJye7G0ltpZTAnvS35bHA+Q5A==")
			.formParam("filename", "zzz_perf.txt")
			.formParam("new_filename", "zzz_perf.txt")
			//.formParam("commit", "978c8d8cb15d70b5adc2a17fedf1af52392e20b9")
            .formParam("commit", session => session("sha").validate[String])
			.formParam("same_repo", "1")
			.formParam("pr", "")
			.formParam("content_changed", "true")
			.formParam("value", "xxxxx")
			.formParam("message", "")
			.formParam("placeholder_message", "Update zzz_perf.txt")
			.formParam("description", "")
			.formParam("commit-choice", "direct")
			.formParam("target_branch", "master")
			.formParam("quick_pull", ""))

      .exec(
           session => {
            println("sha is : " + session("sha").validate[String])
            session
     })

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
