package com.example.demo.repositories;


import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Module;
public interface ModuleRepository extends CrudRepository<Module, Integer> {

}
