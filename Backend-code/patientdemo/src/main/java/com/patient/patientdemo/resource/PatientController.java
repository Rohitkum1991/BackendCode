package com.patient.patientdemo.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.patient.patientdemo.PatientGenerationTask.PatientGenerationTask;
import com.patient.patientdemo.model.Patient;
import com.patient.patientdemo.repository.PatientRepository;

@RestController
public class PatientController {

	@Autowired
	private PatientRepository repository;
	
	@Autowired
	private PatientGenerationTask patientGenerator;
	
	@CrossOrigin(origins = "http://localhost:8081")
	@PostMapping("/addPatient")
	public String savePatient(@RequestBody Patient patient) {
		
		repository.save(patient);
		return "Added patient with id "+patient.getId();
	
	}
	
	@CrossOrigin(origins = "http://localhost:8081")
	@GetMapping("/getPatient/{id}")
	public Patient getPatientById(@PathVariable int id) {
		
		Optional<Patient> pat = repository.findById(id);
		return pat.get();
	
	}
	@CrossOrigin(origins = "http://localhost:8081")
	@PostMapping("/updateWardType")
	public String updateWard(@RequestBody Patient patient) {
		
		Optional<Patient> p = repository.findById(patient.getId());
		Patient p1 =p.get();
		p1.setWardType(patient.getWardType());
		repository.save(p1);
		return "ward updated for patient : "+p1.getId();
	
	}
	@CrossOrigin(origins = "http://localhost:8081")
	@PostMapping("/addMultiplePatients")
	public void addMultiplePatients(@RequestBody List<Patient> patients) {
		
		 patientGenerator.generatePatients(patients);
		
	
	}
	
	@CrossOrigin(origins = "http://localhost:8081")
	@GetMapping("/getAllPatients")
	public List<Patient> getAllPAtients() {
		
		return repository.findAll();
	
	}
	
	@CrossOrigin(origins = "http://localhost:8081")
	@DeleteMapping("/deletePatient/{id}")
	public String deletePatientById(@PathVariable int id) {
		
		repository.deleteById(id);
		
		return "Deleted patient with id "+id;
	}
}
