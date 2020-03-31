package formssi.logisticsserver.consumer;

import com.rabbitmq.client.Channel;
import formssi.logisticsserver.config.rabbit.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class FanoutReceiver {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.FANOUT_QUEUE1)
    public void processQueue1(@Payload String body,
                              Channel channel,
                              Message message) {
        logger.info("处理扇型消息Queue1：{}", body);
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.FANOUT_QUEUE2)
    public void processQueue2(@Payload String body,
                              Channel channel,
                              Message message) {
        logger.info("处理扇型消息Queue2：{}", body);
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}