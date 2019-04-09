package com.gamingfunserver.project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamingfunserver.project.model.company.CompanyType;
import com.gamingfunserver.project.payload.Company.CompanyTypeCreateRequest;
import com.gamingfunserver.project.payload.Company.CompanyTypeUpdateRequest;
import com.gamingfunserver.project.service.CompanyTypeService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyTypeService<CompanyType, CompanyTypeCreateRequest, CompanyTypeUpdateRequest> companyTypeService;

    private ObjectMapper objectMapper;



    @Before
    public void setUp() throws Exception {
        objectMapper  = new ObjectMapper();

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    @WithMockUser(username = "username", authorities={"ROLE_USER"})
    public void allCompanyType() throws Exception{
        List<CompanyType> companyTypeList = new ArrayList<CompanyType>();

        for(long i = 1;i <= 1 ;i++){
            CompanyType companyType = new CompanyType();
            companyType.setId(i);
            companyType.setName("Name"+i);
            companyType.setDescription("Description"+i);
            companyTypeList.add(companyType);
        }

        when(companyTypeService.findAll()).thenReturn(companyTypeList);

        this.mockMvc.perform(get("/api/company/type"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id': 1,'name': 'Name1', 'description': 'Description1'}]"));

       // fail("not implemented");
    }

    @Test
    @WithMockUser(username = "username", authorities={"ROLE_ADMIN"})
    public void createCompanyType() throws Exception{
        CompanyTypeCreateRequest companyTypeCreateRequest = new CompanyTypeCreateRequest();
        companyTypeCreateRequest.setDescription("TEST DESCRIPTION");
        companyTypeCreateRequest.setName("TEST NAME");

        CompanyType ct = new CompanyType();
        ct.setId(6);
        ct.setName(companyTypeCreateRequest.getName());
        ct.setDescription(companyTypeCreateRequest.getDescription());

        when(companyTypeService.save(Mockito.any(CompanyTypeCreateRequest.class))).thenReturn(ct);

        String jsonString = objectMapper.writeValueAsString(companyTypeCreateRequest);

        this.mockMvc.perform(post("/api/company/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id': 6,'name': 'TEST NAME', 'description': 'TEST DESCRIPTION'}"));
    }

    @Test
    @WithMockUser(username = "username", authorities={"ROLE_USER"})
    public void getCompanyTypeById() throws Exception{

        long idToFind = 6;
        CompanyType ct = new CompanyType();
        ct.setId(idToFind);
        ct.setName("TEST NAME");
        ct.setDescription("TEST DESCRIPTION");

        when(companyTypeService.findById(idToFind)).thenReturn(ct);

        this.mockMvc.perform(get("/api/company/type/{id}",idToFind))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id': 6,'name': 'TEST NAME', 'description': 'TEST DESCRIPTION'}"));

        //fail("not implemented");
    }

    @Test
    @WithMockUser(username = "username", authorities={"ROLE_ADMIN"})
    public void updateCompanyTypeById() throws  Exception{

        long idToFind = 6;
        CompanyTypeUpdateRequest ct = new CompanyTypeUpdateRequest();
        ct.setId(idToFind);
        ct.setName("TEST NAME UPDATED");
        ct.setDescription("TEST DESCRIPTION UPDATED");

        CompanyType ctype = new CompanyType();
        ctype.setId(ct.getId());
        ctype.setName("TEST NAME UPDATED");
        ctype.setDescription("TEST DESCRIPTION UPDATED");



        when(companyTypeService.update(Mockito.any(CompanyTypeUpdateRequest.class))).thenReturn(ctype);

        String jsonString = objectMapper.writeValueAsString(ct);

        this.mockMvc.perform(put("/api/company/type/{id}",idToFind)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id': 6,'name': 'TEST NAME UPDATED', 'description': 'TEST DESCRIPTION UPDATED'}"));
    }

    @Test
    @WithMockUser(username = "username", authorities={"ROLE_ADMIN"})
    public void deleteCompanyTYpeById() throws Exception{

        long idToDelete = 6;

        when(companyTypeService.delete(idToDelete)).thenReturn(true);

        this.mockMvc.perform(delete("/api/company/type/{id}",idToDelete)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

    }
}