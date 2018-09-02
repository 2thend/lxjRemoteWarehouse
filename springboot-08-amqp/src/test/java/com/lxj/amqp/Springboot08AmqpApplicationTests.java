package com.lxj.amqp;

import com.lxj.amqp.bean.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot08AmqpApplicationTests {


	@Autowired
	RabbitTemplate rabbitTemplate;


	//发送数据
	@Test
	public void send() {
		//message需要自己构造一个，定义消息体的内容和消息头
		//Object默认当成消息体，只需要传入要发送的对象，自动序列化发送给rabbitmq
		Map<String,Object> map = new HashMap<>();
		map.put("msg","这是一个message");
		map.put("data", Arrays.asList("helloworld",123,false));
		//对象被默认序列化后发送出去
		rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",new Book("西游记","五成儿女"));
	}

	//接受数据:如何将数据自动的转为json发送出去
	@Test
	public void receive(){
		Object convert = rabbitTemplate.receiveAndConvert("atguigu.news");
		System.out.println(convert.getClass());
		System.out.println(convert);

	}


	@Test
	public void testFanout(){
		rabbitTemplate.convertAndSend("exchange.fanout","",new Book("红楼梦","曹雪芹"));
		rabbitTemplate.convertAndSend("exchange.fanout","",new Book("aaa","留心间"));
	}


	@Autowired
	AmqpAdmin amqpAdmin;

	@Test
	public void createExchange(){
	   amqpAdmin.declareExchange(new DirectExchange("test.exchange"));
	   amqpAdmin.declareQueue(new Queue("test.queue",true));

	   amqpAdmin.declareBinding(new Binding("test.queue",Binding.DestinationType.QUEUE,"test.exchange","route.haha",null));

	}




}
