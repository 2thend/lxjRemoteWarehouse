package com.lxj.amqp;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//自动配置
//1.RabbitAutoConfiguration
//2.有自动配置了连接工厂的ConnectionFactory
//3.RabbitProperties封装了RabbitMQ配置
//4.RabbitTemplate:给RabbitMQ发送和接受消息
//5.AmqbAdmin:RabbitMQ系统管理功能组件
//6.@EnableRabbit: @RabbitListener监听消息队列的内容

@EnableRabbit  //开启基于注解的RabbitMQ模式
@SpringBootApplication
public class Springboot08AmqpApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot08AmqpApplication.class, args);
	}
}
