package controllers


import javax.inject.Inject

import akka.actor.{ActorSystem, Props}
import play.api.Logger
import play.api.mvc._
import services.kafka.consumer.{ConsumerActor, KafkaConsumer}
import services.kafka.producer.{KafkaProducer, ProducerActor, Start}
import com.typesafe.akka.extension.quartz.QuartzSchedulerExtension


class HomeController @Inject()(system: ActorSystem) extends Controller {

  val topic = "demo-topic"
  val groupId = "demo-topic-consumer"

  val consumer: KafkaConsumer = new KafkaConsumer(topic, groupId, "localhost:2181")

  val producer: KafkaProducer = new KafkaProducer("localhost:9092")


  def index = Action {

    Ok("Your new application is ready.")
  }


  def start = Action {
    Logger.info("Staring..........................")
   /* val producerActor = system.actorOf(Props(classOf[ProducerActor], producer))
    producerActor ! Start


    val cosumerActor = system.actorOf(Props(classOf[ConsumerActor], consumer))
    cosumerActor ! Start*/

    Ok("Producer & consumer have started")
  }
  
  def demonuno = Action(parse.json)
  { request =>
    var dbname = (request.body \ "dbname").as[String]
    var frequency = (request.body \ "frequency").as[String]
    var date = (request.body \ "date").as[String]
    var cmd = (request.body \ "cmd").as[String]
    var status = (request.body \ "status").as[String]
  
  
		val tocommand=Message("demo-topic",cmd)
		val producerActor = system.actorOf(Props(classOf[ProducerActor], producer))
        QuartzSchedulerExtension(system).createSchedule(dbname,None, "0 */1 * ? * *")
        QuartzSchedulerExtension (system).schedule(dbname,producerActor, tocommand)
    
	 val cosumerActor = system.actorOf(Props(classOf[ConsumerActor], consumer))
    cosumerActor ! Start
    Ok(dbname)
	
    
  }
  
}
case class Message(topic:String, cmd:String)




