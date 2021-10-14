package kg.easy.referralservice.services;

import kg.easy.referralservice.models.dto.SubscriberDto;

public interface SubscriberService {
    SubscriberDto saveSubscriberIfNotExists(SubscriberDto sender);
    SubscriberDto findByPhone(Long phone);
}
