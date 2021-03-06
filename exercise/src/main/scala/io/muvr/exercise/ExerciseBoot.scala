package io.muvr.exercise

import akka.actor.{ActorSystem, ActorRef}
import akka.contrib.pattern.ClusterSharding
import spray.routing.Route

import scala.concurrent.ExecutionContext

case class ExerciseBoot private(exercise: ActorRef) {

  def route(implicit ec: ExecutionContext): Route = ExerciseService.route(exercise)

}

object ExerciseBoot {
  def boot(notification: ActorRef, profile: ActorRef)(implicit system: ActorSystem): ExerciseBoot = {
    val exerciseProcessor = ClusterSharding(system).start(
      typeName = UserExerciseProcessor.shardName,
      entryProps = Some(UserExerciseProcessor.props),
      idExtractor = UserExerciseProcessor.idExtractor,
      shardResolver = UserExerciseProcessor.shardResolver)
    ExerciseBoot(exerciseProcessor)
  }
}
