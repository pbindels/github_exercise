package test_scenario_1 

import scala.util.Random

object Headers {

    //val httpProtocol = http
     //   .baseURL("http://52.88.34.54")
     //   .inferHtmlResources()

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
}
