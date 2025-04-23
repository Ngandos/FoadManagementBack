package com.formation.foadmanagementback.Repositories;

import com.formation.foadmanagementback.Entities.Formateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFormateursRepository extends JpaRepository<Formateur, Long> {
}
