
package projekti;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@AllArgsConstructor @NoArgsConstructor @Data
public class Picture extends AbstractPersistable<Long> {
    
    @Lob
    private byte[] content;
    
    @OneToOne (mappedBy="picture")
    private Account account;
}
