package com.quest.kafka.springbootkafkaconsumerexample.listener;

import java.util.Iterator;
import java.util.List;

import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.quest.kafka.springbootkafkaconsumerexample.model.BP;
import com.quest.kafka.springbootkafkaconsumerexample.model.HeartBeat;

public class BP_Publisher implements Runnable{

	
	List<BP>data;
	List<HeartBeat>data2;
	int patientId =0;
	SimpMessagingTemplate template;
	public BP_Publisher(List<BP>data,int patientId, SimpMessagingTemplate template) {
		this.data=data;
		this.patientId = patientId;
		this.template= template;
	}
	@Override
	public void run() {
		Iterator it = data.listIterator();
		while(it.hasNext()) {
		//template.convertAndSend("/topic/heartBeat", it.next());
   // teplate.convertAndSend("/topic/heartBeat", it.next())

        template.convertAndSend("/topic/bp", it.next());
        System.out.println("bp data sent");

        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        it.next();
        }		
	}

}
