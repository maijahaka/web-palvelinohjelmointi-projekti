
package projekti;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {
    
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private AccountRepository accountRepository;
    
    @After
    public void clearDatabase() {
        accountService.clearDatabase();
    }
    
    @Test
    public void AccountIsSaved() {
        Account account = new Account("testuser", "Test Name", "testpassword", "testuser", null);
        accountService.save(account);
        assertEquals(1, accountRepository.count());
    }
    
    @Test
    public void AllAccountsAreFound() {
        String username = "testuser";
        String name = "Test Name";
        String password = "testpassword";
        String urlIdentifier = "testuser";
        
        for (int i = 1; i < 4; i++) {
            Account account = new Account(username+i, name+i, password+i, urlIdentifier + i, null);
            accountService.save(account);
        }
        
        assertEquals(3, accountService.count());
    }
    
}
