package by.itacademy.repositories.user;

import by.itacademy.entities.User;
import by.itacademy.util.Constants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findUserByLogin(String login);

    List<User> findUsersByCouponsId(UUID id);

    @Query(value = Constants.SELECT_USER_BONUSES_JPQL_QUERY)
    Integer findBonusesById(@Param(value = Constants.ID) UUID id);
}
