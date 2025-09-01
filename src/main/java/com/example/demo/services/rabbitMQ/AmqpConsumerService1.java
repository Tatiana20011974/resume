package com.example.demo.services.rabbitMQ;

import com.example.demo.config.RabbitConfig;
import com.example.demo.dto.ProjectDto;
import com.example.demo.services.FileService;
import com.example.demo.services.MailSenderService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AmqpConsumerService1 {
    MailSenderService mailSender1;
    FileService fileService1;

    @RabbitListener(queues = RabbitConfig.MAIL_QUEUE1)
    public void receiveMessage(ProjectDto projectDto){
        System.out.println("******"+projectDto.toString());
    }
}
