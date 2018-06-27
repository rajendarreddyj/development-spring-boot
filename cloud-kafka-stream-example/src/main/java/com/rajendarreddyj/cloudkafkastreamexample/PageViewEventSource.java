package com.rajendarreddyj.cloudkafkastreamexample;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
    public class PageViewEventSource implements ApplicationRunner {

    private final MessageChannel pageViewsOut;
    private final Log log = LogFactory.getLog(PageViewEventSource.class);
    public PageViewEventSource(AnalyticsBinding binding){
        this.pageViewsOut = binding.pageViewsOut();
    }

    @Override
    public void run(ApplicationArguments args) {
        List<String> names = Arrays.asList("test1","test2", "test3","test4", "test5");
        List<String> pages = Arrays.asList("google","yahoo","live","duckduckgo","altavista");

        Runnable runnable =() -> {
          String rPage = pages.get(new Random().nextInt(pages.size()));
          String rName = names.get(new Random().nextInt(names.size()));

          PageViewEvent pageViewEvent = new PageViewEvent(rName, rPage, Math.random() >.5 ?100:1000);

          Message<PageViewEvent> message = MessageBuilder
                    .withPayload(pageViewEvent)
                    .setHeader(KafkaHeaders.MESSAGE_KEY, pageViewEvent.getUserId().getBytes())
                    .build();
          try {
              this.pageViewsOut.send(message);
              log.info("sent " +message.toString());
          }
          catch(Exception e){
              log.error(e);
          }
        };
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(runnable, 1,1, TimeUnit.SECONDS);
    }
}
