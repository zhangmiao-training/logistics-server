package formssi.logisticsserver.consumer;

import com.rabbitmq.client.Channel;
import formssi.logisticsserver.config.rabbit.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class TopicReceiver {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitHandler
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = RabbitConfig.TOPIC_QUEUE1),
                    exchange = @Exchange(value = RabbitConfig.TOPIC_EXCHAGE, type = ExchangeTypes.TOPIC),
                    key = "direct.route"
            )
    )
    public void processQueue1(@Payload String body,
                              Channel channel,
                              Message message) {
        logger.info("处理主题消息Queue1：{}", body);
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.TOPIC_QUEUE2)
    public void processQueue2(@Payload String body,
                              Channel channel,
                              Message message) {
        logger.info("处理主题消息Queue2：{}", body);
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.TOPIC_QUEUE3)
    public void processQueue3(@Payload String body,
                              Channel channel,
                              Message message) {
        logger.info("处理主题消息Queue3：{}", body);
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}