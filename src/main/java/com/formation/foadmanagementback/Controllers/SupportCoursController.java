package com.formation.foadmanagementback.Controllers;


import com.formation.foadmanagementback.DTO.SupportCours.SupportCoursCreateDTO;
import com.formation.foadmanagementback.DTO.SupportCours.SupportCoursDTO;
import com.formation.foadmanagementback.Services.Abstracts.ISupportsCoursServ;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/supportsCours")
public class SupportCoursController {

    private final ISupportsCoursServ iSupportCoursServ;

    @PostMapping
    public SupportCoursDTO create(@RequestBody SupportCoursCreateDTO dto) {
        return iSupportCoursServ.create(dto);
    }

    @GetMapping
    public List<SupportCoursDTO> getAll() {
        return iSupportCoursServ.getAll();
    }

    @GetMapping("/{uuid}")
    public SupportCoursDTO getByUuid(@PathVariable UUID uuid) {
        return iSupportCoursServ.getByUuid(uuid);
    }

    @GetMapping("/session/{uuid}")
    public List<SupportCoursDTO> getBySession(@PathVariable UUID uuid) {
        return iSupportCoursServ.getBySession(uuid);
    }

    @GetMapping("/formateur/{uuid}")
    public List<SupportCoursDTO> getByFormateur(@PathVariable UUID uuid) {
        return iSupportCoursServ.getByFormateur(uuid);
    }

}
