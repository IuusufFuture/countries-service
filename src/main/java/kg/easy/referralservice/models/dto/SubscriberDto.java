package kg.easy.referralservice.models.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubscriberDto {
    Long subsId;
    Long phone;
    boolean active;
    Date editDate;
}
