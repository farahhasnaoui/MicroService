package com.example.candidats.candidat.Repo;

import com.example.candidats.candidat.entity.Activite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IActiviteRepo extends JpaRepository<Activite, Long> {
}
