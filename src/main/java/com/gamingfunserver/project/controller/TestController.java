package com.gamingfunserver.project.controller;

import java.util.List;

import com.gamingfunserver.project.model.Role;
import com.gamingfunserver.project.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;

@RestController
public class TestController {

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/")
    public String index(){
        return "Hello World :) !!!!!!!";
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
