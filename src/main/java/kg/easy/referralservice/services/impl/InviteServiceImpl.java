package kg.easy.referralservice.services.impl;

import kg.easy.referralservice.dao.InviteRepo;
import kg.easy.referralservice.mappers.InviteMapper;
import kg.easy.referralservice.mappers.SubscriberMapper;
import kg.easy.referralservice.models.dto.InviteDto;
import kg.easy.referralservice.models.dto.ReceiverInviteDto;
import kg.easy.referralservice.models.dto.SubscriberDto;
import kg.easy.referralservice.models.enities.Invite;
import kg.easy.referralservice.models.enums.InviteStatus;
import kg.easy.referralservice.models.responses.ErrorResponses;
import kg.easy.referralservice.services.InviteService;
import kg.easy.referralservice.services.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Objects;

@Service
public class InviteServiceImpl implements InviteService {

    @Autowired
    private SubscriberService subscriberService;
    @Autowired
    private InviteRepo inviteRepo;

    @Override
    public InviteDto sendInvite(InviteDto inviteDto) {
        SubscriberDto sender = subscriberService.saveSubscriberIfNotExists(inviteDto.getSender());
        SubscriberDto receiver = subscriberService.saveSubscriberIfNotExists(inviteDto.getReceiver());

        Calendar startDay = Calendar.getInstance();
        startDay.set(Calendar.HOUR_OF_DAY, 0);
        startDay.set(Calendar.MINUTE, 0);
        startDay.set(Calendar.SECOND, 0);
        startDay.set(Calendar.MILLISECOND, 0);
        Calendar endDay = (Calendar) startDay.clone();
        startDay.add(Calendar.MILLISECOND, 86_399999);
        int countOfDaily = inviteRepo.countBySenderAndStartDateBetween(
                SubscriberMapper.INSTANCE.toSubscriber(sender),
                startDay.getTime(),
                endDay.getTime()
        );
        if(countOfDaily > 5) {
            throw new RuntimeException("hello");
        }
        Calendar startMonth = (Calendar) startDay.clone();
        startDay.set(Calendar.DAY_OF_MONTH, 1);
        Calendar endMonth = (Calendar) startMonth.clone();
        endMonth.add(Calendar.DAY_OF_MONTH, 29);
        // Down
        int countOfMonthly = inviteRepo.countBySenderAndStartDateBetween(
                SubscriberMapper.INSTANCE.toSubscriber(sender),
                startMonth.getTime(),
                endMonth.getTime()
        );
        if(countOfMonthly > 30) {
            throw new RuntimeException("hello");
        }
        Invite lastInvite = inviteRepo.findByReceiverAndStatus(
                SubscriberMapper.INSTANCE.toSubscriber(receiver),
                InviteStatus.ACTIVE
        );
        if(Objects.nonNull(lastInvite)) {
            lastInvite.setStatus(InviteStatus.DEACTIVATED);
            inviteRepo.save(lastInvite);
        }
        InviteDto actualInvite = new InviteDto();
        actualInvite.setSender(sender);
        actualInvite.setReceiver(receiver);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MILLISECOND, 86_400_000);
        actualInvite.setEndDate(calendar.getTime());
        actualInvite.setStatus(InviteStatus.ACTIVE);
        lastInvite = inviteRepo.save(InviteMapper.INSTANCE.toInvite(actualInvite));
        return InviteMapper.INSTANCE.toInviteDto(lastInvite);
    }

    @Override
    public ResponseEntity<?> getLastInvite(Long phone) {
        SubscriberDto receiver = subscriberService.findByPhone(phone);
        if(Objects.isNull(receiver)) {
            return new ResponseEntity<>(new ErrorResponses("User not found", "Call call centre"), HttpStatus.NOT_FOUND);
        }
        Invite status = inviteRepo.findByReceiverAndStatus(SubscriberMapper.INSTANCE.toSubscriber(receiver), InviteStatus.ACTIVE);
        if(Objects.isNull(status)) {
            return ResponseEntity.ok(new ErrorResponses("No invites found", null));
        }
        return ResponseEntity.ok(InviteMapper.INSTANCE.toReceiverInviteDto(status));
    }
}
