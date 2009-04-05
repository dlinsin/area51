package de.linsin.sample.spring.integration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.channel.PollableChannel;
import org.springframework.integration.core.Message;
import org.springframework.integration.core.MessageChannel;
import org.springframework.integration.message.StringMessage;

/**
 * Bootstrapping the example see http://blog.springsource.com/2009/02/13/982/
 *
 * @author David Linsin
 * @version 0.1
 * @since 0.1
 */
public class Bootstrap {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//        toOutputChannel(ctx);
//        toFile(ctx);
        toFileWithGateway(ctx);
    }

    private static void toFileWithGateway(ApplicationContext argCtx) {
        Gateway gateway = (Gateway) argCtx.getBean("gateway");
        gateway.send("Spring Integration rocks");
    }

    private static void toFile(ApplicationContext argCtx) {
        MessageChannel input = (MessageChannel) argCtx.getBean("input");
        input.send(new StringMessage("Spring Integration rocks"));
    }

    private static void toOutputChannel(ApplicationContext argCtx) {
        MessageChannel input = (MessageChannel) argCtx.getBean("input");
        PollableChannel output = (PollableChannel) argCtx.getBean("output");
        input.send(new StringMessage("Spring Integration rocks"));
        Message<?> reply = output.receive();
        System.out.println("received: " + reply);
    }
}
