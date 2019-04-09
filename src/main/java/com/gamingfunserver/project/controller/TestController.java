package com.gamingfunserver.project.controller;

import java.util.List;

import com.gamingfunserver.project.model.company.CompanyType;
import com.gamingfunserver.project.model.user.Role;
import com.gamingfunserver.project.repository.RoleRepository;
import com.gamingfunserver.project.service.CompanyTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CompanyTypeService companyTypeService;


    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);


    @GetMapping("/")
    public String index(){
        return "Hello World :) !!!!!!!";
    }


    @GetMapping("/test")
    public List<CompanyType> getAllCompanyTest(){
        LOGGER.trace("TEST CONTROLLER TEST trace");
        LOGGER.debug("TEST CONTROLLER TEST debug");
        LOGGER.info("TEST CONTROLLER TEST info");
        LOGGER.warn("TEST CONTROLLER TEST warn");
        LOGGER.error("TEST CONTROLLER TEST error");
        return companyTypeService.findAll();
    }


    @GetMapping("/roles")
    public List<Role> allRole(){
        return roleRepository.findAll();
    }


    @GetMapping("/echo")
    public String echo(@RequestParam(value = "request",defaultValue = "Hello!") String request){
        return request;
    }


}
