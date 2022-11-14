import domain.db.EntityManagerHelper;
import domain.entidades.*;
import domain.services.creacionReporte.ReportGenerator;
import domain.usuarios.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import java.time.LocalDate;
import java.util.ArrayList;

public class PersistenciaTest {

    /* ----------------CONFIGURACION GENERAL DE LA CLASE--------------------- */

    @BeforeAll
    static void crearUsuarioPersona(){
        Persona persona = new Persona();
        persona.setNombre("Thiagolenny");
        persona.setApellido("Marmocaldare");
        persona.setNroDocumento(43988887);
        persona.setTipoDoc(Documentacion.DNI);

        Usuario usuario = new Usuario();
        usuario.setNombreDeUsuario("panfleto");
        usuario.setContrasenia("fedeSosGroso");
        usuario.setActor(persona);
        EntityManagerHelper.getEntityManager()
                .getTransaction()
                .begin();
        EntityManagerHelper.getEntityManager()
                .persist(usuario);
        EntityManagerHelper.getEntityManager()
                .getTransaction()
                .commit();

    }

    @BeforeAll
    static void crearUsuarioOrganizacion(){
        Organizacion organizacion = new Organizacion();
        organizacion.setNombre("DespeEze");
        organizacion.setRazonSocial("15/89685");

        Usuario usuario = new Usuario();
        usuario.setNombreDeUsuario("despeEze1995");
        usuario.setContrasenia("jovenesAltoVuelo");
        usuario.setActor(organizacion);

        EntityManagerHelper.getEntityManager()
                .getTransaction()
                .begin();
        EntityManagerHelper.getEntityManager()
                .persist(usuario);
        EntityManagerHelper.getEntityManager()
                .getTransaction()
                .commit();

    }

    @BeforeAll
    static void crearUsuarioAgenteSectorial(){

        AgenteSectorial agenteSectorial = new AgenteSectorial();
        Provincia bsAs = (Provincia) EntityManagerHelper.getEntityManager()
                .createQuery("from " + Provincia.class.getName() + " as p  where p.descripcion = 'BUENOS AIRES'")
                .getSingleResult();
        agenteSectorial.setSector(bsAs);

        Usuario usuario = new Usuario();
        usuario.setNombreDeUsuario("leonardoDiCaprio");
        usuario.setContrasenia("theAviator2004");
        usuario.setActor(agenteSectorial);
        EntityManagerHelper.getEntityManager()
                .getTransaction()
                .begin();
        EntityManagerHelper.getEntityManager()
                .persist(usuario);
        EntityManagerHelper.getEntityManager()
                .getTransaction()
                .commit();

    }

    @BeforeAll
    static void crearOrgParaReportes(){
        HuellaDeCarbono h1 = new HuellaDeCarbono();
        h1.setFechaMedicion(LocalDate.of(2019,4,24));
        h1.setValor(386.17);
        HuellaDeCarbono h2 = new HuellaDeCarbono();
        h2.setFechaMedicion(LocalDate.of(2020,4,24));
        h2.setValor(528.78);
        HuellaDeCarbono h3 = new HuellaDeCarbono();
        h3.setFechaMedicion(LocalDate.of(2021,4,24));
        h3.setValor(708.98);
        HuellaDeCarbono h4 = new HuellaDeCarbono();
        h4.setFechaMedicion(LocalDate.of(2022,4,24));
        h4.setValor(1052.78);

        ArrayList<HuellaDeCarbono> huellas = new ArrayList<>();
        huellas.add(h1);
        huellas.add(h2);
        huellas.add(h3);
        huellas.add(h4);

        Organizacion organizacion = new Organizacion("78-486", null, null, null);
        organizacion.setNombre("org para reportes");
        organizacion.setHuellasDeCarbono(huellas);

        EntityManagerHelper.getEntityManager().persist(organizacion);

    }

    @AfterAll
    static void elminarObjetos(){

        // eliminamos al usuario de la Persona cargada
        Usuario up = (Usuario) EntityManagerHelper.getEntityManager()
                .createQuery("from " + Usuario.class.getName() + " as u where u.nombreDeUsuario = 'panfleto'")
                .getSingleResult();
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().remove(up);
        EntityManagerHelper.getEntityManager().remove(up.getActor());
        EntityManagerHelper.getEntityManager().getTransaction().commit();

        // eliminamos al usuario de la Persona cargada
        Usuario uO = (Usuario) EntityManagerHelper.getEntityManager()
                .createQuery("from " + Usuario.class.getName() + " as u where u.nombreDeUsuario = 'despeEze1995'")
                .getSingleResult();
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().remove(uO);
        EntityManagerHelper.getEntityManager().remove(uO.getActor());
        EntityManagerHelper.getEntityManager().getTransaction().commit();

        // eliminamos al usuario de la Persona cargada
        Usuario uAS = (Usuario) EntityManagerHelper.getEntityManager()
                .createQuery("from " + Usuario.class.getName() + " as u where u.nombreDeUsuario = 'leonardoDiCaprio'")
                .getSingleResult();
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().remove(uAS);
        EntityManagerHelper.getEntityManager().remove(uAS.getActor());
        EntityManagerHelper.getEntityManager().getTransaction().commit();

        // eliminamos a la organizacion con las huellas de carbono
        Organizacion organizacion = (Organizacion) EntityManagerHelper.getEntityManager()
                .createQuery("from " + Organizacion.class.getName() + " as o where o.razonSocial = '78-486'")
                .getSingleResult();
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().remove(organizacion);
        EntityManagerHelper.getEntityManager().getTransaction().commit();

    }

    /* --------------------------------------TESTS---------------------------------------------- */

    @Test
    public void obtenerPersonaDeUsuario(){
        Usuario usuario = (Usuario) EntityManagerHelper.getEntityManager()
                .createQuery("from " + Usuario.class.getName() + " as u where u.nombreDeUsuario = 'panfleto'")
                .getSingleResult();
        Persona persona = (Persona) usuario.getActor();

        Assertions.assertEquals("Thiagolenny", persona.getNombre());
        Assertions.assertEquals("Marmocaldare", persona.getApellido());
        Assertions.assertEquals(43988887, persona.getNroDocumento());
        Assertions.assertEquals(Documentacion.DNI, persona.getTipoDoc());

    }

    @Test
    public void obtenerOrganizacionDeUsuario(){
        Usuario usuario = (Usuario) EntityManagerHelper.getEntityManager()
                .createQuery("from " + Usuario.class.getName() + " as u where u.nombreDeUsuario = 'despeEze1995'")
                .getSingleResult();
        Organizacion organizacion = (Organizacion) usuario.getActor();

        Assertions.assertEquals("DespeEze", organizacion.getNombre());
        Assertions.assertEquals("15/89685", organizacion.getRazonSocial());

    }

    @Test
    public void obtenerAgenteSectorialDeUsuario(){
        Usuario usuario = (Usuario) EntityManagerHelper.getEntityManager()
                .createQuery("from " + Usuario.class.getName() + " as u where u.nombreDeUsuario = 'leonardoDiCaprio'")
                .getSingleResult();
        AgenteSectorial agenteSectorial = (AgenteSectorial) usuario.getActor();
        Provincia bsAs = (Provincia) EntityManagerHelper.getEntityManager()
                .createQuery("from " + Provincia.class.getName() + " as p  where p.descripcion = 'BUENOS AIRES'")
                .getSingleResult();
        Assertions.assertEquals(bsAs, agenteSectorial.getSector());

    }

    @Test
    public void generarReporteEvolucionDeHcDeOrgTest(){

        Organizacion organizacion = (Organizacion) EntityManagerHelper.getEntityManager()
                .createQuery("from " + Organizacion.class.getName() + " as o where o.razonSocial = '78-486'")
                .getSingleResult();

        ReportGenerator reportGenerator = new ReportGenerator();
        reportGenerator.evolucionDeHCTotal(organizacion);

    }

    @Test
    public void persistirPersonasConAreas(){
        Organizacion organizacion = new Organizacion("Falopa SA", null, null, null);
        organizacion.setNombre("Jumbo (?)");
        Area area = new Area();
        area.setOrganizacion(organizacion);
        area.setNombre("Area de prueba");
        organizacion.agregarArea(area);
        Persona persona = new Persona();
        persona.setNombre("Marmo");
        persona.setApellido("Midfade");
        ArrayList<Area> areas = new ArrayList<>();
        areas.add(area);
        persona.setListaAreas(areas);
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().persist(organizacion);
        EntityManagerHelper.getEntityManager().getTransaction().commit();

        persona.setListaAreas(areas);
        area.setMiembros(new ArrayList<>());
        area.agregarMiembro(persona);
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().persist(persona);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
        Persona persona2 = new Persona("Lenny", "Frontend Web Developer", 425866558, Documentacion.DNI);
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().persist(persona2);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
        persona2.agregarArea(area);
        area.agregarMiembro(persona2);
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().merge(persona2);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }

}
