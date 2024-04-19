package com.xcel.bankpropertyevaluation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xcel.bankpropertyevaluation.dto.AddPropertyEvaluationRequest;
import com.xcel.bankpropertyevaluation.dto.AddPropertyEvaluationResponse;
import com.xcel.bankpropertyevaluation.dto.GetAllPropertiesResponse;
import com.xcel.bankpropertyevaluation.enums.Currency;
import com.xcel.bankpropertyevaluation.enums.Purpose;
import com.xcel.bankpropertyevaluation.enums.TypeOfEvaluation;
import com.xcel.bankpropertyevaluation.model.*;
import com.xcel.bankpropertyevaluation.service.PropertyEvaluationService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PropertyEvaluationController.class)
@AutoConfigureMockMvc
public class PropertyEvaluationControllerTest {

    private static final Logger log = LoggerFactory.getLogger(PropertyEvaluationControllerTest.class);
    String initiatorBusinessUnit = "Ishant96";
    String initiatorName = "Ishant";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private PropertyEvaluationService propertyEvaluationService;

    @Test
    public void getAllPropertiesEvaluation_No_PropertyEvaluation_Found() throws Exception {
        GetAllPropertiesResponse getAllPropertiesResponse = new GetAllPropertiesResponse();

        when(propertyEvaluationService.getAllPropertiesEvaluation(initiatorName, initiatorBusinessUnit)).thenReturn(getAllPropertiesResponse);

        mockMvc.perform(get("/api/property-evaluation/getAllProperties")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .queryParam("initiatorName", initiatorName)
                        .queryParam("initiatorBusinessUnit", initiatorBusinessUnit))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.propertyEvaluation", CoreMatchers.is(CoreMatchers.nullValue())))
                .andDo(print());

    }

    @Test
    public void getAllPropertiesEvaluation_Invalid_InitiatorBusinessUnit() throws Exception {
        GetAllPropertiesResponse getAllPropertiesResponse = new GetAllPropertiesResponse();

        when(propertyEvaluationService.getAllPropertiesEvaluation("Invalid_name", "Invalid_BusinessUnit")).thenReturn(getAllPropertiesResponse);

        mockMvc.perform(get("/api/property-evaluation/getAllProperties")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .queryParam("initiatorName", initiatorName)
                        .queryParam("initiatorBusinessUnit", initiatorBusinessUnit))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.propertyEvaluation", CoreMatchers.is(CoreMatchers.nullValue())))
                .andDo(print());
    }

    @Test
    public void addNewPropertyEvaluationRequest_Created() throws Exception {
        AddPropertyEvaluationRequest propertyEvaluationRequest = createNewPropertySampleResponse();

        AddPropertyEvaluationResponse addPropertyEvaluationResponse = new AddPropertyEvaluationResponse();
        addPropertyEvaluationResponse.setCreatedBy(initiatorName);
        addPropertyEvaluationResponse.setCreatedOn(Date.from(Instant.now()));
        addPropertyEvaluationResponse.setRefrenceNo(UUID.randomUUID().toString());

        when(propertyEvaluationService.addPropertyEvaluation(propertyEvaluationRequest, initiatorBusinessUnit)).thenReturn(addPropertyEvaluationResponse);

        mockMvc.perform(post("/api/property-evaluation/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(propertyEvaluationRequest))
                        .queryParam("initatorBusinessUnit", initiatorBusinessUnit))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.createdBy").value(initiatorName))
                .andExpect(jsonPath("$.createdOn", CoreMatchers.is(CoreMatchers.notNullValue())))
                .andExpect(jsonPath("$.refrenceNo", CoreMatchers.is(CoreMatchers.notNullValue())))
                .andDo(print());

    }

    @Test
    public void getAllProperties_ByBusinessUnit_FoundOne() throws Exception {
        AddPropertyEvaluationRequest propertyEvaluationRequest = createNewPropertySampleResponse();

        AddPropertyEvaluationResponse addPropertyEvaluationResponse = new AddPropertyEvaluationResponse();
        addPropertyEvaluationResponse.setCreatedBy(initiatorName);
        addPropertyEvaluationResponse.setCreatedOn(Date.from(Instant.now()));
        addPropertyEvaluationResponse.setRefrenceNo(UUID.randomUUID().toString());

        when(propertyEvaluationService.addPropertyEvaluation(propertyEvaluationRequest, initiatorBusinessUnit)).thenReturn(addPropertyEvaluationResponse);

        mockMvc.perform(post("/api/property-evaluation/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(propertyEvaluationRequest))
                        .queryParam("initatorBusinessUnit", initiatorBusinessUnit))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.createdBy").value(initiatorName))
                .andExpect(jsonPath("$.createdOn", CoreMatchers.is(CoreMatchers.notNullValue())))
                .andExpect(jsonPath("$.refrenceNo", CoreMatchers.is(CoreMatchers.notNullValue())))
                .andDo(print());


        GetAllPropertiesResponse getAllPropertiesResponse = createGetAllPropertyResponseSample();
        when(propertyEvaluationService.getAllPropertiesEvaluation(initiatorName, initiatorBusinessUnit)).thenReturn(getAllPropertiesResponse);

        mockMvc.perform(get("/api/property-evaluation/getAllProperties")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .queryParam("initiatorName", initiatorName)
                        .queryParam("initiatorBusinessUnit", initiatorBusinessUnit))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.propertyEvaluation").isArray())
                .andExpect(jsonPath("$.propertyEvaluation[0].propertyValuation.typeOfEvaluation").value("New"))
                .andExpect(jsonPath("$.propertyEvaluation[0].propertyValuation.fosReference").value("FosRef"))
                .andExpect(jsonPath("$.propertyEvaluation[0].facilityDetails.amount").value(1234556))
                .andExpect(jsonPath("$.propertyEvaluation[0].facilityDetails.category").value("25000-Apartment"))
                .andExpect(jsonPath("$.propertyEvaluation[0].facilityDetails.currency").value("GBP"))
                .andExpect(jsonPath("$.propertyEvaluation[0].facilityDetails.typeOfFacility").value("Reparation"))
                .andExpect(jsonPath("$.propertyEvaluation[0].facilityDetails.purpose").value("Construction"))
                .andExpect(jsonPath("$.propertyEvaluation[0].facilityDetails.termInMonths").value(300))
                .andExpect(jsonPath("$.propertyEvaluation[0].borrowerDetails.mainBorrower.address").value("address"))
                .andExpect(jsonPath("$.propertyEvaluation[0].borrowerDetails.mainBorrower.email").value("ASD@sff"))
                .andExpect(jsonPath("$.propertyEvaluation[0].borrowerDetails.mainBorrower.contactNumber").value(12345667))
                .andExpect(jsonPath("$.propertyEvaluation[0].borrowerDetails.mainBorrower.customerName").value("Ishant"))
                .andExpect(jsonPath("$.propertyEvaluation[0].borrowerDetails.mainBorrower.customerNumber").value("qw123"))
                .andExpect(jsonPath("$.propertyEvaluation[0].borrowerDetails.jointBorrowers.address").value("address"))
                .andExpect(jsonPath("$.propertyEvaluation[0].borrowerDetails.jointBorrowers.email").value("ASD@sff"))
                .andExpect(jsonPath("$.propertyEvaluation[0].borrowerDetails.jointBorrowers.contactNumber").value(12345667))
                .andExpect(jsonPath("$.propertyEvaluation[0].borrowerDetails.jointBorrowers.customerName").value("Ishant"))
                .andExpect(jsonPath("$.propertyEvaluation[0].borrowerDetails.jointBorrowers.customerNumber").value("qw123"))
                .andDo(print());
    }

    private AddPropertyEvaluationRequest createNewPropertySampleResponse() {

        FacilityDetails facilityDetails = new FacilityDetails();
        facilityDetails.setAmount(1234556);
        facilityDetails.setCategory("25000-Apartment");
        facilityDetails.setCurrency(Currency.GBP);
        facilityDetails.setTypeOfFacility("Reparation");
        facilityDetails.setPurpose(Purpose.Construction);
        facilityDetails.setTermInMonths(300);

        PropertyValuation propertyValuation = new PropertyValuation();
        propertyValuation.setTypeOfEvaluation(TypeOfEvaluation.New);
        propertyValuation.setFosReference("FosRef");

        Borrower mainBorrower = new Borrower();
        mainBorrower.setAddress("address");
        mainBorrower.setEmail("ASD@sff");
        mainBorrower.setContactNumber(12345667L);
        mainBorrower.setCustomerName("Ishant");
        mainBorrower.setCustomerNumber("qw123");

        Borrower jointBorrower = new Borrower();
        jointBorrower.setEmail("ASD@sff");
        jointBorrower.setAddress("address");
        jointBorrower.setContactNumber(12345667L);
        jointBorrower.setCustomerName("Ishant");
        jointBorrower.setCustomerNumber("qw123");

        BorrowerDetails borrowerDetails = new BorrowerDetails();
        borrowerDetails.setMainBorrower(mainBorrower);
        borrowerDetails.setJointBorrowers(jointBorrower);

        AddPropertyEvaluationRequest addPropertyEvaluationRequest = new AddPropertyEvaluationRequest();
        addPropertyEvaluationRequest.setPropertyValuation(propertyValuation);
        addPropertyEvaluationRequest.setFacilityDetails(facilityDetails);
        addPropertyEvaluationRequest.setBorrowerDetails(borrowerDetails);
        addPropertyEvaluationRequest.setComment("HAppu comment");

        return addPropertyEvaluationRequest;
    }

    private GetAllPropertiesResponse createGetAllPropertyResponseSample() {

        FacilityDetails facilityDetails = new FacilityDetails();
        facilityDetails.setAmount(1234556);
        facilityDetails.setCategory("25000-Apartment");
        facilityDetails.setCurrency(Currency.GBP);
        facilityDetails.setTypeOfFacility("Reparation");
        facilityDetails.setPurpose(Purpose.Construction);
        facilityDetails.setTermInMonths(300);

        PropertyValuation propertyValuation = new PropertyValuation();
        propertyValuation.setTypeOfEvaluation(TypeOfEvaluation.New);
        propertyValuation.setFosReference("FosRef");

        Borrower mainBorrower = new Borrower();
        mainBorrower.setAddress("address");
        mainBorrower.setEmail("ASD@sff");
        mainBorrower.setContactNumber(12345667L);
        mainBorrower.setCustomerName("Ishant");
        mainBorrower.setCustomerNumber("qw123");

        Borrower jointBorrower = new Borrower();
        jointBorrower.setEmail("ASD@sff");
        jointBorrower.setAddress("address");
        jointBorrower.setContactNumber(12345667L);
        jointBorrower.setCustomerName("Ishant");
        jointBorrower.setCustomerNumber("qw123");

        BorrowerDetails borrowerDetails = new BorrowerDetails();
        borrowerDetails.setMainBorrower(mainBorrower);
        borrowerDetails.setJointBorrowers(jointBorrower);

        PropertyEvaluation propertyEvaluation = new PropertyEvaluation();
        propertyEvaluation.setPropertyValuation(propertyValuation);
        propertyEvaluation.setFacilityDetails(facilityDetails);
        propertyEvaluation.setBorrowerDetails(borrowerDetails);
        propertyEvaluation.setCreatedBy(initiatorName);
        propertyEvaluation.setCreatedOn(Date.from(Instant.now()));
        propertyEvaluation.setId(1L);
        propertyEvaluation.setModifiedOn(Date.from(Instant.now()));
        propertyEvaluation.setReceivedOn(Date.from(Instant.now()));
        propertyEvaluation.setRefrenceNo(UUID.randomUUID().toString());

        GetAllPropertiesResponse getAllPropertiesResponse = new GetAllPropertiesResponse();
        getAllPropertiesResponse.setPropertyEvaluation(List.of(propertyEvaluation));

        return getAllPropertiesResponse;
    }
}
