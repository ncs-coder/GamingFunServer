package com.gamingfunserver.project.service;

import com.gamingfunserver.project.model.company.CompanyType;
import com.gamingfunserver.project.payload.Company.CompanyTypeCreateRequest;
import com.gamingfunserver.project.payload.Company.CompanyTypeUpdateRequest;
import com.gamingfunserver.project.repository.CompanyTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CompanyJPARepoService  implements CompanyTypeService<CompanyType, CompanyTypeCreateRequest, CompanyTypeUpdateRequest> {

    @Autowired
    CompanyTypeRepository companyTypeRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyJPARepoService.class);

    @Override
    public List<CompanyType> findAll() {
        LOGGER.debug("Findall ");

        return companyTypeRepository.findAll();
    }

    @Override
    public CompanyType findById(Long l) {
        //throw new UnsupportedOperationException();
        Optional<CompanyType> companyType =companyTypeRepository.findById(l);
        return companyType.isPresent()? companyType.get():null;
    }



    @Override
    public CompanyType save(CompanyTypeCreateRequest companyTypeCreateRequest) {
       CompanyType ct = new CompanyType();
        ct.setDescription(companyTypeCreateRequest.getDescription());
        ct.setName(companyTypeCreateRequest.getName());


        return companyTypeRepository.save(ct);
//        Optional<CompanyType> companyType =companyTypeRepository.findById(ct.getId());
//        return companyType.isPresent()? companyType.get():null;
    }

    @Override
    public CompanyType update(CompanyTypeUpdateRequest companyTypeUpdateRequest) {

        CompanyType ct = companyTypeRepository.findById(companyTypeUpdateRequest.getId()).get();

        ct.setDescription(companyTypeUpdateRequest.getDescription());
        ct.setName(companyTypeUpdateRequest.getName());

         companyTypeRepository.save(ct);
         return ct;
    }

    @Override
    public boolean delete(Long l) {

        boolean deleted = false;
        try{
            companyTypeRepository.deleteById(l);
            deleted = true;
        }catch (Exception e){
            deleted = false;
        }
        return deleted;
    }
}
