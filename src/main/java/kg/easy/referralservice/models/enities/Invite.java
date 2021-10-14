package kg.easy.referralservice.models.enities;

import kg.easy.referralservice.models.enums.InviteStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "invites")
public class Invite {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    Subscriber sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    Subscriber receiver;

    @CreationTimestamp
    Date startDate;

    Date endDate;

    @Enumerated(value = EnumType.STRING)
    InviteStatus status;
}
