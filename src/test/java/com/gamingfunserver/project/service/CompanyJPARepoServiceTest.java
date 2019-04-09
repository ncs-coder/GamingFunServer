package com.gamingfunserver.project.service;

import com.gamingfunserver.project.model.company.CompanyType;
import com.gamingfunserver.project.payload.Company.CompanyTypeCreateRequest;
import com.gamingfunserver.project.payload.Company.CompanyTypeUpdateRequest;
import com.gamingfunserver.project.repository.CompanyTypeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class CompanyJPARepoServiceTest {

    @Mock
    CompanyTypeRepository companyTypeRepository;

    @InjectMocks
    CompanyJPARepoService companyTypeService;

    List<CompanyType> companyTypeList;


    @Before
    public void setUp() throws Exception {
        // this.companyTypeService = new CompanyJPARepoService();
        companyTypeList = new ArrayList<CompanyType>();

        for(long i = 1;i <= 10 ;i++){
            CompanyType companyType = new CompanyType();
            companyType.setId(i);
            companyType.setName("Name"+i);
            companyType.setDescription("Description"+i);
            companyTypeList.add(companyType);
        }

    }

    @Test
    public void findAll() {
        when(companyTypeRepository.findAll()).thenReturn(companyTypeList);
        assertEquals(10, companyTypeService.findAll().size());

    }

    @Test
    public void findById() {
        long idToFind = 1;
        when(companyTypeRepository.findById(idToFind)).thenReturn(Optional.of(companyTypeList.stream()
                .filter(companyType -> idToFind ==(companyType.getId()))
                .findAny()
                .orElse(null)));

        CompanyType companyType = companyTypeService.findById(idToFind);


        assertNotNull(companyType);
        assertEquals(idToFind,companyType.getId());
        assertTrue(("Description"+idToFind).equals(companyType.getDescription()));
        assertTrue(("Name"+idToFind).equals(companyType.getName()));
       // fail("not implemented");
    }

    @Test
    public void save() {
        long id = companyTypeList.size()+1;
        CompanyTypeCreateRequest companyType = new CompanyTypeCreateRequest();
            //setId(id);
        companyType.setDescription("Description"+id);
        companyType.setName("Name"+id);


        CompanyType ct = new CompanyType();
            ct.setId(id);
            ct.setName(companyType.getName());
            ct.setDescription(companyType.getDescription());

        when(companyTypeRepository.save(Mockito.any(CompanyType.class))).thenReturn(ct);

        CompanyType companyTypeSave = companyTypeService.save(companyType);
        assertNotNull(companyTypeSave);
        assertTrue(companyType.getDescription().equals(companyTypeSave.getDescription()));
        assertTrue(companyType.getName().equals(companyTypeSave.getName()));
        assertEquals(id,companyTypeSave.getId());
    }

    @Test
    public void update() {

        long id = 5;
        CompanyTypeUpdateRequest companyType = new CompanyTypeUpdateRequest();
        companyType.setId(id);
        companyType.setDescription("Description UPDATED"+id);
        companyType.setName("Name UPDATED"+id);


        when(companyTypeRepository.findById(id)).thenReturn(Optional.of(companyTypeList.stream()
                .filter(ct -> id ==(ct.getId()))
                .findAny()
                .orElse(null)));

        CompanyType ct =new CompanyType();
        ct.setId(id);
        ct.setName(companyType.getName());
        ct.setDescription(companyType.getDescription());


        when(companyTypeRepository.save(Mockito.any(CompanyType.class))).thenReturn(ct);

        CompanyType companyTypeSave = companyTypeService.update(companyType);
        assertNotNull(companyTypeSave);
        assertTrue(companyType.getDescription().equals(companyTypeSave.getDescription()));
        assertTrue(companyType.getName().equals(companyTypeSave.getName()));
        assertEquals(id,companyTypeSave.getId());

       // fail("not implemented");
    }

    @Test
    public void delete() {

        long idToDelete = 100;
        companyTypeService.delete(idToDelete);
        verify(companyTypeRepository, times(1)).deleteById(idToDelete);


    }
}