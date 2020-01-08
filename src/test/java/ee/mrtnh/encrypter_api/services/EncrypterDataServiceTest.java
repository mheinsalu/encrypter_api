package ee.mrtnh.encrypter_api.services;

import ee.mrtnh.encrypter_api.model.DataPacket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
class EncrypterDataServiceTest {
    // TODO: can't get these tests to work. NullPointerException on encrypterDataService

    @Autowired
    private EncrypterDataService encrypterDataService;

    @BeforeEach
    void setUp() {
        /*
        DataPacket dataPacket2 = new DataPacket("data2", "user2", "password2"); // YDvOZHfT0w+IJLP3gAeo6Q==
        DataPacket dataPacket3 = new DataPacket("data3", "user3", "password3"); // mPAc/KRoZAQGjyBZ9nA7Sg==
         */
    }

    @Test
    void encryptData() {
        // DataPacket dataPacket1 = new DataPacket("data1", "user1", "password1"); // mg+C8CFApORVgn7CF0jE0w==
        // encrypterDataService.encryptData(dataPacket1);
    }

    @Test
    void decryptData() {

    }

    @Test
    void getAllData() {

    }
}