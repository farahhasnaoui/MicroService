package com.example.parenttuteur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParentService {
    @Autowired
    private ParentRepo parentRepo;
    @Autowired
    private TuteurRepo tuteurRepo;
    public List<Parent> Parents(){
        return (List<Parent>) parentRepo.findAll();
    }
    public Parent addParent(Parent parent) {
        return parentRepo.save(parent);
    }

    public Parent updateParent(Long id, Parent newParent) {
        if (parentRepo.findById(id).isPresent()) {
            Parent existingParent = parentRepo.findById(id).get();
            existingParent.setFirstName(newParent.getFirstName());
            existingParent.setLastName(newParent.getLastName());


            return parentRepo.save(existingParent);
        } else
            return null;
    }

    public String deleteParent(Long id) {
        if (parentRepo.findById(id).isPresent()) {
            parentRepo.deleteById(id);
            return "parent supprimé";
        } else
            return "parent non supprimé";
    }
    public String AffecterTuteurParent(Long idParent,Long idTuteur){
       Tuteur tuteur= tuteurRepo.findById(idTuteur).get();
       Parent parent = parentRepo.findById(idParent).get();
       parent.getTuteurs().add(tuteur);
       parentRepo.save(parent);
       return "affected";
    }
}
