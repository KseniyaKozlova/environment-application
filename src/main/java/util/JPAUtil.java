package util;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Objects;

public class JPAUtil {

    private static EntityManager entityManager;

    private static EntityManager buildEntityManager() {
        return Persistence.createEntityManagerFactory("Environment")
                .createEntityManager();
    }

    public static EntityManager getEntityManager() {
        return Objects.requireNonNullElseGet(entityManager, () -> entityManager = buildEntityManager());
    }
}
