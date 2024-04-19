package com.xcel.bankpropertyevaluation.controller;

import com.xcel.bankpropertyevaluation.dto.AddInitiatorRequest;
import com.xcel.bankpropertyevaluation.dto.AddInitiatorResponse;
import com.xcel.bankpropertyevaluation.dto.GetInitiatorByIdResponse;
import com.xcel.bankpropertyevaluation.service.InitiatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/initiator")
public class InitiatorController {

    @Autowired
    private InitiatorService initiatorService;

    @GetMapping(value = "/getInitiatorByName", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetInitiatorByIdResponse> getInitiatorByInitatorBusinessUnit(@RequestParam String initatorBusinessUnit) {
        GetInitiatorByIdResponse response = initiatorService.getInitiatorByInitatorBusinessUnit(initatorBusinessUnit);
        if (response != null && response.getInitiatorBusinessUnit() != null && !response.getInitiatorName().isEmpty()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GetInitiatorByIdResponse());
        }
    }

    @PostMapping("/addInitiator")
    public AddInitiatorResponse addInitiatorDetails(@RequestBody AddInitiatorRequest initiatorDetails) {
        return initiatorService.addInitiatorDetails(initiatorDetails);
    }


}
