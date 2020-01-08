package ee.mrtnh.encrypter_api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.mrtnh.encrypter_api.model.DataPacket;
import ee.mrtnh.encrypter_api.services.EncrypterDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EncrypterApiController.class)
class EncrypterApiControllerTest {
    // from https://reflectoring.io/spring-boot-web-controller-test/

    @MockBean // Mocks the bean. Doesn't create an actual instance of EncrypterDataService
    EncrypterDataService encrypterDataService;

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {
    }

    @Test
    void decryptDataUsingJson() throws Exception {
        DataPacket dataPacket = new DataPacket("data1", "user1", "password1");
        mockMvc.perform(post("/decrypt").content(mapper.writeValueAsString(dataPacket)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        // test that endpoint works. service level / business logic is tested in EncrypterDataServiceTest
    }

    @Test
    void encryptDataUsingJson() throws Exception {
        DataPacket dataPacket = new DataPacket("data1", "user1", "password1");
        mockMvc.perform(post("/encrypt").content(mapper.writeValueAsString(dataPacket)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAllDataRowsUsingJson() throws Exception {
        mockMvc.perform(post("/getAll").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}