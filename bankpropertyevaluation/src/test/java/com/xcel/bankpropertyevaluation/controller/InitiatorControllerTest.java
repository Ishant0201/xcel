package com.xcel.bankpropertyevaluation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xcel.bankpropertyevaluation.dto.GetInitiatorByIdResponse;
import com.xcel.bankpropertyevaluation.service.InitiatorService;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = InitiatorController.class)
@AutoConfigureMockMvc
public class InitiatorControllerTest {

    private static final Logger log = LoggerFactory.getLogger(InitiatorControllerTest.class);
    @Autowired
    MockMvc mockMvc;
    String initiatorBusinessUnit = "Ishant96";
    String initiatorName = "Ishant";
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private InitiatorService initiatorService;

    @Test
    public void getInitiatorDeatilsByBusinessUnit() throws Exception {

        String initatorBusinessUnit = initiatorBusinessUnit;
        GetInitiatorByIdResponse getInitiatorByIdResponse = new GetInitiatorByIdResponse();
        getInitiatorByIdResponse.setInitiatorName(initiatorName);
        getInitiatorByIdResponse.setInitiatorBusinessUnit(initiatorBusinessUnit);
        getInitiatorByIdResponse.setInitiatorContactNo(98765432);

        when(initiatorService.getInitiatorByInitatorBusinessUnit(initatorBusinessUnit)).thenReturn(getInitiatorByIdResponse);

        mockMvc.perform(get("/api/initiator/getInitiatorByName")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .queryParam("initatorBusinessUnit", initiatorBusinessUnit))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.initiatorName").value(initiatorName))
                .andExpect(jsonPath("$.initiatorBusinessUnit").value(initatorBusinessUnit))
                .andExpect(jsonPath("$.initiatorContactNo").value(98765432))
                .andDo(print());
    }

    @Test
    public void getInitiatorDeatilsByBusinessUnit_Invalid_BusinessUnit() throws Exception {

        String initatorBusinessUnit = "Invalid_BusinessUnit";
        GetInitiatorByIdResponse getInitiatorByIdResponse = new GetInitiatorByIdResponse();
        getInitiatorByIdResponse.setInitiatorName(null);
        getInitiatorByIdResponse.setInitiatorBusinessUnit(null);
        getInitiatorByIdResponse.setInitiatorContactNo(0);

        when(initiatorService.getInitiatorByInitatorBusinessUnit(initatorBusinessUnit)).thenReturn(getInitiatorByIdResponse);

        mockMvc.perform(get("/api/initiator/getInitiatorByName")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .queryParam("initatorBusinessUnit", "Invalid_BusinessUnit"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.initiatorName", CoreMatchers.is(CoreMatchers.nullValue())))
                .andExpect(jsonPath("$.initiatorBusinessUnit", CoreMatchers.is(CoreMatchers.nullValue())))
                .andExpect(jsonPath("$.initiatorContactNo").value(0))
                .andDo(print());
    }
}
