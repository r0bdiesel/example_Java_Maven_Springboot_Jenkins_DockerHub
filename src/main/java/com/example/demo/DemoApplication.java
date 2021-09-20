package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.model.Company;


import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class DemoApplication {

	private List<Company> companies = new ArrayList<Company>();
	private Long idx = 0L;
	private Long numOfCalls = 0L;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/calls")
	public String calls() {
		return String.format("number of calls %s!", numOfCalls.toString());
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		numOfCalls++;
		return String.format("Hello %s!", name);
	}
	
	@GetMapping(path = "/person/{id}", produces = "application/json")
	public String person(@PathVariable(value = "id") String id) {
		numOfCalls++;
		return String.format("Hello %s!", id);
	}
	
	@GetMapping(path = "/company/{id}", produces = "application/json")
	public ResponseEntity<Company> company(@PathVariable(value = "id") Long id) {
		numOfCalls++;
		Company comp = findCompany(id);
		if(comp != null){
			return ResponseEntity.ok().body(comp);
		}
		else{
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@GetMapping("/listCompanies")
	public List<Company> listCompanies() {
		numOfCalls++;
		return companies;
	}
	
	
	@PostMapping(path = "/addCompany")
	public void addCompany(@RequestBody Company comp) {
		numOfCalls++;
		companies.add(comp);
    }
	
	
	@PutMapping(path = "/updateCompany")
	public ResponseEntity<Company> updateCompany(@RequestBody Company comp) {
		numOfCalls++;
		Company oldComp = findCompany(comp.getId());
		companies.remove(oldComp);
		companies.add(comp);
		if(comp != null){
			return ResponseEntity.ok().body(comp);
		}
		else{
			return ResponseEntity.badRequest().body(null);
		}
    }
	
	@DeleteMapping(path = "/deleteCompany/{id}")
	public ResponseEntity<Boolean> deleteCompany(@PathVariable(value = "id") Long id) {
		numOfCalls++;
		Company comp = findCompany(id);
		if(comp != null){
			companies.remove(comp);
			return ResponseEntity.ok().body(true);
		}
		else{
			return ResponseEntity.badRequest().body(false);
		}
    }
	
	
	private Company findCompany(Long id){
		Company comp = null;
		for(Company c: this.companies){
			if(c.getId() == id){
				comp = c;
			}
		}
		return comp;
	}
	
}
