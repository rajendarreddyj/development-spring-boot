package com.rajendarreddyj.cloudkafkastreamexample;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface AnalyticsBinding {

    String PAGE_VIEW_OUT ="pvout";

    @Output(PAGE_VIEW_OUT)
    MessageChannel pageViewsOut();
}
