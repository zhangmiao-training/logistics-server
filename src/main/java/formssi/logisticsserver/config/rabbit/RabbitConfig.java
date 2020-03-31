package formssi.logisticsserver.config.rabbit;

public class RabbitConfig {

    //---------------直连交换机---------------
    public static final String ORDER_QUEUE = "order.queue";
    public static final String ORDER_EXCHAGE = "order.exchage";
    public static final String ORDER_ROUTE = "order.route";

    //---------------扇型交换机---------------
    public static final String FANOUT_QUEUE1 = "fanout.queue1";
    public static final String FANOUT_QUEUE2 = "fanout.queue2";
    public static final String FANOUT_EXCHAGE = "fanout.exchage";

    //---------------主题交换机---------------
    public static final String TOPIC_QUEUE1 = "topic.queue1";
    public static final String TOPIC_QUEUE2 = "topic.queue2";
    public static final String TOPIC_QUEUE3 = "topic.queue3";
    public static final String TOPIC_EXCHAGE = "topic.exchage";

}