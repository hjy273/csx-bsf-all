package com.yh.csx.bsf.mq.base;

import com.yh.csx.bsf.core.util.LogUtils;
import com.yh.csx.bsf.mq.MQProperties;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;

/**
 * @author:
 * @version: 2019-06-12 12:54
 * 消费者抽象
 **/
public abstract class AbstractConsumer extends AbstractMQ  {
    @Override
    public void close(){
        try {
            LogUtils.debug(AbstractConsumer.class, MQProperties.Project,"MQ消费者准备释放资源...");
            Object obj = getObject();
            if (obj != null) {
                if (obj instanceof DefaultMQPushConsumer) {
                    DefaultMQPushConsumer consumer = ((DefaultMQPushConsumer) obj);
                    LogUtils.info(AbstractConsumer.class,MQProperties.Project,String.format("rocketmq 消费者:%s 释放资源完毕", consumer.getConsumerGroup()));
                    consumer.shutdown();
                    obj = null;
                }
            }
            innerClose(obj);
        }catch (Exception exp)
        {
            LogUtils.warn(AbstractConsumer.class,MQProperties.Project,"MQ消费者资源释放异常",exp);
        }

    }
}
