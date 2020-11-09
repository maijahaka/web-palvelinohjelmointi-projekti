
package projekti;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    
    @Autowired
    private AccountRepository accountRepository;
    
    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }
    
    public Account save(Account account) {
        return accountRepository.save(account);
    }
    
    public List<Account> findAll() {
        return accountRepository.findAll();
    }
    
    public long count() {
        return accountRepository.count();
    }
    
    public boolean isUsernameAlreadyInUse(String value) {
        return accountRepository.findByUsername(value) != null;
    }
    
    public boolean isUrlIdentifierAlreadyInUse(String value) {
        return accountRepository.findByUrlIdentifier(value) != null;
    }

    public void clearDatabase() {
        accountRepository.deleteAll();
    }
    
}
