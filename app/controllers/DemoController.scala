package controllers


import com.typesafe.akka.extension.quartz.QuartzSchedulerExtension
import akka.actor.Actor
import akka.actor.ActorLogging

import akka.actor.Props
import java.util.Calendar
import play.api.Logger
import play.api.mvc.Controller


import javax.inject._
import java.util.Properties
import play.api._
import play.api.libs.EventSource
import play.api.libs.json._
import play.api.mvc._
import akka.NotUsed
import akka.actor.ActorSystem
import akka.kafka.ProducerSettings
import akka.stream.scaladsl.{Source, _}
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.kafka.common.serialization.{ByteArraySerializer, StringSerializer}
import play.api.libs.EventSource.Event

import scala.concurrent.ExecutionContext

//import scala.concurrent.duration._
//import services.KafkaStreamer

import akka.kafka.ConsumerSettings
import akka.kafka.scaladsl._
import akka.kafka.scaladsl.Consumer.Control


import akka.stream.ActorMaterializer
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.ByteArrayDeserializer
import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord}


class DemoController extends Controller {

  def demo(dbname:String,frequency:String,startDate:String,cmd:String,status:String) = Action {
    
    println(frequency)
    val System = ActorSystem(dbname)
    val  Actor  =   System.actorOf(Props(classOf[FirstActor]))
    
    QuartzSchedulerExtension(System).createSchedule(dbname,None, "0 */1 * ? * *")
    QuartzSchedulerExtension (System).schedule(dbname,Actor, cmd)
    
    Ok(cmd)
	Ok(dbname)
	 
   
  }

  //This definition will handle the call with a JSON object
  def demonuno = Action(parse.json)
  { request =>
    val dbname = (request.body \ "dbname").as[String]
    val frequency = (request.body \ "frequency").as[String]
    val date = (request.body \ "date").as[String]
    val cmd = (request.body \ "cmd").as[String]
    val status = (request.body \ "status").as[String]
  
    val System = ActorSystem(dbname)
      val  Actor  =   System.actorOf(Props(classOf[FirstActor]))
    
        QuartzSchedulerExtension(System).createSchedule(dbname,None, "0 */1 * ? * *")
        QuartzSchedulerExtension (System).schedule(dbname,Actor, cmd)
    
    Ok(dbname)
    
  }

}




class FirstActor extends Actor with App with ActorLogging{

 override def receive: Receive = {
         case  msg :  String  =>  println ( msg ) 
         
         val topic = "demo-topic"
		 
		 val  props = new Properties()
 props.put("bootstrap.servers", "localhost:6667")
  
 props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
 props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

 val producer = new KafkaProducer[String, String](props)
   
 //val TOPIC="test"
 
 for(i<- 1 to 50){
  val record = new ProducerRecord(topic, "key", s"hello $i")
  producer.send(record)
 }
    
 val record = new ProducerRecord(topic, "key", "the end "+new java.util.Date)
 producer.send(record)

 /* val producer = new AsyncProducer("localhost:6667")
  //val producer = new KafkaProducer("localhost:9092")

  val batchSize = 100

	
    println("Sqoop Started message just for understanding" + msg)
	producer.send(topic, msg)
	println("sqoop send"+msg)*/
    
	/*val groupId = "demo-topic-consumer"
	val consumer = new KafkaConsumer(topic, groupId, "centos6.qubida.io:2181")

	
    consumer.read() match {
      case Some(msg) =>
        println("Running sqoop.......................  " + msg)
        println("executing scoop command uncomment the lines to really execute the project")
     /* val sqoopssh=new SqoopSSH()
      val status=  sqoopssh.sqoopexecute(message)*/
       
        
      case None =>
        println("Queue is empty.......................  ")
        
       
    }
*/
  
         
  }
  




}
