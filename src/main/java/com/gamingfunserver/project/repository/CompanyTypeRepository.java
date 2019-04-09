package com.gamingfunserver.project.repository;

import com.gamingfunserver.project.model.company.CompanyType;
import com.gamingfunserver.project.model.company.CompanyTypeName;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface CompanyTypeRepository extends JpaRepository<CompanyType, Long> {
    Optional<CompanyType> findByName(CompanyTypeName companyTypeName);


}
