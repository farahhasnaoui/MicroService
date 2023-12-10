package com.example.candidats.candidat.Repo;

import com.example.candidats.candidat.entity.Programme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProgrammeRepo extends JpaRepository<Programme, Long> {
}
