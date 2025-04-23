package com.formation.foadmanagementback.Controllers;

import com.formation.foadmanagementback.DTO.Formation.FormationCreateDTO;
import com.formation.foadmanagementback.DTO.Formation.FormationDTO;
import com.formation.foadmanagementback.Services.Abstracts.IFormationsServ;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/formations")
public class FormationController {

    private final IFormationsServ iFormationsServ;

    public FormationController(IFormationsServ iFormationsServ) {
        this.iFormationsServ = iFormationsServ;
    }

    @GetMapping
    public List<FormationDTO> getAll() {
        return iFormationsServ.getAllFormations();
    }

    @GetMapping("/{uuid}")
    public FormationDTO getByUuid(@PathVariable UUID uuid) {
        return iFormationsServ.getFormationByUuid(uuid);
    }

    @PostMapping
    public FormationDTO create(@RequestBody FormationCreateDTO dto) {
        return iFormationsServ.createFormation(dto);
    }
}
