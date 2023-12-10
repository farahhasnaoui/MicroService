package com.example.candidats.candidat.Service;

import com.example.candidats.candidat.Repo.IActiviteRepo;
import com.example.candidats.candidat.entity.Activite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

@Service
public class ActiviteService {
    @Autowired
    private IActiviteRepo activiteRepository;

    public Activite addActivite(Activite activite) {
        return activiteRepository.save(activite);
    }

    public List<Activite> findAllActivites() {
        return activiteRepository.findAll();
    }

    public Optional<Activite> findActiviteById(Long id) {
        return activiteRepository.findById(id);
    }

    public Activite updateActivite(Long id, Activite newActivite) {
        Optional<Activite> existingActiviteOptional = activiteRepository.findById(id);
        if (existingActiviteOptional.isPresent()) {
            Activite existingActivite = existingActiviteOptional.get();
            existingActivite.setNom(newActivite.getNom());
            existingActivite.setDescription(newActivite.getDescription());
            existingActivite.setDateDebut(newActivite.getDateDebut());
            existingActivite.setDateFin(newActivite.getDateFin());
            existingActivite.setLieu(newActivite.getLieu());
            existingActivite.setObligatoire(newActivite.isObligatoire());

            return activiteRepository.save(existingActivite);
        } else {
            return null;
        }
    }

    public String deleteActivite(Long id) {
        if (activiteRepository.findById(id).isPresent()) {
            activiteRepository.deleteById(id);
            return "Activite Deleted!";
        } else {
            return "Error on Deleting Activite";
        }
    }

    public double calculateAverageActivityDuration() {
        List<Activite> activities = activiteRepository.findAll();
        if (activities.isEmpty()) {
            return 0.0;
        }

        long totalDurationMillis = 0;
        for (Activite activity : activities) {
            long durationMillis = activity.getDateFin().getTime() - activity.getDateDebut().getTime();
            totalDurationMillis += durationMillis;
        }

        double averageDurationMinutes = (double) totalDurationMillis / (activities.size() * 1000 * 60);
        return averageDurationMinutes;
    }

    public long getNumberOfActivities() {
        return activiteRepository.count();
    }

    public double calculateAverageActivitiesPerDay() {
        List<Activite> activities = activiteRepository.findAll();
        if (activities.isEmpty()) {
            return 0.0;
        }

        Map<String, Integer> activitiesPerDay = new HashMap<>();

        for (Activite activity : activities) {
            String activityDate = activity.getDateDebut().toString(); // You may need to format this as per your requirements
            activitiesPerDay.put(activityDate, activitiesPerDay.getOrDefault(activityDate, 0) + 1);
        }

        int totalActivities = activitiesPerDay.values().stream().mapToInt(Integer::intValue).sum();
        int totalDays = activitiesPerDay.size();

        return (double) totalActivities / totalDays;
    }

    public long calculateMaximumActivityDuration() {
        List<Activite> activities = activiteRepository.findAll();
        if (activities.isEmpty()) {
            return 0;
        }

        long maxDurationMinutes = 0;

        for (Activite activity : activities) {
            long durationMillis = activity.getDateFin().getTime() - activity.getDateDebut().getTime();
            long durationMinutes = durationMillis / (1000 * 60);
            maxDurationMinutes = Math.max(maxDurationMinutes, durationMinutes);
        }

        return maxDurationMinutes;
    }
}
