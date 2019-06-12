package com.patient.patientdemo.PatientGenerationTask;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.patient.patientdemo.model.Patient;
import com.patient.patientdemo.repository.PatientRepository;
@Component
public class PatientGenerationTask {

	
	//List<Patient> records ;
	@Autowired
	private PatientRepository repository;
	public void generatePatients(List<Patient> patients) {
		Iterator it = patients.iterator();
		while(it.hasNext()) {
			Patient p = (Patient)it.next();
			Optional<Patient> p1= repository.findById(p.getId());
			boolean b = p1.isPresent();
			if(!p1.isPresent())
				repository.save(p);

			
		}
		
	}

}
