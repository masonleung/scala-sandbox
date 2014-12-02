import sbt._
import Process._
import Keys._
import sbtrelease._
import ReleaseStateTransformations._

releaseSettings

ReleaseKeys.releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,              // : ReleaseStep
  inquireVersions,                        // : ReleaseStep
  runTest,                                // : ReleaseStep
  setReleaseVersion,                      // : ReleaseStep
  commitReleaseVersion,                   // : ReleaseStep, performs the initial git checks
  tagRelease,                             // : ReleaseStep
  publishArtifacts,                       // : ReleaseStep, checks whether `publishTo` is properly set up
  setNextVersion,                         // : ReleaseStep
  commitNextVersion,                      // : ReleaseStep
  pushChanges                             // : ReleaseStep, also checks that an upstream branch is properly configured
)

credentials += Credentials(sys.env("MAVEN_REALM"),sys.env("MAVEN_HOST"),sys.env("MAVEN_USER"),sys.env("MAVEN_PASSWORD"))

publishTo <<= version { (v: String) =>
  val artifactory = "http://sharethrough.artifactoryonline.com/sharethrough/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some(Resolver.url("libs-snapshots-local", new URL(artifactory + "libs-snapshots-local/")))
  else
    Some(Resolver.url("libs-releases-local", new URL(artifactory + "libs-releases-local/")))
}

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
