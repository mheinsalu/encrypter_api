package ee.mrtnh.encrypter_api.repository;

import ee.mrtnh.encrypter_api.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    User user = new User("testUser", "testPassword");

    @BeforeEach
    void setUp() {
        userRepository.save(user);
    }

    @Test
    void findUserByUserNamePass() {
        User foundUser = userRepository.findUserByUserName(user.getUserName());
        assertThat(user).isEqualTo(foundUser);
    }

    @Test
    void findUserByUserNameNotPass() {
        User foundUser = userRepository.findUserByUserName(user.getUserName());
        User user1 = new User("testUser1", "testPassword1");
        assertThat(user1).isNotEqualTo(foundUser);
    }
}