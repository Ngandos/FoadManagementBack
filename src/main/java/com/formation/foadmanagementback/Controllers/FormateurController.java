package com.formation.foadmanagementback.Controllers;

import com.formation.foadmanagementback.Entities.Formateur;
import com.formation.foadmanagementback.Repositories.IFormateursRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formateurs")
public class FormateurController {

    public final IFormateursRepository formateursRepository;

    public FormateurController(IFormateursRepository formateursRepository) {
        this.formateursRepository = formateursRepository;
    }

    @GetMapping
    public List<Formateur> getAll() {
        return formateursRepository.findAll();
    }

    @PostMapping
    public Formateur create(@RequestBody Formateur formateur) {
        return formateursRepository.save(formateur);
    }
}
