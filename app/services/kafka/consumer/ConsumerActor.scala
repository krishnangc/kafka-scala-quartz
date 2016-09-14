package services.kafka.consumer

import akka.actor.Actor
import play.api.Logger
import services.kafka.producer.Start


class ConsumerActor(consumer: KafkaConsumer) extends Actor {
  def receive: Receive = {
    case Start =>
      Logger.info("Read  message from kafka")
        startConsumer()
  }

  def startConsumer() = {

    while (true) {
      consumer.read() match {
        case Some(message) =>
          Logger.info("Getting message.......................  " + message)
          // wait for 100 milli second for another read
          Thread.sleep(100)
        case None =>
          Logger.info("Queue is empty.......................  ")
          // wait for 2 second
          Thread.sleep(2 * 1000)
      }
    }
  }
}