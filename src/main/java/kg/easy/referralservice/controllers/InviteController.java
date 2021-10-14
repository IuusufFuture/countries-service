package kg.easy.referralservice.controllers;

import kg.easy.referralservice.models.dto.InviteDto;
import kg.easy.referralservice.models.dto.ReceiverInviteDto;
import kg.easy.referralservice.services.InviteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/invite")
public class InviteController {

    private InviteService inviteService;

    public InviteController(InviteService inviteService) {
        this.inviteService = inviteService;
    }

    @PostMapping("/send")
    public InviteDto send(@RequestBody InviteDto inviteDto) {
        return inviteService.sendInvite(inviteDto);
    }

    @GetMapping("/getInvites")
    public ResponseEntity<?> getLastInvite(@RequestParam Long phone) {
        return inviteService.getLastInvite(phone);
    }
}