/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.example.persistence;

import edu.eci.cosw.jpa.sample.model.Paciente;
import edu.eci.cosw.jpa.sample.model.PacienteId;
import edu.eci.cosw.samples.services.ServicesException;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author 2105534
 */
public interface PatientsRepository extends JpaRepository<Paciente, PacienteId>{
    
    @Query("SELECT pa from Paciente pa where size(pa.consultas)>?1")
    public List<Paciente> topPatients(int n) throws ServicesException;
    
}
