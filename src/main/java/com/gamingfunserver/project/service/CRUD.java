package com.gamingfunserver.project.service;

import java.util.List;

public interface CRUD<T,C,U> {

     List<T> findAll();

     T findById(Long l);

     T save(C c);

     T update(U u);

     boolean delete(Long l);

}