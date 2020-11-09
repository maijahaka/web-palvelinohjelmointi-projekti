
package projekti;

import org.junit.After;
import static org.junit.Assert.assertEquals;
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
public class RegistrationControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private AccountService accountService;
    
    @After
    public void clearDatabase() {
        accountService.clearDatabase();
    }

    @Test
    public void statusOk() throws Exception {
        mockMvc.perform(get("/registration"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void responseContainsTextCreateUsername() throws Exception {
        MvcResult res = mockMvc.perform(get("/registration")).andReturn();
        
        String content = res.getResponse().getContentAsString();
        assertTrue(content.contains("Luo käyttäjätunnus"));
    }
    
    @Test
    public void redirectedAfterPostRequest() throws Exception {
        mockMvc.perform(post("/registration")
                .param("name", "Test Name")
                .param("username", "testuser")
                .param("password", "testpassword")
                .param("urlIdentifier", "testuser"))
                .andExpect(redirectedUrl("/success"));
    }
    
    @Test
    public void createdAccountIsAddedToDatabase() throws Exception {
        mockMvc.perform(post("/registration")
                .param("name", "Test Name")
                .param("username", "testuser")
                .param("password", "testpassword")
                .param("urlIdentifier", "testuser"))
                .andReturn();

        assertEquals(1, accountService.count());
    }
    
    @Test
    public void createdAccountIsFoundFromDatabase() throws Exception {
        mockMvc.perform(post("/registration")
                .param("name", "Test Name")
                .param("username", "testuser")
                .param("password", "testpassword")
                .param("urlIdentifier", "testuser"))
                .andReturn();

        assertTrue(accountService.findAll().stream()
                .anyMatch(account -> account.getUsername().equals("testuser")));
    }
    
}
