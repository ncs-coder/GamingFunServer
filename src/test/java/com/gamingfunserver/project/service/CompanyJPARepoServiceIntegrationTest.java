package com.gamingfunserver.project.service;


import com.gamingfunserver.project.model.company.CompanyType;
import com.gamingfunserver.project.payload.Company.CompanyTypeCreateRequest;
import com.gamingfunserver.project.payload.Company.CompanyTypeUpdateRequest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;


//@DataJpaTest
@RunWith(SpringRunner.class)
@SpringBootTest()
public class CompanyJPARepoServiceIntegrationTest {

    @Autowired
    private CompanyJPARepoService companyTypeService;

    @Before
    public void setUp() throws Exception {


    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void integrationTestForCompanyTypeService(){

        //findAll

        List<CompanyType> allCompanyTypes = companyTypeService.findAll();
        int sizeBeforeAdd = allCompanyTypes.size();

        assertTrue(sizeBeforeAdd >= 0);

        //Add
        CompanyTypeCreateRequest companyTypeCreateRequest = new CompanyTypeCreateRequest();
        companyTypeCreateRequest.setName("TEST Name");
        companyTypeCreateRequest.setDescription("TEST Description");


        CompanyType testCompanyType = companyTypeService.save(companyTypeCreateRequest);

        List<CompanyType> allCompanyTypesAfterAdd = companyTypeService.findAll();
        int sizeAfterAdd = allCompanyTypesAfterAdd.size();

        assertTrue(sizeAfterAdd > sizeBeforeAdd);


        //findById
        CompanyType companyTypeFindById = companyTypeService.findById(testCompanyType.getId());
        assertEquals(companyTypeFindById.getId(),testCompanyType.getId());
        assertEquals(companyTypeFindById.getDescription(),testCompanyType.getDescription());
        assertEquals(companyTypeFindById.getName(),testCompanyType.getName());



        //Update
        CompanyTypeUpdateRequest companyTypeUpdateRequest = new CompanyTypeUpdateRequest();
        companyTypeUpdateRequest.setId(companyTypeFindById.getId());
        companyTypeUpdateRequest.setName("TEST NAME UPDATED");
        companyTypeUpdateRequest.setDescription("TEST Description UPDATED");


        CompanyType companyTypeUpdated = companyTypeService.update(companyTypeUpdateRequest);

        assertEquals(companyTypeFindById.getId(),companyTypeUpdated.getId());
        assertNotEquals(companyTypeFindById.getDescription(),companyTypeUpdated.getDescription());
        assertNotEquals(companyTypeFindById.getName(),companyTypeUpdated.getName());

        //delete
        companyTypeService.delete(companyTypeUpdated.getId());

        CompanyType shouldBeNull = companyTypeService.findById(companyTypeUpdated.getId());

        assertNull(shouldBeNull);


    }
}