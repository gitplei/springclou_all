package com.pl.springcloud.service.impl;

import com.pl.springcloud.service.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;


import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author plei
 * @date 2020/4/5
 */
@EnableBinding(Source.class)//定义消息推送管道
public class IMessageProviderImpl implements IMessageProvider {

    @Resource
    private MessageChannel output; //消息发送管道

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("============= serial " + serial);
        return null;
    }


}
