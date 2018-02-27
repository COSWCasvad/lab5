package edu.eci.cosw.samples;


import com.sun.net.httpserver.Authenticator;
import edu.eci.cosw.example.persistence.PatientsRepository;
import edu.eci.cosw.jpa.sample.model.Consulta;
import edu.eci.cosw.jpa.sample.model.Paciente;
import edu.eci.cosw.jpa.sample.model.PacienteId;
import edu.eci.cosw.samples.services.PatientServices;
import edu.eci.cosw.samples.services.ServicesException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
            Set<Consulta> c = new HashSet<>();
            c.add(new Consulta(new Date(100), "consulta"));
            Paciente p = new Paciente(new PacienteId(1, "cc"), "carlossdad", new Date(5000),c);
            pr.save(p);
            try {
                assert  p.getNombre().equals(ps.getPatient(1, "cc").getNombre());

            } catch (ServicesException ex) {
                fail("Fail in test deberiaConsultarPacienteQueExiste");
            }finally{
                pr.deleteAll();
            }
        }
        
        @Test
        public void noDeberiaConsultarPacienteQueExiste(){
            try {
                ps.getPatient(1, "cc");
                fail("Fail in test noDeberiaConsultarPacienteQueExiste");
            } catch (ServicesException ex) {
                
            }finally{
                pr.deleteAll();
            }
            
            
        }
        @Test
        public void noDeberiaObtenerPacientesConMenosConsultas(){
            Set<Consulta> c = new HashSet<>();
            c.add(new Consulta(new Date(100), "consulta"));
            Paciente p = new Paciente(new PacienteId(1, "cc"), "carlossdad", new Date(5000),c);
            pr.save(p);
            try {
                List al=ps.topPatients(2);
                assert al.isEmpty();

            } catch (ServicesException ex) {
                fail("Fail in test noDeberiaObtenerPacientesConMenosConsultas");
            }finally{
                pr.deleteAll();
            }
        }
        @Test
        public void deberiaConsultarPacientesConNConsultas(){
            Set<Consulta> c = new HashSet<>();
            Set<Consulta> c2 = new HashSet<>();
            Set<Consulta> c3 = new HashSet<>();
            c.add(new Consulta(new Date(100), "consulta"));c.add(new Consulta(new Date(100), "consulta2"));
            c2.add(new Consulta(new Date(500), "consulta3"));
            Paciente p = new Paciente(new PacienteId(1, "cc"), "carlossdad", new Date(5000),c);
            Paciente p2 = new Paciente(new PacienteId(1, "cc"), "carlossdad", new Date(5000),c2);
            Paciente p3 = new Paciente(new PacienteId(1, "cc"), "carlossdad", new Date(5000),c3);
            pr.save(p);
            pr.save(p2);
            pr.save(p3);
            try {
                List al=ps.topPatients(2);
                assert al.size()==1;

            } catch (ServicesException ex) {
                fail("Fail in test deberiaConsultarPacientesConNConsultas");
            }finally{
                pr.deleteAll();
            }
        }


}
