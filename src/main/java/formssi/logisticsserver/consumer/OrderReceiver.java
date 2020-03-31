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

import java.util.Map;

@Component
public class OrderReceiver {

    public Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitHandler
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitConfig.ORDER_QUEUE),
            exchange = @Exchange(value = RabbitConfig.ORDER_EXCHAGE),
            key = RabbitConfig.ORDER_ROUTE
    ))
    public void receiveObject(@Payload Map<String, Object> order,
                              Channel channel,
                              Message message) {
        try {
            //消息的标识，false只确认当前一个消息收到，true确认所有consumer获得的消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

            //ack返回false，并重新回到队列
//            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);

            //拒绝消息
//            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);

            logger.info("消息处理成功：{}", order.get("orderId"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}