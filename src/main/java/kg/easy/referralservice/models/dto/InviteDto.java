package kg.easy.referralservice.models.dto;

import kg.easy.referralservice.models.enities.Subscriber;
import kg.easy.referralservice.models.enums.InviteStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InviteDto {
    Long id;
    SubscriberDto sender;
    SubscriberDto receiver;
    Date startDate;
    Date endDate;
    InviteStatus status;
}
