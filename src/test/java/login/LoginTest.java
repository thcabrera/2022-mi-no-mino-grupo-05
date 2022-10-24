package login;

import domain.db.EntityManagerHelper;
import domain.usuarios.Usuario;
import helpers.HashHelper;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginTest {

    private final String nombreDeUsuario = "saulgoodman@outlook.com";
    private final String contrasenia = "im_heisenberg2009";
    private Usuario usuario;

    @BeforeAll
    public void instanciarUsuario(){
        this.usuario = new Usuario();
        this.usuario.setNombreDeUsuario(this.nombreDeUsuario);
        this.usuario.setContrasenia(HashHelper.hashear(this.contrasenia));
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().persist(this.usuario);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }

    @AfterAll
    public void eliminarUsuario(){
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().remove(this.usuario);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }

    @Test
    public void recuperarUsuarioPorContrasenia(){
        Usuario usuarioDeBD = (Usuario) EntityManagerHelper.getEntityManager()
                .createQuery("from " + Usuario.class.getName() + " as u where u.nombreDeUsuario = '"
                        + this.nombreDeUsuario + "' and u.contrasenia = '"
                        + HashHelper.hashear(this.contrasenia) + "'")
                .getSingleResult();

        // me deberia traer el mismo usuario
        Assertions.assertEquals(this.usuario, usuarioDeBD);

    }

    @Test
    public void recuperarUsuarioConContraseniaIncorrecta(){
        String contraseniaIncorrecta = "gustavo_fring2013";
        // vamos a testear si pidiendo que lo traiga con una contraseña incorrecta nos tira NoResultException
        Assertions.assertThrows(javax.persistence.NoResultException.class,
                () -> EntityManagerHelper.getEntityManager()
                                .createQuery("from " + Usuario.class.getName() + " as u where u.nombreDeUsuario = '"
                                        + this.nombreDeUsuario + "' and u.contrasenia = '"
                                        + HashHelper.hashear(contraseniaIncorrecta) + "'")
                                .getSingleResult());
    }

}
