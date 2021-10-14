package kg.easy.referralservice.dao;

import kg.easy.referralservice.models.enities.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriberRepo extends JpaRepository<Subscriber, Long> {
    Subscriber findByPhone(Long phone);
}
