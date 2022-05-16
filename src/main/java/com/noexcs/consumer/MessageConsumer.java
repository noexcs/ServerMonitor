package com.noexcs.consumer;

import com.noexcs.entity.Status;
import com.noexcs.service.StatusService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author noexcs
 */
@Component
public class MessageConsumer {
    @Autowired
    private StatusService statusService;
//    @KafkaListener(topics = "aaa")
    @KafkaListener(topics = "server-monitor")
    public void getMessage(ConsumerRecord record){
       // System.out.println("接收到的消息："+record.value()+record.offset());
        //获取消息对列中的消息，
        Object value = record.value();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            //讲消息转换成java对象
            Status status = objectMapper.readValue(value.toString(), Status.class);
            //存入mysql数据库中
            statusService.insert(status);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
