package by.itacademy.environment.repositories.user;

import by.itacademy.environment.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static by.itacademy.environment.util.Constants.ID;
import static by.itacademy.environment.util.Constants.SELECT_USER_BONUSES_JPQL_QUERY;

public interface UserRepository extends JpaRepository<User, UUID> {

    /**
     * Get user from DB by login
     *
     * @param login user login
     * @return user optional
     */
    Optional<User> findUserByLogin(String login);

    /**
     * Get users from DB with current coupon
     *
     * @param id coupon id, that user must have
     * @return list of users
     */
    List<User> findUsersByCouponsId(UUID id);

    /**
     * Get user's bonuses from DB
     *
     * @param id user id, whose bonuses are need
     * @return bonuses
     */
    @Query(value = SELECT_USER_BONUSES_JPQL_QUERY)
    Integer findBonusesById(@Param(value = ID) UUID id);
}
