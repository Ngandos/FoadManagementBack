package com.formation.foadmanagementback.Controllers;

import com.formation.foadmanagementback.DTO.Certification.CertificationCreateDTO;
import com.formation.foadmanagementback.DTO.Certification.CertificationDTO;
import com.formation.foadmanagementback.Services.Abstracts.ICertificationsServ;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/certifications")
public class CertificationController {

    private final ICertificationsServ iCertificationsServ;

    public CertificationController(ICertificationsServ iCertificationsServ) {
        this.iCertificationsServ = iCertificationsServ;
    }

    @GetMapping
    public List<CertificationDTO> getAll() {
        return iCertificationsServ.getAll();
    }

    @GetMapping("/{uuid}")
    public CertificationDTO getByUuid(@PathVariable UUID uuid) {
        return iCertificationsServ.getByUuid(uuid);
    }

    @PostMapping
    public CertificationDTO create(@RequestBody CertificationCreateDTO dto) {
        return iCertificationsServ.create(dto);
    }

}
