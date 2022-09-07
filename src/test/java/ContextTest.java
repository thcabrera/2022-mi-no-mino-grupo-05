import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import static org.junit.Assert.assertNotNull;

public class ContextTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

    @Test
    public void contextUp() {
        assertNotNull(entityManager());
    }
    @Test
    public void contextUpWithTransaction() throws Exception {
        withTransaction(() -> {});

    }
}