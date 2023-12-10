package com.example.candidats.candidat.Repo;

import com.example.candidats.candidat.entity.Candidat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatRepository extends JpaRepository<Candidat, Integer> {
    @Query("select c from Candidat c where c.nom like :nom")
    public Page<Candidat> candidatByNom(@Param("nom") String n, Pageable pageable);

}
