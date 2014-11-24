import sbt._
import Process._
import Keys._
import com.typesafe.sbt.SbtGit._

versionWithGit

git.baseVersion := "0.1"

lazy val root = (project in file(".")).
    settings(
        name := "hello",
        version := "1.0",
        scalaVersion := "2.11.4",
        libraryDependencies += "org.apache.derby" % "derby" % "10.4.1.3"
    )

val sampleStringTask = taskKey[String]("A sample string task")
val sampleIntTask = taskKey[Int]("A sample in task")
val sampleMinusTask = taskKey[Int]("Blah test")


sampleStringTask := System.getProperty("user.home")

sampleIntTask := {
    val sum = 1 + addMore(2, 10)
    println("sum: " + sum)
    sum
}

sampleMinusTask := {
    val diff = 4 - 2
    println("diff: " + diff)
    diff
}

def addMore(a: Int, b: Int) : Int = {
    var sum:Int = 0
    sum = a + b
    sum
}