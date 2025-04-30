package com.formation.foadmanagementback.Controllers;

import com.formation.foadmanagementback.DTO.Notification.NotificationDTO;
import com.formation.foadmanagementback.Services.Abstracts.INotificationsServ;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {

    private final INotificationsServ iNotificationService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/non-lues/{uuid}")
    public ResponseEntity<List<NotificationDTO>> getNonLues(@PathVariable UUID uuid) {
        return ResponseEntity.ok(iNotificationService.getNonLuesParDestinataireUuid(uuid));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/recents/{uuid}")
    public ResponseEntity<List<NotificationDTO>> getRecents(
        @PathVariable UUID uuid, @RequestParam(defaultValue = "5") int limit) {
        return ResponseEntity.ok(iNotificationService.getRecentsParDestinataireUuid(uuid, limit));
    }


}
