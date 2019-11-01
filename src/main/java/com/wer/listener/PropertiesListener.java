package com.wer.listener;

import com.wer.common.GlobalConstant;
import com.wer.util.PropertiesListenerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class PropertiesListener implements ApplicationListener<ApplicationStartedEvent> {

    private Logger logger = LoggerFactory.getLogger(PropertiesListener.class);
    private String propertyFileName;

    public PropertiesListener() {
        this.propertyFileName = GlobalConstant.BASE_FILE_NAMES;
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        logger.info(">>>>>>>>>>>>>>加载需要加载的Properties  Begin>>>>>>>>>>");
        PropertiesListenerConfig.loadAllProperties(propertyFileName);
        logger.info(">>>>>>>>>>>>>>加载需要加载的Properties  End>>>>>>>>>>");
    }
}
