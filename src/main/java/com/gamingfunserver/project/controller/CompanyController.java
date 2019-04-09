package com.gamingfunserver.project.controller;

import com.gamingfunserver.project.model.company.CompanyType;
import com.gamingfunserver.project.payload.Company.CompanyTypeCreateRequest;
import com.gamingfunserver.project.payload.Company.CompanyTypeUpdateRequest;
import com.gamingfunserver.project.service.CompanyTypeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private CompanyTypeService<CompanyType,CompanyTypeCreateRequest,CompanyTypeUpdateRequest> companyTypeService;

    @GetMapping("/type")
    public List<CompanyType> allCompanyType() {
        return companyTypeService.findAll();
    }

    @PostMapping("/type")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CompanyType createCompanyType(@Valid @RequestBody CompanyTypeCreateRequest companyTypeCreateRequest){
        return  companyTypeService.save(companyTypeCreateRequest);
    }

    @GetMapping("/type/{id}")
    public CompanyType getCompanyTypeById(@PathVariable(value = "id") Long id){
        return companyTypeService.findById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/type/{id}")
    public CompanyType updateCompanyTypeById(@Valid @RequestBody CompanyTypeUpdateRequest companyTypeUpdateRequest){
        return companyTypeService.update(companyTypeUpdateRequest);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/type/{id}")
    public boolean deleteCompanyTypeById(@PathVariable(value = "id") Long id){
        return companyTypeService.delete(id);

    }

}
