
package projekti;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@AllArgsConstructor @NoArgsConstructor @Data
public class Account extends AbstractPersistable<Long> {
    
    @NotEmpty
    @Size(min=3, max=20)
    @Column(unique=true)
    private String username;
    
    @NotEmpty
    @Size(min=4, max=40)    
    private String name;
    
    @NotEmpty
    @Size(min=3, max=256)
    private String password;
    
    @NotEmpty
    @Size(min=3, max=20)
    @Column(unique=true)
    private String urlIdentifier;
    
}
