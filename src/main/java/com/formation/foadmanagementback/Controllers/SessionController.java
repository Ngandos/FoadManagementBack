package com.formation.foadmanagementback.Controllers;

import com.formation.foadmanagementback.DTO.Session.SessionCreateDTO;
import com.formation.foadmanagementback.DTO.Session.SessionDTO;
import com.formation.foadmanagementback.Services.Abstracts.ISessionsServ;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sessions")
@SecurityRequirement(name = "BearerAuth")
public class SessionController {

    private final ISessionsServ iSessionsServ;

    public SessionController(ISessionsServ iSessionsServ) {
        this.iSessionsServ = iSessionsServ;
    }

    @GetMapping
    public List<SessionDTO> getAll() {
        return iSessionsServ.getAllSessions();
    }

    @GetMapping("/{uuid}")
    public SessionDTO getByUuid(@PathVariable UUID uuid) {
        return iSessionsServ.getSessionByUuid(uuid);
    }

    @PostMapping
    public SessionDTO create(@RequestBody SessionCreateDTO dto) {
        return iSessionsServ.createSession(dto);
    }

}
