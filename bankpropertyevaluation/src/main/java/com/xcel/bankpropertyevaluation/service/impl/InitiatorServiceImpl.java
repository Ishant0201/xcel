package com.xcel.bankpropertyevaluation.service.impl;

import com.xcel.bankpropertyevaluation.dto.AddInitiatorRequest;
import com.xcel.bankpropertyevaluation.dto.AddInitiatorResponse;
import com.xcel.bankpropertyevaluation.dto.GetInitiatorByIdResponse;
import com.xcel.bankpropertyevaluation.dto.Mapper;
import com.xcel.bankpropertyevaluation.repository.InitiatorRepository;
import com.xcel.bankpropertyevaluation.service.InitiatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitiatorServiceImpl implements InitiatorService {

    private static final Logger log = LoggerFactory.getLogger(InitiatorServiceImpl.class);
    @Autowired
    private InitiatorRepository initiatorRepository;
    @Autowired
    private Mapper mapper;

    /**
     *
     */
    @Override
    public GetInitiatorByIdResponse getInitiatorByInitatorBusinessUnit(String initatorBusinessUnit) {
        log.info("InitiatorService >> getInitiatorByInitatorBusinessUnit initatorBusinessUnit: {}", initatorBusinessUnit);
        var dbRes = initiatorRepository.findByInitiatorBusinessUnit(initatorBusinessUnit);
        if (dbRes != null) {
            return mapper.mapToResponse(initiatorRepository.findByInitiatorBusinessUnit(initatorBusinessUnit));
        } else {
            // If initiator is not found, return a null response
            return null;
        }
    }

    /**
     * @param initiatorDetails
     * @return
     */
    @Override
    public AddInitiatorResponse addInitiatorDetails(AddInitiatorRequest initiatorDetails) {
        log.info("InitiatorService >> addInitiatorDetails");
        var dbRes = initiatorRepository.save(mapper.mapToinitiator(initiatorDetails));

        AddInitiatorResponse response = new AddInitiatorResponse();
        response.setInitiatorName(dbRes.getInitiatorName());
        response.setMessage("Initiator added successfully.");
        return response;
    }

}
