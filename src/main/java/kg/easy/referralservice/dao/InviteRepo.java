package kg.easy.referralservice.dao;

import kg.easy.referralservice.models.enities.Invite;
import kg.easy.referralservice.models.enities.Subscriber;
import kg.easy.referralservice.models.enums.InviteStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;

@Repository
public interface InviteRepo extends JpaRepository<Invite, Long> {
    int countBySenderAndStartDateBetween(Subscriber sender, Date startDate, Date endDate);
    Invite findByReceiverAndStatus(Subscriber subscriber, InviteStatus inviteStatus);
}
