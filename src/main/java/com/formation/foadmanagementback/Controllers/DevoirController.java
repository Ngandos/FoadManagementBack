package com.formation.foadmanagementback.Controllers;

import com.formation.foadmanagementback.DTO.Devoirs.DevoirCreateDTO;
import com.formation.foadmanagementback.DTO.Devoirs.DevoirDTO;
import com.formation.foadmanagementback.Services.Abstracts.IDevoirsServ;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/devoirs")
public class DevoirController {

    private final IDevoirsServ iDevoirsServ;

    @PostMapping
    public DevoirDTO create(@RequestBody DevoirCreateDTO dto) {
        return iDevoirsServ.create(dto);
    }

    @GetMapping
    public List<DevoirDTO> getAll() {
        return iDevoirsServ.getAll();
    }

    @GetMapping("/{uuid}")
    public DevoirDTO getByUuid(@PathVariable UUID uuid) {
        return iDevoirsServ.getByUuid(uuid);
    }

    @GetMapping("/session/{uuid}")
    public List<DevoirDTO> getBySession(@PathVariable UUID uuid) {
        return iDevoirsServ.getBySession(uuid);
    }

    @GetMapping("/formateur/{uuid}")
    public List<DevoirDTO> getByFormateur(@PathVariable UUID uuid) {
        return iDevoirsServ.getByFormateur(uuid);
    }

}
