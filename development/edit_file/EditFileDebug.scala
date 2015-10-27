package edit_file

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
import scala.util.Random

class EditFileDebug extends Simulation {

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

    val user_feeder = csv("gh.csv").random
    val repo_feeder = csv("repo.csv").random
    val file_feeder = csv("edit_file_gatling.csv").random
    
    //val branch_feeder = csv("branches.csv").random 

    var newstr = new StringBuilder
    var str = ""

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

    def buildFullPath(file: String) = {
        val sb = new StringBuilder("/first-org/docker/blob/master/")
        sb.append(file)
        sb.append("?_pjax=%23js-repo-pjax-container")
        println("file is : " + file)
        //println("r is : " + r)
        println("sb is : " + sb)
        sb.toString
    }

    def buildFilePath(dir: String, file: String) = {
        val sb = new StringBuilder
        sb.append(dir)
        sb.append(file)
        println("file is : " + file)
        println("dir is : " + dir)
        println("sb is : " + sb)
        sb.toString
    }

    def getRandomFileName(length: Int) = {
        val sb = new StringBuilder
        val r =  Random.alphanumeric.take(length).mkString
        sb.append(r)
        r
    }

   var fullPath = ""
   var filePath = ""
   var fileName = ""
   val blobPath = ""
	val scn = scenario("EditFileDebug")
		.exec(http("request_0")
			.get("/login")
            .check(auth_token))

        .feed(user_feeder)
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


        .exec((session: Session) => { // use a simple action
            fullPath = buildFullPath(session("fileName").as[String]) // retrieve the keywords saved by the previous check
            //filePath = buildFilePath("/first-org/docker/edit/master/", session("fileName").as[String])
            val filePath1 =  session("blobPath").as[String]
            session.set("filePath_1",filePath1)
            println("fullPath is: " + fullPath)
            println("filePath is: " + filePath)
            println("filePath2 is: " + filePath1)
            session
        })

		//.pause(2)
		.exec(http("request_5")
			//.get("/first-org/docker/blob/master/zzzz99.txt?_pjax=%23js-repo-pjax-container")
			//.get("${filePath1}")
            .get("${blobPath}")
            //println("fullPath in HERE is : " + fullPath)
			//.get(fullPath)
            .check(post_auth_token)
			.headers(headers_5))
        .exec(
              session => {
                 println("THIS -- post_auth_token is : " + session("post_auth_token").validate[String])
                 println("THIS -- userName is : " + session("userName").validate[String])
                 println("THIS -- fileName is : " + session("fileName").validate[String])
                 println("THIS -- fullPath is : " + fullPath) 
                 println("THIS -- fileName is : " + fileName) 
                 session
        })
		.pause(2)
		.exec(http("request_6")
			.post("${prePath}")
			//.post("/first-org/docker/edit/master/zzzz99.txt")
			//.post(filePath)
			//.post(buildFilePath("/first-org/docker/edit/master", fileName))
			//.post(buildFilePath("/first-org/docker/edit/master", "zzzz99.txt"))
            .check(pre_auth_token)
            .check(sha)
			.formParam("utf8", "✓")
            .formParam("authenticity_token", session => session("post_auth_token").validate[String]))

        .exec(
              session => {
                 println("THIS -- pre_auth_token is : " + session("pre_auth_token").validate[String])
                 println("THIS -- file is : " + session("fileName").validate[String])
                 println("THIS -- filePath is : " + filePath)
                 println("THIS -- sha is : " + session("sha").validate[String])
                 session
        })
		.pause(2)
		.exec(http("request_7")
            .post("${postPath}")
			//.post("/first-org/docker/tree-save/master/zzzz99.txt")
			//.post(buildFilePath("/first-org/docker/tree-save/master/", fileName))
			//.post(buildFilePath("/first-org/docker/tree-save/master/", "zzzz99.txt"))
			.formParam("utf8", "✓")
            .formParam("authenticity_token", session => session("pre_auth_token").validate[String])
			.formParam("filename", "zzzz99.txt") 
			.formParam("new_filename", "zzzz99.txt") 
			//.formParam("filename", session => session("fileName").validate[String])
			//.formParam("new_filename", session => session("fileName").validate[String])
			.formParam("commit", session => session("sha").validate[String])
			.formParam("same_repo", "1")
			.formParam("pr", "")
			.formParam("content_changed", "true")
			.formParam("value", getRandomAlphaNumeric(40))
			.formParam("message", "${fileName}")
			.formParam("placeholder_message", "Update zzzz99.txt")
			.formParam("description", "Perf Test Description")
			.formParam("commit-choice", "direct")
			.formParam("target_branch", "master")
			.formParam("quick_pull", ""))

	setUp(scn.inject(atOnceUsers(10))).protocols(httpProtocol)
}
