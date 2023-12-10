package com.example.candidats.candidat.Service;

import com.example.candidats.candidat.Controller.ContratNotFoundException;
import com.example.candidats.candidat.Controller.EnseignantNotFoundException;
import com.example.candidats.candidat.Repo.ContratRepository;
import com.example.candidats.candidat.Repo.EnseignantRepository;
import com.example.candidats.candidat.entity.Contrat;
import com.example.candidats.candidat.entity.Enseignant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EnseignantService {

    @Autowired
    private EnseignantRepository enseignantRepository;
    @Autowired
    private ContratRepository contratRepository;

    public Enseignant addEnseignant(Enseignant enseignant) {
        return enseignantRepository.save(enseignant);
    }

    public Enseignant updateEnseignant(Long id, Enseignant newEnseignant) {
        if (enseignantRepository.findById(id).isPresent()) {
            Enseignant existingEnseignant = enseignantRepository.findById(id).get();
            existingEnseignant.setNom(newEnseignant.getNom());
            existingEnseignant.setPrenom(newEnseignant.getPrenom());
            existingEnseignant.setEmail(newEnseignant.getEmail());

            return enseignantRepository.save(existingEnseignant);
        } else {
            return null; // Handle not found case
        }
    }

    public Enseignant createEnseignantWithExistingContrat(Enseignant enseignant, Long contratId) {
        // Assurez-vous que l'enseignant est correctement configuré.

        // Recherchez le contrat existant par son ID.
        Optional<Contrat> contratOptional = contratRepository.findById(contratId);

        if (contratOptional.isPresent()) {
            Contrat existingContrat = contratOptional.get();

            // Associez le contrat existant à l'enseignant.
            enseignant.setContrat( existingContrat); // Utilisez setContrat au lieu de setContrats

            // Enregistrez l'enseignant avec le contrat associé.
            return enseignantRepository.save(enseignant);
        } else {
            // Gérez l'erreur si le contrat n'est pas trouvé.
            throw new ContratNotFoundException("Le contrat avec l'ID " + contratId + " n'a pas été trouvé.");
        }
    }






    public String deleteEnseignant(Long id) throws EnseignantNotFoundException {
        Optional<Enseignant> optional = enseignantRepository.findById(id);
        if (optional.isPresent()) {
            enseignantRepository.deleteById(id);
            return "Enseignant deleted.";
        } else {
            throw new EnseignantNotFoundException("Enseignant with ID " + id + " not found.");
        }
    }


    @Autowired
    public EnseignantService(EnseignantRepository enseignantRepository) {
        this.enseignantRepository = enseignantRepository;
    }

    public List<Enseignant> getAllEnseignantsWithContrats() {
        return enseignantRepository.findAll(); // Create this custom query method in your repository.
    }
    public Enseignant getEnseignantById(Long id) throws EnseignantNotFoundException {
        Optional<Enseignant> enseignantOptional = enseignantRepository.findById(id);
        if (enseignantOptional.isPresent()) {
            return enseignantOptional.get();
        } else {
            throw new EnseignantNotFoundException("Enseignant not found with ID: " + id);
        }
    }



}