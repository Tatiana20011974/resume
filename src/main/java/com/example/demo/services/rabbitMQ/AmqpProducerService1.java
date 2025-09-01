package com.example.demo.services.rabbitMQ;

import com.example.demo.config.RabbitConfig;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.ProjectDto;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AmqpProducerService1 {
    public final RabbitTemplate template1;

    public void sendMessage(ProjectDto projectDto){
        template1.convertAndSend(RabbitConfig.MAIL_QUEUE1, projectDto);
    }
}
