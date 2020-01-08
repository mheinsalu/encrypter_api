package ee.mrtnh.encrypter_api.repository;

import ee.mrtnh.encrypter_api.model.EncryptedData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncryptedDataRepository extends JpaRepository<EncryptedData, Integer> {

    // method body is auto-generated.
    // Parameter name in method name must match parameter name in class
    EncryptedData findDataByUserName(String userName);

    EncryptedData findDataByEncryptedData(String encryptedData);
}
