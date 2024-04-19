package com.xcel.bankpropertyevaluation.service.impl;

import com.xcel.bankpropertyevaluation.dto.*;
import com.xcel.bankpropertyevaluation.model.Comments;
import com.xcel.bankpropertyevaluation.model.PropertyEvaluation;
import com.xcel.bankpropertyevaluation.repository.CommentRepository;
import com.xcel.bankpropertyevaluation.repository.PropertyEvaluationRepository;
import com.xcel.bankpropertyevaluation.service.PropertyEvaluationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class PropertyEvaluationServiceImpl implements PropertyEvaluationService {

    private static final Logger log = LoggerFactory.getLogger(PropertyEvaluationServiceImpl.class);
    @Autowired
    private PropertyEvaluationRepository propertyEvaluationRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private InitiatorServiceImpl initiatorService;
    @Autowired
    private Mapper mapper;

    @Override
    public AddPropertyEvaluationResponse addPropertyEvaluation(AddPropertyEvaluationRequest propertyEvaluationRequest, String initatorBusinessUnit) {
        try {
            log.info("PropertyEvaluationService >> createPropertyEvaluation initatorBusinessUnit: {}", initatorBusinessUnit);

            var initiatorDetails = initiatorService.getInitiatorByInitatorBusinessUnit(initatorBusinessUnit);
            log.info("PropertyEvaluationService >> initiatorDetails: {}", initiatorDetails);
            if (initiatorDetails != null) {

                var newEntity = createNewPropertyEntity(propertyEvaluationRequest, initiatorDetails);
                addCommentEntity(propertyEvaluationRequest, initiatorDetails);
                var dbResponse = propertyEvaluationRepository.save(newEntity);
                log.info("PropertyEvaluationService >> createPropertyEvaluation dbResponse: {}", dbResponse);

                AddPropertyEvaluationResponse response = new AddPropertyEvaluationResponse();
                response.setRefrenceNo(dbResponse.getRefrenceNo());
                response.setCreatedBy(dbResponse.getCreatedBy());
                response.setCreatedOn(dbResponse.getCreatedOn());

                return response;

            } else {
                return null;
            }

        } catch (Exception e) {
            log.error("Error: {}", e);
            throw e;
        }
    }

    @Override
    public GetAllPropertiesResponse getAllPropertiesEvaluation(String initatorName, String initatorBusinessUnit) {
        try {
            var initiatorDetails = initiatorService.getInitiatorByInitatorBusinessUnit(initatorBusinessUnit);
            if (initiatorDetails != null && initiatorDetails.getInitiatorBusinessUnit().equals(initatorBusinessUnit) && initiatorDetails.getInitiatorName().equals(initatorName)) {

                GetAllPropertiesResponse getAllPropertiesResponse = new GetAllPropertiesResponse();
                getAllPropertiesResponse.setPropertyEvaluation(propertyEvaluationRepository.findAll());
                return getAllPropertiesResponse;
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error("Error:", e);
            throw e;
        }
    }

    private PropertyEvaluation createNewPropertyEntity(AddPropertyEvaluationRequest propertyEvaluationRequest, GetInitiatorByIdResponse initiatorDetails) {
        PropertyEvaluation entity = mapper.mapToEntity(propertyEvaluationRequest);
        entity.setInitiatorDetails(mapper.mapToinitiatorDetails(initiatorDetails));
        entity.setRefrenceNo(UUID.randomUUID().toString());
        entity.setCreatedOn(Date.from(Instant.now()));
        entity.setReceivedOn(Date.from(Instant.now()));
        entity.setModifiedOn(Date.from(Instant.now()));
        entity.setCreatedBy(initiatorDetails.getInitiatorName());

        return entity;
    }

    private Comments addCommentEntity(AddPropertyEvaluationRequest propertyEvaluationRequest,
                                      GetInitiatorByIdResponse initiatorDetails) {

        Comments comment = new Comments();
        comment.setInitiatorBusinessUnit(initiatorDetails.getInitiatorBusinessUnit());
        comment.setDate(Date.from(Instant.now()));
        comment.setComment(propertyEvaluationRequest.getComment());

        return commentRepository.save(comment);
    }

}
