package org.example.controllers;

import org.example.model.Socks;
import org.example.model.typeOperation;
import org.example.repository.SocksRepository;
import org.example.servicies.SocksServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.example.SocksTestConstance.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class SocksControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SocksRepository socksRepository;
    @SpyBean
    private SocksServiceImpl socksService;
    @InjectMocks
    private SocksController socksController;
    private final Socks SOCKS_OBJECT_INCOME = SOCK_1;
    private final Socks SOCKS_OBJECT_OUTCOME = SOCK_2;
    private final String LOCAL_URL = URL + PORT + "/" + "/api/socks";

    @Test
    void contextLoads() {
        assertThat(socksController).isNotNull();
    }

    @Test
    void testIncomeSocks() {
        SOCKS_OBJECT_INCOME.setDate(Timestamp.valueOf(LocalDateTime.now()));
        SOCKS_OBJECT_INCOME.setTypeOperation(typeOperation.INCOME);

        when(socksRepository.save(any(Socks.class))).thenReturn(SOCKS_OBJECT_INCOME);

        String url = LOCAL_URL + "/income?colour=" + COlOUR_RED + "&cottonPart=" + COTTON_PART_80 + "&quantity=" + QUANTITY_50;

        try {
            mockMvc.perform(MockMvcRequestBuilders
                            .post(url)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.colour").value(COlOUR_RED))
                    .andExpect(jsonPath("$.quantity").value(QUANTITY_50))
                    .andExpect(jsonPath("$.cottonPart").value(COTTON_PART_80))
                    .andExpect(jsonPath("$.typeOperation").value("INCOME"))
                    .andExpect(jsonPath("$.date").isNotEmpty());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    void testOutcomeSocks() {
        SOCKS_OBJECT_OUTCOME.setDate(Timestamp.valueOf(LocalDateTime.now()));
        SOCKS_OBJECT_OUTCOME.setTypeOperation(typeOperation.OUTCOME);

        when(socksRepository.save(any(Socks.class))).thenReturn(SOCKS_OBJECT_OUTCOME);

        String url = LOCAL_URL + "/outcome?colour=" + COlOUR_RED + "&cottonPart=" + COTTON_PART_80 + "&quantity=" + 70;

        try {
            mockMvc.perform(MockMvcRequestBuilders
                            .post(url)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.colour").value(COlOUR_RED))
                    .andExpect(jsonPath("$.quantity").value(QUANTITY_MINUS_70))
                    .andExpect(jsonPath("$.cottonPart").value(COTTON_PART_80))
                    .andExpect(jsonPath("$.typeOperation").value("OUTCOME"))
                    .andExpect(jsonPath("$.date").isNotEmpty());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    @Test
    void testGetAmountOfSocksByColour() throws Exception {
        when(socksRepository.getQuantityByColourAndMoreThanCottonPart(COlOUR_RED, COTTON_PART_80)).thenReturn(QUANTITY_50 + QUANTITY_1);
        when(socksRepository.getQuantityByColourAndEqualsCottonPart(COlOUR_GREEN, COTTON_PART_100)).thenReturn(QUANTITY_1);
        when(socksRepository.getQuantityByColourAndLessThenCottonPart(COlOUR_BLACK, COTTON_PART_10)).thenReturn(QUANTITY_50);


        MvcResult resultMoreThan = mockMvc.perform(get(LOCAL_URL + "?colour=" + COlOUR_RED + "&operation=moreThan&cottonPart=" + COTTON_PART_80))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals("moreThan", resultMoreThan.getResponse().getContentAsString(),(Integer.toString(QUANTITY_50 + QUANTITY_1)));

        MvcResult resultEquals = mockMvc.perform(get(LOCAL_URL + "?colour=" + COlOUR_GREEN + "&operation=equals&cottonPart=" + COTTON_PART_100))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals("equals", resultEquals.getResponse().getContentAsString(),(Integer.toString(QUANTITY_1)));

        MvcResult resultLessThan = mockMvc.perform(get(LOCAL_URL + "?colour=" + COlOUR_BLACK + "&operation=lessThan&cottonPart=" + COTTON_PART_10))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals("lessThan", resultLessThan.getResponse().getContentAsString(),(Integer.toString(QUANTITY_50)));


    }


}
