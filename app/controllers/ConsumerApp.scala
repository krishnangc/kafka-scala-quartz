package controllers

import controllers.KafkaConsumer
import controllers.SqoopSSH

class ConsumerApp extends App {


  val topic = "demo-topic"
  val groupId = "demo-topic-consumer"

  val consumer = new KafkaConsumer(topic, groupId, "localhost:2181")

  while (true) {
    consumer.read() match {
      case Some(message) =>
        println("Running sqoop.......................  " + message)
        println("executing scoop command uncomment the lines to really execute the project")
     /* val sqoopssh=new SqoopSSH()
      val status=  sqoopssh.sqoopexecute(message)*/
       
        // wait for 100 milli second for another read
        Thread.sleep(100)
      case None =>
        println("Queue is empty.......................  ")
        // wait for 2 second
        Thread.sleep(2 * 1000)
    }

  }

}
