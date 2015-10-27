package edit_file

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
import scala.util.Random

class EditFile extends Simulation {

	val httpProtocol = http
		.baseURL("http://52.88.81.144")
		.inferHtmlResources()

	val headers_3 = Map("Accept" -> "image/png,image/*;q=0.8,*/*;q=0.5")

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

    val uri1 = "godoc.org"
    val uri2 = "img.shields.io"
    val uri3 = "http://52.88.81.144"

    val auth_token = regex("""<input name="authenticity_token" type="hidden" value="(.*?)" />""").saveAs("auth_token")
    val post_auth_token = regex(""".*?edit\/master.*?<input name="authenticity_token" type="hidden" value="(.*?)" /></div>""").saveAs("post_auth_token")
    val pre_auth_token = regex(""".*?js-blob-form.*?<input name="authenticity_token" type="hidden" value="(.*?)" /></div>""").saveAs("pre_auth_token")
    val sha = regex("""<input type="hidden" name="commit" class="js-commit-oid" value="(.*?)">""").saveAs("sha")

    //val file_feeder = csv("edit_file_gatling.csv")
    val file_feeder = csv("edit_file_gatling.csv").random

    def randomString(length: Int) = {
        val r = new scala.util.Random
        val sb = new StringBuilder
        for (i <- 1 to length) {
            sb.append(r.nextPrintableChar)
         }
         sb.toString
    }

    def getRandomAlphaNumeric(length: Int) = {
        val sb = new StringBuilder
        val r =  Random.alphanumeric.take(length).mkString
        sb.append(r)
        r
    }

    def getRandomFileName(length: Int) = {
        val sb = new StringBuilder
        val r =  Random.alphanumeric.take(length).mkString
        sb.append(r)
        r
    }

	val scn = scenario("EditFile")
		.exec(http("request_0")
			.get("/login")
            .check(auth_token))

        .feed(file_feeder)

		.pause(2)
		.exec(http("request_1")
			.post("/session")
			.formParam("utf8", "✓")
            .formParam("authenticity_token", session => session("auth_token").validate[String])
			.formParam("login", session => session("userName").validate[String])
			.formParam("password", "passworD1"))

		.pause(2)
		.exec(http("request_2")
			.get("/first-org/docker"))

		//.pause(2)
		.exec(http("request_5")
            .get("${blobPath}")
            .check(post_auth_token)
			.headers(headers_5))
        .exec(
              session => {
                 println("THIS -- post_auth_token is : " + session("post_auth_token").validate[String])
                 println("THIS -- userName is : " + session("userName").validate[String])
                 println("THIS -- fileName is : " + session("fileName").validate[String])
                 session
        })
		.pause(2)
		.exec(http("request_6")
			.post("${prePath}")
            .check(pre_auth_token)
            .check(sha)
			.formParam("utf8", "✓")
            .formParam("authenticity_token", session => session("post_auth_token").validate[String]))

        .exec(
              session => {
                 println("THIS -- pre_auth_token is : " + session("pre_auth_token").validate[String])
                 println("THIS -- file is : " + session("fileName").validate[String])
                 println("THIS -- sha is : " + session("sha").validate[String])
                 session
        })
		.pause(2)
		.exec(http("request_7")
            .post("${postPath}")
			.formParam("utf8", "✓")
            .formParam("authenticity_token", session => session("pre_auth_token").validate[String])
			.formParam("filename", session => session("fileName").validate[String])
			.formParam("new_filename", session => session("fileName").validate[String])
			.formParam("commit", session => session("sha").validate[String])
			.formParam("same_repo", "1")
			.formParam("pr", "")
			.formParam("content_changed", "true")
			.formParam("value", getRandomAlphaNumeric(40))
			.formParam("message", "${fileName}")
			.formParam("placeholder_message", "Update ${fileName}")
			.formParam("description", "Perf Test Description")
			.formParam("commit-choice", "direct")
			.formParam("target_branch", "master")
			.formParam("quick_pull", ""))

	setUp(scn.inject(rampUsers(80) over (60 seconds))).protocols(httpProtocol)
	//setUp(scn.inject(atOnceUsers(10))).protocols(httpProtocol)
}
