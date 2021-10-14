package kg.easy.referralservice.services.impl;

import kg.easy.referralservice.dao.SubscriberRepo;
import kg.easy.referralservice.mappers.SubscriberMapper;
import kg.easy.referralservice.models.dto.SubscriberDto;
import kg.easy.referralservice.models.enities.Subscriber;
import kg.easy.referralservice.services.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriberServiceImpl implements SubscriberService {

    @Autowired
    private SubscriberRepo subscriberRepo;

    @Override
    public SubscriberDto saveSubscriberIfNotExists(SubscriberDto sender) {
        Subscriber subscriber = SubscriberMapper.INSTANCE.toSubscriber(sender);
        if(!subscriberRepo.existsById(subscriber.getSubsId())) {
            subscriber = subscriberRepo.save(subscriber);
        }
        return SubscriberMapper.INSTANCE.toSubscriberDto(subscriber);
    }

    @Override
    public SubscriberDto findByPhone(Long phone) {
        Subscriber subscriber = subscriberRepo.findByPhone(phone);
        return SubscriberMapper.INSTANCE.toSubscriberDto(subscriber);
    }
}
