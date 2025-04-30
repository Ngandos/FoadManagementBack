package com.formation.foadmanagementback.Controllers;

import com.formation.foadmanagementback.DTO.Message.MessageCreateDTO;
import com.formation.foadmanagementback.DTO.Message.MessageDTO;
import com.formation.foadmanagementback.Services.Abstracts.IMessageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/messages")
@SecurityRequirement(name = "BearerAuth")
public class MessageController {

    private final IMessageService iMessageService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'FORMATEUR')")
    public MessageDTO sendMessage(@RequestBody MessageCreateDTO dto) {
        return iMessageService.sendMessage(dto);
    }

    @GetMapping("/{uuid}")
    public MessageDTO getMessageByUuid(@PathVariable UUID uuid) {
        return iMessageService.getByUuid(uuid);
    }

    @GetMapping("/sent/{uuid}")
    public List<MessageDTO> getMessagesSent(@PathVariable UUID uuid) {
        return iMessageService.getAllMessagesSent(uuid);
    }

    @GetMapping("/received/{uuid}")
    public List<MessageDTO> getMessagesReceived(@PathVariable UUID uuid) {
        return iMessageService.getAllMessagesReceived(uuid);
    }

    @GetMapping("/conversation")
    public List<MessageDTO> getConversation(
            @RequestParam UUID expediteurUuid,
            @RequestParam UUID destinataireUuid
    ) {
        return iMessageService.getConversation(expediteurUuid, destinataireUuid);
    }

    @PatchMapping("/{uuid}/lu")
    public void marquerCommeLu(@PathVariable UUID uuid) {
        iMessageService.marquerCommeLu(uuid);
    }

}
