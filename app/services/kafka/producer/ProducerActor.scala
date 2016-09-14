package services.kafka.producer

import akka.actor.Actor
import play.api.Logger

class ProducerActor(producer: KafkaProducer) extends Actor {

  
 //val topic = "demo-topic"
  
  def receive: Receive = {
    case Message(topic,cmd) =>
      producer.send(topic, cmd)

      Logger.info("Got message........................" + cmd)
     // startProducer()

  }

  def startProducer() = {
    Logger.info("Sending........................")
    
      Thread.sleep(1000)

    
  }


}


case object Start