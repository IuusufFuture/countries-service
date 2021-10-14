package kg.easy.referralservice.models.enities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "subscribers")
public class Subscriber {
    @Id
    Long subsId;
    @Column(unique = true)
    Long phone;
    boolean active;
    @UpdateTimestamp
    Date editDate;
}
