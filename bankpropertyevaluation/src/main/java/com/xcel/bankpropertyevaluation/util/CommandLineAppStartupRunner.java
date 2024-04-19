package com.xcel.bankpropertyevaluation.util;

import com.xcel.bankpropertyevaluation.dto.AddInitiatorRequest;
import com.xcel.bankpropertyevaluation.service.InitiatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(CommandLineAppStartupRunner.class);
    @Autowired
    private InitiatorService initiatorService;

    @Override
    public void run(String... args) throws Exception {
        log.info("Application started with command-line arguments: {} . \n To kill this application, press Ctrl + C.", Arrays.toString(args));

        AddInitiatorRequest request = new AddInitiatorRequest();
        request.setInitiatorName("Ishant");
        request.setInitiatorBusinessUnit("Ishant96");
        request.setInitiatorContactNo(98765432);
        request.setPassword("I12345");
        var response = initiatorService.addInitiatorDetails(request);
        log.info("Response: {}", response);
    }
}