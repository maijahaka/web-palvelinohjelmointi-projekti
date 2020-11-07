
package projekti;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SuccessControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void statusOk() throws Exception {
        mockMvc.perform(get("/success"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void responseContainsTextUsernameCreted() throws Exception {
        MvcResult res = mockMvc.perform(get("/success")).andReturn();
        
        String content = res.getResponse().getContentAsString();
        assertTrue(content.contains("Käyttäjätunnus luotu"));
    }
       
}
