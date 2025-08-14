package com.rookies4.myspringbootlab.runner;

import com.rookies4.myspringbootlab.property.MyPropProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyPropRunner implements ApplicationRunner {

    @Value("${myprop.username}")
    private String username;

    @Value("${myprop.port}")
    private int port;

    private final MyPropProperties myPropProperties;

    public MyPropRunner(MyPropProperties myPropProperties) {
        this.myPropProperties = myPropProperties;
    }

    //Logger 객체 생성
    private Logger logger = LoggerFactory.getLogger(MyPropRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.debug("username = " + myPropProperties.getUsername());
        logger.info("port = " + myPropProperties.getPort());

    }
}
