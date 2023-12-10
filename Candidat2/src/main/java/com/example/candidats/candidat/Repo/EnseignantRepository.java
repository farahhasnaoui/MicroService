package com.example.candidats.candidat.Repo;

import com.example.candidats.candidat.entity.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
public interface EnseignantRepository extends JpaRepository<Enseignant,Long> {

}
