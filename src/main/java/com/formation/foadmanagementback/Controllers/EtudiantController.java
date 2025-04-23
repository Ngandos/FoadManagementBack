package com.formation.foadmanagementback.Controllers;

import com.formation.foadmanagementback.DTO.Etudiant.EtudiantCreateDTO;
import com.formation.foadmanagementback.DTO.Etudiant.EtudiantDTO;
import com.formation.foadmanagementback.Services.Abstracts.IEtudiantsServ;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/etudiants")
public class EtudiantController {

    private final IEtudiantsServ iEtudiantsServ;

    public EtudiantController(IEtudiantsServ iEtudiantsServ) {
        this.iEtudiantsServ = iEtudiantsServ;
    }

    @GetMapping
    public List<EtudiantDTO> getAll() {
        return iEtudiantsServ.getAllEtudiants();
    }

    @GetMapping("/{uuid}")
    public EtudiantDTO getByUuid(@PathVariable UUID uuid) {
        return iEtudiantsServ.getEtudiantByUuid(uuid);
    }

    @PostMapping
    public EtudiantDTO create(@RequestBody EtudiantCreateDTO dto) {
        return iEtudiantsServ.createEtudiant(dto);
    }

}
