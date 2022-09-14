package test;

import db.EntityManagerHelper;
import entities.Usuario;
import org.junit.Assert;
import org.junit.Test;

public class EmTest {
    @Test
    public  void persistirUsuario(){
        Usuario usuario = new Usuario();

        usuario.setNombre("Eze");
        usuario.setApellido("Alfonso");
        usuario.setLegajo(1111111);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(usuario); // Se prepara el INSERT
        EntityManagerHelper.commit(); // Se ejecutan todas las lineas

    }

    @Test
    public void actualizarUsuario(){

        EntityManagerHelper.beginTransaction();

        // Como recuperaaria el id para realizar el update
        Usuario usuario = EntityManagerHelper.entityManager().find(Usuario.class, 1);

        usuario.setNombre("Ezequiel");
        EntityManagerHelper.getEntityManager().merge(usuario);

        EntityManagerHelper.commit();

    }
    @Test
    public  void recuperarUsuario(){
        Usuario eze = (Usuario) EntityManagerHelper.createQuery("from Usuario where nombre='Eze'").getSingleResult();
        Assert.assertEquals("Eze", eze.getNombre());

        System.out.println("Nombre: " + eze.getNombre() + "Apellido: " + eze.getApellido());
        System.out.println("Nombre: " + eze.getNombre() + "Apellido: " + eze.getLegajo());
    }
}
