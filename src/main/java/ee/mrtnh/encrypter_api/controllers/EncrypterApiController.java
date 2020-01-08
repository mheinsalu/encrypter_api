package ee.mrtnh.encrypter_api.controllers;

import ee.mrtnh.encrypter_api.model.DataPacket;
import ee.mrtnh.encrypter_api.model.EncryptedData;
import ee.mrtnh.encrypter_api.services.EncrypterDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class EncrypterApiController {

    private static final Logger logger = LoggerFactory.getLogger(EncrypterApiController.class);

    @Resource
    private EncrypterDataService encrypterDataService;

    @RequestMapping(value = "/decrypt", method = RequestMethod.POST) // on external Tomcat the url is <artifact name> + value
    @ResponseBody
    // The @ResponseBody annotation tells a controller that the object returned is automatically serialized into JSON and passed back into the HttpResponse object.
    public String decryptDataUsingJson(@RequestBody DataPacket dataPacket) {
        logger.info("Called decryptDataUsingJson");
        return encrypterDataService.decryptData(dataPacket);
    }

    @RequestMapping(value = "/encrypt", method = RequestMethod.POST)
    @ResponseBody
    public List<EncryptedData> encryptDataUsingJson(@RequestBody DataPacket dataPacket) {
        // @RequestBody see https://www.baeldung.com/spring-thymeleaf-request-parameters
        logger.info("Called encryptDataUsingJson");
        encrypterDataService.encryptData(dataPacket);
        List<EncryptedData> list = encrypterDataService.getAllData();
        return encrypterDataService.getAllData();
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.POST)
    @ResponseBody
    public List<EncryptedData> getAllDataRowsUsingJson() {
        logger.info("Called getAllDataRowsUsingJson");
        return encrypterDataService.getAllData(); // Returns JSON array e.g.  [ {"data": "sfgsg, "user": "sdgsdg", "password": "sadgsfg"} ]
    }

}
