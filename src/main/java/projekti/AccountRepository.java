
package projekti;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    public Account findByUsername(String username);
    public Account findByUrlIdentifier(String urlIdentifier);
    
}
