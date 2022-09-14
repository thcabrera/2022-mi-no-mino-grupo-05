import domain.db.EntityManagerHelper;
import domain.entidades.TipoOrg;
import org.junit.Assert;
import org.junit.Test;

public class EmTest {
    @Test
    public  void persistirTipoOrg(){
        TipoOrg tipo = new TipoOrg("criminal");

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo); // Se prepara el INSERT
        EntityManagerHelper.commit(); // Se ejecutan todas las lineas

    }

    @Test
    public void actualizarTipoOrg(){

        EntityManagerHelper.beginTransaction();

        // Como recuperaaria el id para realizar el update
        TipoOrg tipo = EntityManagerHelper.entityManager().find(TipoOrg.class, 1);

        EntityManagerHelper.getEntityManager().merge(tipo);

        EntityManagerHelper.commit();

    }
    @Test
    public  void recuperarTipoOrg(){
        TipoOrg tipo = (TipoOrg) EntityManagerHelper.createQuery("from TipoOrg where tipoOrg='criminal'").getSingleResult();
        Assert.assertEquals("criminal", tipo.getTipoOrg());
        System.out.println("Descripcion: " + tipo.getTipoOrg());
    }
}
