package arnado.mike.userapplication.impl.domain.service;

import arnado.mike.common.domain.model.User;
import arnado.mike.userapplication.domain.service.UserRegistrationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRegistrationServiceTest {

    @Autowired
    UserRegistrationService service;

    @Test
    void testRegister() {
        User user = service.register();

        assert user != null;
    }
}