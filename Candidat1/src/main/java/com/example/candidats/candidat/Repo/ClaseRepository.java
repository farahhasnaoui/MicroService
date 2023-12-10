package com.example.candidats.candidat.Repo;

import com.example.candidats.candidat.entity.clase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClaseRepository extends JpaRepository<clase, Integer> {
    @Query(value = "select * from clase e ORDER BY e.block ASC " ,nativeQuery = true)
    List<clase> Trier();

    @Query("SELECT c, e FROM clase c JOIN Eleves e ON c.id = e.clas.id")
    List<Object[]> findClassesAndStudents();
}
