/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.samples.services;

import edu.eci.cosw.example.persistence.PatientsRepository;
import edu.eci.cosw.jpa.sample.model.Paciente;
import edu.eci.cosw.jpa.sample.model.PacienteId;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 2105534
 */
@Service
public class PatientServiceImpl implements PatientServices{
    
    @Autowired
    private PatientsRepository pr;
    
    @Override
    public Paciente getPatient(int id, String tipoid) throws ServicesException {
        if(!pr.exists(new PacienteId(id, tipoid))) throw new ServicesException("The user doen't exist");
        return pr.findOne(new PacienteId(id, tipoid));
    }

    @Override
    public List<Paciente> topPatients(int n) throws ServicesException {
        return pr.topPatients(n);
        
    }
    
}
