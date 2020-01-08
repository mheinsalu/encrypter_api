package ee.mrtnh.encrypter_api.repository;

import ee.mrtnh.encrypter_api.model.EncryptedData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EncryptedDataRepositoryTest {

    @Autowired
    EncryptedDataRepository encryptedDataRepository;

    EncryptedData data1 = new EncryptedData("testData1", "testUser1");
    EncryptedData data2 = new EncryptedData("testData2", "testUser2");

    @BeforeEach
    void setUp() {
        encryptedDataRepository.save(data1);
        encryptedDataRepository.save(data2);
    }

    @Test
    void findDataByUserNamePass() {
        EncryptedData foundEncryptedData = encryptedDataRepository.findDataByUserName(data1.getUserName());
        assertThat(foundEncryptedData).isEqualTo(data1);
    }

    @Test
    void findDataByEncryptedDataPass() {
        EncryptedData foundEncryptedData = encryptedDataRepository.findDataByEncryptedData(data1.getEncryptedData());
        assertThat(foundEncryptedData).isEqualTo(data1);
    }

    @Test
    void findDataByUserNameNotPass() {
        EncryptedData foundEncryptedData = encryptedDataRepository.findDataByUserName(data1.getUserName());
        assertThat(foundEncryptedData).isNotEqualTo(data2);
    }

    @Test
    void findDataByEncryptedDataNotPass() {
        EncryptedData foundEncryptedData = encryptedDataRepository.findDataByEncryptedData(data1.getEncryptedData());
        assertThat(foundEncryptedData).isNotEqualTo(data2);
    }
}