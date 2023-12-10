package com.example.candidats.candidat.Service;


import com.example.candidats.candidat.Repo.IProgrammeRepo;
import com.example.candidats.candidat.entity.Programme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProgrammeService {
    @Autowired
    IProgrammeRepo iProgrammeRepo;

    public Programme addProgramme(Programme programme) {
        return iProgrammeRepo.save(programme);
    }

    public Programme updateProgramme(Long id, Programme newProgramme) {
        Optional<Programme> existingProgrammeOptional = iProgrammeRepo.findById(id);
        if (existingProgrammeOptional.isPresent()) {
            Programme existingProgramme = existingProgrammeOptional.get();
            existingProgramme.setNom(newProgramme.getNom());
            existingProgramme.setDescription(newProgramme.getDescription());
            existingProgramme.setObjectif(newProgramme.getObjectif());
            existingProgramme.setNiveau(newProgramme.getNiveau());
            existingProgramme.setDureeEnSemaines(newProgramme.getDureeEnSemaines());
            return iProgrammeRepo.save(existingProgramme);
        } else {
            return null; // Handle the case where the Programme with the given ID is not found
        }
    }

    public String deleteProgramme(Long id) {
        if (iProgrammeRepo.findById(id).isPresent()) {
            iProgrammeRepo.deleteById(id);
            return "Programme Deleted!";
        } else {
            return "Error on Deleting Programme";
        }
    }

    public List<Programme> findAllProgrammes() {
        return iProgrammeRepo.findAll();
    }

    public Optional<Programme> findProgrammeById(Long id) {
        return iProgrammeRepo.findById(id);
    }
    public long getNumberOfPrograms() {
        return iProgrammeRepo.count();
    }
    public double calculateAverageActivitiesPerProgram() {

        Iterable<Programme> programs = iProgrammeRepo.findAll();

        int totalPrograms = 0;
        int totalActivities = 0;


        for (Programme program : programs) {
            totalPrograms++;
            totalActivities += program.getActivites().size();
        }

        if (totalPrograms == 0) {
            return 0;
        } else {
            return (double) totalActivities / totalPrograms;
        }
    }
    public List<Programme> findProgramsWithLongestDuration() {
        List<Programme> allPrograms = iProgrammeRepo.findAll();

        // Sort the programs by duration in descending order
        allPrograms.sort(Comparator.comparing(Programme::getDureeEnSemaines).reversed());

        // Get the top 5 longest programs
        List<Programme> programsWithLongestDuration = allPrograms.stream()
                .limit(5)
                .collect(Collectors.toList());

        return programsWithLongestDuration;
    }

}
