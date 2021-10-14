package kg.easy.referralservice.mappers;

import kg.easy.referralservice.models.dto.InviteDto;
import kg.easy.referralservice.models.dto.ReceiverInviteDto;
import kg.easy.referralservice.models.enities.Invite;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InviteMapper {

    InviteMapper INSTANCE = Mappers.getMapper(InviteMapper.class);

    Invite toInvite(InviteDto inviteDto);
    InviteDto toInviteDto(Invite invite);

    @Mapping(source = "invite.sender.phone", target = "senderPhone")
    ReceiverInviteDto toReceiverInviteDto(Invite invite);
}
