package com.example.candidats.candidat.Service;


import com.example.candidats.candidat.Repo.ClaseRepository;
import com.example.candidats.candidat.entity.clase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaseService {

    @Autowired
    private ClaseRepository claseRepository;



    public List<clase> getAllClasse() {
        return claseRepository.findAll();
    }

    public clase addClasse(clase clase) {
        return claseRepository.save(clase);
    }



    public clase updateClasse(int id, clase newclase) {
        if (claseRepository.findById(id).isPresent()) {
            clase existingClasse = claseRepository.findById(id).get();
            existingClasse.setNom(newclase.getNom());
            existingClasse.setEtage(newclase.getEtage());
            existingClasse.setBlock(newclase.getBlock());

            return claseRepository.save(existingClasse);
        } else
            return null;
    }


    public String deleteClasses(int id) {
        if (claseRepository.findById(id).isPresent()) {
            claseRepository.deleteById(id);
            return "classe supprimé";
        } else
            return "classe non supprimé";
    }

    public List<clase> sortClase() {
        return claseRepository.Trier();
    }

    public List<Object[]> getClassesAndStudents() {
        return claseRepository.findClassesAndStudents();
    }
}
