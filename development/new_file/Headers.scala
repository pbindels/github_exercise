package new_file

import scala.util.Random

object Headers {
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
