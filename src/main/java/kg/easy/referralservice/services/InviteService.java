package kg.easy.referralservice.services;

import kg.easy.referralservice.models.dto.InviteDto;
import kg.easy.referralservice.models.dto.ReceiverInviteDto;
import org.springframework.http.ResponseEntity;

public interface InviteService {
    InviteDto sendInvite(InviteDto inviteDto);

    ResponseEntity<?> getLastInvite(Long phone);
}
