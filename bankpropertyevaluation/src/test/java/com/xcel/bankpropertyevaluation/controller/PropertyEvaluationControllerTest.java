package com.xcel.bankpropertyevaluation.controller;

import com.xcel.bankpropertyevaluation.dto.GetAllPropertiesResponse;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = PropertyEvaluationController.class)
@AutoConfigureMockMvc
public class PropertyEvaluationControllerTest {

    private static final Logger log = LoggerFactory.getLogger(PropertyEvaluationControllerTest.class);
    @Autowired
    MockMvc mockMvc;
    @MockBean
    private PropertyEvaluationService propertyEvaluationService;

    String initiatorBusinessUnit = "Ishant96";
    String initiatorName = "Ishant";

    @Test
    public void getAllPropertiesEvaluation_No_PropertyEvaluation_Found() throws Exception {
        GetAllPropertiesResponse getAllPropertiesResponse = new GetAllPropertiesResponse();

        when(propertyEvaluationService.getAllPropertiesEvaluation(anyString(), anyString())).thenReturn(getAllPropertiesResponse);
        when(propertyEvaluationService.getAllPropertiesEvaluation(anyString(), anyString())).thenReturn(getAllPropertiesResponse);

        // Performing an HTTP GET request to get employees
        ResultActions response = mockMvc.perform(get("/api/property-evaluation/getAllProperties")
                .contentType(MediaType.APPLICATION_JSON)
                .param("initiatorName", initiatorName)
                .param("initiatorBusinessUnit", initiatorBusinessUnit));

        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.propertyEvaluation", CoreMatchers.is(CoreMatchers.nullValue())));
    }

    @Test
    public void getAllPropertiesEvaluation_Invalid_InitiatorBusinessUnit() throws Exception {
        GetAllPropertiesResponse getAllPropertiesResponse = new GetAllPropertiesResponse();
        when(propertyEvaluationService.getAllPropertiesEvaluation(anyString(), anyString())).thenReturn(getAllPropertiesResponse);

        // Performing an HTTP GET request to get employees
        ResultActions response = mockMvc.perform(get("/api/property-evaluation/getAllProperties")
                .contentType(MediaType.APPLICATION_JSON)
                .param("initiatorName", initiatorName)
                .param("initiatorBusinessUnit", "Invalid_Business_unit"));

        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.propertyEvaluation", CoreMatchers.is(CoreMatchers.nullValue())));
    }
}
