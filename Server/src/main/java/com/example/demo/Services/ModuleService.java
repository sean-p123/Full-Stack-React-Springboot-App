package com.example.demo.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Module;
import com.example.demo.repositories.ModuleRepository;

@Service
public class ModuleService {

	@Autowired
	ModuleRepository mr;
	
	public Optional<Module> getModule(int mid){
		return mr.findById(mid);
	}
	
	public void createModule(Module m) {
		mr.save(m);
	}
}
