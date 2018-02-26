package edu.eci.cosw.samples;


import edu.eci.cosw.example.persistence.PatientsRepository;
import edu.eci.cosw.jpa.sample.model.Paciente;
import edu.eci.cosw.jpa.sample.model.PacienteId;
import edu.eci.cosw.samples.services.PatientServices;
import edu.eci.cosw.samples.services.ServicesException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Assert;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringDataRestApiApplication.class)
@WebAppConfiguration
public class SpringDataRestApiApplicationTests {
    @Autowired
    private PatientServices ps;
    
    @Autowired
    private PatientsRepository pr;
        
        
        @Test
        public void deberiaConsultarPacienteQueExiste(){
            Paciente p = new Paciente(new PacienteId(1, "cc"), "carlossdad", new Date(5000));
            pr.save(p);
            System.out.println("------------------------ANTES--------------------------------");
        try {
            System.out.println(p.getNombre());
            //System.out.println(ps.getPatient(1, "cc").getNombre());
            System.out.println(ps.topPatients(0).get(0).getNombre());
            
        } catch (ServicesException ex) {
            fail("Fail in test deberiaConsultarPacienteQueExiste");
        }
        }
        
        @Test
        public void noDeberiaConsultarPacienteQueExiste(){
            
        }
        @Test
        public void noDeberiaObtenerPacientesConMenosConsultas(){
            
        }
        @Test
        public void deberiaConsultarPacientesConNConsultas(){
            
        }


}
