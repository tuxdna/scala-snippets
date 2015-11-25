package examples.part06

import akka.actor.{ActorLogging, Props, ActorSystem, Actor}

class StudentActor extends Actor with ActorLogging {
  def receive = {
    case "hello" =>
  }
}

object Request

case class Response(x: Any)

class TeacherActor extends Actor with ActorLogging {
  def receive = {
    case x => sender ! Response(x)
  }
}

class TeacherSupervisorActor extends Actor with ActorLogging {
  val teacherActorRef = context.actorOf(Props[TeacherActor], "teacher1")
  println(teacherActorRef.path)

  def receive = {
    case x => teacherActorRef ! x
  }
}

object ChildAndPathApp {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("MyActorSystem")
    val sampleActor1Ref = system.actorOf(Props[StudentActor])
    println(sampleActor1Ref.path)
    val sampleActor2Ref = system.actorOf(Props[StudentActor], "sample2")
    println(sampleActor2Ref.path)

    val teacherSupervisorRef = system.actorOf(Props[TeacherSupervisorActor], "teacherSupervisor")
    println(teacherSupervisorRef.path)
  }
}
