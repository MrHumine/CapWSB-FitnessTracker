package pl.wsb.fitnesstracker.user.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserProvider {

    /**
     * Retrieves a user based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param userId id of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<User> getUser(Long userId);

    /**
     * Retrieves a user based on their email.
     * If the user with given email is not found, then {@link Optional#empty()} will be returned.
     *
     * @param email The email of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<User> getUserByEmail(String email);

    /**
     * Retrieves all users.
     *
     * @return An {@link Optional} containing the all users,
     */
    List<User> findAllUsers();

    /**
     * Retrieves users whose email contains the specified fragment (case-insensitive).
     *
     * @param emailFragment fragment of email to search for
     * @return List of users whose email contains the fragment
     */
    List<User> findUsersByEmailFragment(String emailFragment);

    /**
     * Retrieves users who are older than the specified date (born before the date).
     *
     * @param date the date to compare against
     * @return List of users born before the specified date
     */
    List<User> findUsersOlderThan(LocalDate date);

}
