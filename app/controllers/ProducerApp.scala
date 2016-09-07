package controllers

import controllers.{AsyncProducer, KafkaProducer}


class ProducerApp(message1: String) extends App {

	println("reaching producer app")
  val topic = "demo-topic"

  val producer = new AsyncProducer("localhost:6667")
  //val producer = new KafkaProducer("localhost:9092")

  val batchSize = 100

	
    println("Sqoop Started message just for understanding" + message1.length)
	producer.send(topic, message1)

    val message2="Sqoop Ended"
    println("Sqoop Ended message just for understaing " + message2.length)
	producer.send(topic, message2)
  

}