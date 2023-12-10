package com.example.candidats.candidat.Repo;

import com.example.candidats.candidat.entity.Eleves;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElevesRepository  extends JpaRepository<Eleves, Integer> {
    @Query("select c from Eleves c where c.nom like :name")
    public Page<Eleves> candidatByNom(@Param("name") String n, Pageable pageable);

    @Query(value = "SELECT * FROM Eleves e WHERE e.nom LIKE %:keyword% OR e.email LIKE %:keyword%", nativeQuery = true)
    List<Eleves> search(@Param("keyword") String keyword);
}