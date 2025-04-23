package com.formation.foadmanagementback.Services.Abstracts;

import com.formation.foadmanagementback.DTO.Session.SessionCreateDTO;
import com.formation.foadmanagementback.DTO.Session.SessionDTO;

import java.util.List;
import java.util.UUID;

public interface ISessionsServ {

    List<SessionDTO> getAllSessions();
    SessionDTO getSessionByUuid(UUID uuid);
    SessionDTO createSession(SessionCreateDTO dto);

}
