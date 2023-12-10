package com.example.candidats.candidat.Service;
import com.example.candidats.candidat.Repo.ContratRepository;
import com.example.candidats.candidat.entity.Contrat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;

@Service
public class ContratService {
    @Autowired
    private EnseignantService enseignantService;

    @Autowired
    private ContratRepository contratRepository;

    public Contrat addContrat(Contrat contrat) {
        return contratRepository.save(contrat);
    }

    public List<Contrat> getAllContrats() {
        return contratRepository.findAll();
    }

    public Optional<Contrat> getContratById(Long id) {
        return contratRepository.findById(id);
    }

    public Optional<Contrat> updateContrat(Long id, Contrat newContrat) {
        if (contratRepository.existsById(id)) {
            newContrat.setId(id);
            return Optional.of(contratRepository.save(newContrat));
        } else {
            return Optional.empty(); // Handle not found case
        }
    }


    public String deleteContrat(Long id) {
        if (contratRepository.existsById(id)) {
            contratRepository.deleteById(id);
            return "Contrat supprimé";
        }
        return "Contrat non supprimé"; // Handle not found case
    }



}
