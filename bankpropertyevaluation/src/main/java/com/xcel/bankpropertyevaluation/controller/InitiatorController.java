package com.xcel.bankpropertyevaluation.controller;

import com.xcel.bankpropertyevaluation.dto.AddInitiatorRequest;
import com.xcel.bankpropertyevaluation.dto.AddInitiatorResponse;
import com.xcel.bankpropertyevaluation.dto.GetInitiatorByIdResponse;
import com.xcel.bankpropertyevaluation.service.InitiatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/initiator")
public class InitiatorController {

    @Autowired
    private InitiatorService initiatorService;

    @GetMapping(value = "/getInitiatorByName", produces = MediaType.APPLICATION_JSON_VALUE)
    public GetInitiatorByIdResponse getInitiatorByInitatorBusinessUnit(@RequestParam String initatorBusinessUnit) {
        return initiatorService.getInitiatorByInitatorBusinessUnit(initatorBusinessUnit);
    }

    @PostMapping("/addInitiator")
    public AddInitiatorResponse addInitiatorDetails(@RequestBody AddInitiatorRequest initiatorDetails) {
        return initiatorService.addInitiatorDetails(initiatorDetails);
    }


}
