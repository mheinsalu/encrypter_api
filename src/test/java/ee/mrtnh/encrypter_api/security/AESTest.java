package ee.mrtnh.encrypter_api.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AESTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void setKey() {
    }

    @Test
    void encrypt() {
        AES.setKey("testKey");
        String encryptedString = AES.encrypt("testString", "testPassword");
        assertThat(encryptedString).isEqualTo("HMzhctXGFRRTJKvAgzkmhw==");
    }

    @Test
    void decryptValid() {
        AES.setKey("testKey");
        String stringToDecrypt = "HMzhctXGFRRTJKvAgzkmhw==";
        String decryptedString = AES.decrypt(stringToDecrypt, "testPassword");
        assertThat(decryptedString).isEqualTo("testString");
    }

    @Test
    void decryptInvalidData() {
        AES.setKey("testKey");
        String stringToDecrypt = "invalid";
        String decryptedString = AES.decrypt(stringToDecrypt, "testPassword");
        assertThat(decryptedString).isNotEqualTo("testString");
    }

    @Test
    void decryptWithIncorrectPassword() {
        AES.setKey("testKey");
        String stringToDecrypt = "HMzhctXGFRRTJKvAgzkmhw==";
        String decryptedString = AES.decrypt(stringToDecrypt, "invalid");
        assertThat(decryptedString).isNotEqualTo("testString");
    }
}