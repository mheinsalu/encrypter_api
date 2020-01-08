package ee.mrtnh.encrypter_api.services;

import ee.mrtnh.encrypter_api.model.DataPacket;
import ee.mrtnh.encrypter_api.model.EncryptedData;
import ee.mrtnh.encrypter_api.model.User;
import ee.mrtnh.encrypter_api.repository.EncryptedDataRepository;
import ee.mrtnh.encrypter_api.repository.UserRepository;
import ee.mrtnh.encrypter_api.security.AES;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class EncrypterDataService {

    private static final Logger logger = LoggerFactory.getLogger(EncrypterDataService.class);

    @Resource
    EncryptedDataRepository encryptedDataRepository;

    @Resource
    UserRepository userRepository;

    public void encryptData(@NotNull DataPacket dataPacket) {
        logger.info("Encrypting data");
        User user = new User(dataPacket.getUser(), dataPacket.getPassword());
        addUserToDbIfNecessary(user);

        String encryptedDataString = AES.encrypt(dataPacket.getData(), dataPacket.getPassword());
        EncryptedData encryptedData = new EncryptedData(encryptedDataString, dataPacket.getUser());

        if (encryptedDataAlreadyExistsInDb(encryptedData)) {
            return;
        }
        encryptedDataRepository.save(encryptedData);
    }

    public String decryptData(@NotNull DataPacket dataPacket) {
        logger.info("Decrypting data");
        if (encryptedDataRepository.findDataByEncryptedData(dataPacket.getData()) == null) {
            // TODO: maybe it would be better to create a custom Exception class
            logger.info("Couldn't decrypt data. This data doesn't exist in the database");
            return "Couldn't decrypt data. This data doesn't exist in the database";
        }
        String userName = dataPacket.getUser();
        User user = userRepository.findUserByUserName(userName);
        if (user == null) {
            logger.info("Couldn't decrypt data. No such user exists in database");
            return "Couldn't decrypt data. No such user exists in database";
        }
        User dataPacketUser = new User(dataPacket.getUser(), dataPacket.getPassword());
        if (passwordMatchesPasswordInDatabase(dataPacketUser)) {
            return AES.decrypt(dataPacket.getData(), dataPacket.getPassword());
        } else {
            logger.info("Couldn't decrypt data. User's password doesn't match user's password in database");
            return "Couldn't decrypt data. User's password doesn't match user's password in database";
        }
    }

    public List<EncryptedData> getAllData() {
        logger.info("Fetching all data");
        return encryptedDataRepository.findAll();
    }

    private void addUserToDbIfNecessary(User user) {
        String userName = user.getUserName();
        if (userRepository.findUserByUserName(userName) != null) {
            logger.info("User " + userName + " exists in database");
            return;
        }
        logger.info("Adding user " + userName + " to database");
        userRepository.save(user);
    }

    private boolean passwordMatchesPasswordInDatabase(User user) {
        String userPwd = user.getPassword();
        String userPwdInDb = userRepository.findUserByUserName(user.getUserName()).getPassword();
        if (userPwd.compareTo(userPwdInDb) == 0) {
            logger.info("Password matches the password in database");
            return true;
        }
        logger.info("Password doesn't match the password in database");
        return false;
    }

    private boolean encryptedDataAlreadyExistsInDb(EncryptedData encryptedData) {
        EncryptedData foundEncryptedData = encryptedDataRepository.findDataByEncryptedData(encryptedData.getEncryptedData());
        if (foundEncryptedData != null && foundEncryptedData.getUserName().compareTo(encryptedData.getUserName()) == 0) {
            logger.info("Encrypted data and user pair already exists in database");
            return true;
        }
        logger.info("Encrypted data and user pair doesn't exist in database");
        return false;
    }

}
