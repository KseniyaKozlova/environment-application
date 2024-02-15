package by.itacademy.environment.repositories.user;

import by.itacademy.environment.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static by.itacademy.environment.util.Constants.ID;
import static by.itacademy.environment.util.Constants.SELECT_USER_BONUSES_JPQL_QUERY;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findUserByLogin(String login);

    List<User> findUsersByCouponsId(UUID id);

    @Query(value = SELECT_USER_BONUSES_JPQL_QUERY)
    Integer findBonusesById(@Param(value = ID) UUID id);
}
