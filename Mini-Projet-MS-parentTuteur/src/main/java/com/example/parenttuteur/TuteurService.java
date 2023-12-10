package com.example.parenttuteur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TuteurService {
    @Autowired
    private TuteurRepo tuteurRepo;
    @Autowired
    private ParentRepo parentRepo;

    public List<Tuteur> getAllTuteurs() {
        return (List<Tuteur>) tuteurRepo.findAll();
    }

    public Tuteur addTuteur(Tuteur tuteur) {
        return tuteurRepo.save(tuteur);
    }

    public Tuteur updateTuteur(Long id, Tuteur newTuteur) {

        if (tuteurRepo.findById(id).isPresent()) {
            Tuteur existingTuteur = tuteurRepo.findById(id).get();
            existingTuteur.setFirstName(newTuteur.getFirstName());
            existingTuteur.setLastName(newTuteur.getLastName());

            return tuteurRepo.save(existingTuteur);
        } else {
            return null;
        }
    }

    public String deleteTuteur(Long id) {
        if (tuteurRepo.existsById(id)) {
            for (Parent p:parentRepo.findAll()
                 ) {
                if(p.getTuteurs().contains(tuteurRepo.findById(id))){
                    p.getTuteurs().remove(tuteurRepo.findById(id));
                    parentRepo.save(p);
                }
            }
            tuteurRepo.deleteById(id);
            return "Tuteur supprimé";
        } else {
            return "Tuteur non supprimé";
        }
    }
}
