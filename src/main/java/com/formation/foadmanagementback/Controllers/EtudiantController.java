package com.formation.foadmanagementback.Controllers;

import com.formation.foadmanagementback.DTO.Etudiant.EtudiantCreateDTO;
import com.formation.foadmanagementback.DTO.Etudiant.EtudiantDTO;
import com.formation.foadmanagementback.Services.Abstracts.IEtudiantsServ;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/etudiants")
@SecurityRequirement(name = "BearerAuth")
public class EtudiantController {

    private final IEtudiantsServ iEtudiantsServ;

    public EtudiantController(IEtudiantsServ iEtudiantsServ) {
        this.iEtudiantsServ = iEtudiantsServ;
    }

    @GetMapping
    @PreAuthorize("hasRole('FORMATEUR')")
    public ResponseEntity<List<EtudiantDTO>> getAllEtudiants() {
        List<EtudiantDTO> etudiants = iEtudiantsServ.getAllEtudiants();
        return ResponseEntity.ok(etudiants);
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
