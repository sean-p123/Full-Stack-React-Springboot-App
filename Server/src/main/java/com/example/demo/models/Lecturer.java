package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

@Entity
public class Lecturer {
	//generates primary key
	
	/*@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Null(message = "ID must not be provided")
	private Integer id;
	*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "Lid is mandatory")
	@Column(unique = true)
	private String lid;
	@NotNull(message = "Lecturer name is mandatory")
	private String name;
	
	//one lecturer to many modules
	//@JsonIgnore
	@OneToMany(mappedBy = "lecturer")
	private List<Module> modules = new ArrayList<Module>();
	
	//optional attributes
	private String taxBand;
	private Integer salaryScale;
	
	public String getTaxBand() {
		return taxBand;
	}
	public void setTaxBand(String taxBand) {
		this.taxBand = taxBand;
	}
	public Integer getSalaryScale() {
		return salaryScale;
	}
	public void setSalaryScale(Integer salaryScale) {
		this.salaryScale = salaryScale;
	}
	public Integer getId() {
		return id;
	}
	@JsonIgnore
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLid() {
		return lid;
	}
	public void setLid(String lid) {
		this.lid = lid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Module> getModules() {
		return modules;
	}
	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
	@Override
	public String toString() {
		return "Lecturer [id=" + id + ", lid=" + lid + ", name=" + name + ", modules=" + modules + ", taxBand="
				+ taxBand + ", salaryScale=" + salaryScale + "]";
	}

	

}
