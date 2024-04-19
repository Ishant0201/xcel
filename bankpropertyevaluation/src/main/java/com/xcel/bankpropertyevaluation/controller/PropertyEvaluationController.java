package com.xcel.bankpropertyevaluation.controller;

import com.xcel.bankpropertyevaluation.dto.AddPropertyEvaluationRequest;
import com.xcel.bankpropertyevaluation.dto.AddPropertyEvaluationResponse;
import com.xcel.bankpropertyevaluation.dto.GetAllPropertiesResponse;
import com.xcel.bankpropertyevaluation.service.PropertyEvaluationService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/property-evaluation")
public class PropertyEvaluationController {

    private static final Logger log = LoggerFactory.getLogger(PropertyEvaluationController.class);
    @Autowired
    private PropertyEvaluationService propertyEvaluationService;

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddPropertyEvaluationResponse> addPropertyEvaluation(@RequestBody @Valid AddPropertyEvaluationRequest propertyEvaluationRequest, @RequestParam String initatorBusinessUnit) {
        try {
            log.info("Controller >> addPropertyEvaluation initiatorBusinessUnit:{}", initatorBusinessUnit);
            AddPropertyEvaluationResponse response = propertyEvaluationService.addPropertyEvaluation(propertyEvaluationRequest, initatorBusinessUnit);
            if (response != null && response.getRefrenceNo() != null && !response.getRefrenceNo().isEmpty()) {
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AddPropertyEvaluationResponse());
            }
        } catch (Exception e) {
            log.error("Error: {}", e);
            throw e;
        }
    }

    @CrossOrigin
    @GetMapping(value = "/getAllProperties", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetAllPropertiesResponse> getAllPropertiesEvaluation(@RequestParam String initiatorName,
                                                                               @RequestParam String initiatorBusinessUnit) {
        log.info("Controller >> getPropertyEvaluation initiatorBusinessUnit:{}", initiatorBusinessUnit);
        GetAllPropertiesResponse response = propertyEvaluationService.getAllPropertiesEvaluation(initiatorName, initiatorBusinessUnit);
        if (response != null && response.getPropertyEvaluation() != null && !response.getPropertyEvaluation().isEmpty()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GetAllPropertiesResponse());
        }
    }

}
