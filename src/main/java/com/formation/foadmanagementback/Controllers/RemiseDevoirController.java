package com.formation.foadmanagementback.Controllers;

import com.formation.foadmanagementback.DTO.RemiseDevoir.RemiseDevoirCreateDTO;
import com.formation.foadmanagementback.DTO.RemiseDevoir.RemiseDevoirDTO;
import com.formation.foadmanagementback.Services.Abstracts.IRemisesDevoirsServ;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/remises")
public class RemiseDevoirController {

    private final IRemisesDevoirsServ iRemisesDevoirsServ;

    @PostMapping
    @PreAuthorize("hasRole('ETUDIANT')")
    public RemiseDevoirDTO create(@RequestBody RemiseDevoirCreateDTO dto) {
        return iRemisesDevoirsServ.create(dto);
    }

    @GetMapping("/{uuid}")
    public RemiseDevoirDTO getByUuid(@PathVariable UUID uuid) {
        return iRemisesDevoirsServ.getByUuid(uuid);
    }

    @GetMapping("/etudiant/{uuid}")
    public List<RemiseDevoirDTO> getByEtudiant(@PathVariable UUID uuid) {
        return iRemisesDevoirsServ.getByEtudiant(uuid);
    }

    @GetMapping("/devoir/{uuid}")
    public List<RemiseDevoirDTO> getByDevoir(@PathVariable UUID uuid) {
        return iRemisesDevoirsServ.getByDevoir(uuid);
    }

}
