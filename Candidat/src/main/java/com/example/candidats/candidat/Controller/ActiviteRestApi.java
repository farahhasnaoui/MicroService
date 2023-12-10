package com.example.candidats.candidat.Controller;

import com.example.candidats.candidat.Service.ActiviteService;
import com.example.candidats.candidat.entity.Activite;
import com.example.candidats.candidat.entity.Programme;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/candidats")
public class ActiviteRestApi {
    @Autowired
    private ActiviteService activiteService;

//    @PostMapping
//    public ResponseEntity<Activite> createActivite(@RequestBody Activite activite) {
//        Activite createdActivite = activiteService.addActivite(activite);
//        return new ResponseEntity<>(createdActivite, HttpStatus.CREATED);
//    }


    @PostMapping
    @RequestMapping(value = "/user/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Activite> createActivite(@RequestBody Activite activite, KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");

        if (hasUserRole) {
            return new ResponseEntity<>(activiteService.addActivite(activite), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }




//    @GetMapping
//    public ResponseEntity<List<Activite> > getAllActivites() {
//        List<Activite> activites = activiteService.findAllActivites();
//        return new ResponseEntity<>(activites, HttpStatus.OK);
//    }


    @GetMapping("/user/Activites")
    public ResponseEntity<List<Activite>> getAllActivites(KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");

        if (hasUserRole) {
            List<Activite> activites = activiteService.findAllActivites();
            return new ResponseEntity<>(activites, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }



//    @GetMapping("/{id}")
//    public ResponseEntity<Activite> getActiviteById(@PathVariable Long id) {
//        Optional<Activite> activite = activiteService.findActiviteById(id);
//        if (activite.isPresent()) {
//            return new ResponseEntity<>(activite.get(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @GetMapping("/user/By/{id}")
    public ResponseEntity<Optional<Activite>> getActiviteById(@PathVariable Long id,KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");

        if (hasUserRole) {
            Optional<Activite> activite = activiteService.findActiviteById(id);
            return new ResponseEntity<>(activite, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }





//    @PutMapping("/{id}")
//    public ResponseEntity<Activite> updateActivite(@PathVariable Long id, @RequestBody Activite newActivite) {
//        Activite updatedActivite = activiteService.updateActivite(id, newActivite);
//        if (updatedActivite != null) {
//            return new ResponseEntity<>(updatedActivite, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }



    @PutMapping("user/update/{id}")
    @ResponseBody
    public ResponseEntity<Activite> updateActivite(@PathVariable("id") Long id, @RequestBody Activite newActivite, KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");

        if (hasUserRole) {
            Activite updatedActivite = activiteService.updateActivite(id, newActivite);
            return new ResponseEntity<>(updatedActivite, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }


//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteActivite(@PathVariable Long id) {
//        String result = activiteService.deleteActivite(id);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }



    @DeleteMapping(value = "/admin/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteActivite(@PathVariable(value = "id") Long id, KeycloakAuthenticationToken auth){
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("admin");
        if (hasUserRole) {
            return new ResponseEntity<>(activiteService.deleteActivite(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }



//    @GetMapping("/average-activity-duration")
//    public ResponseEntity<Double> getAverageActivityDuration() {
//        double averageDuration = activiteService.calculateAverageActivityDuration();
//        return new ResponseEntity<>(averageDuration, HttpStatus.OK);
//    }


    @GetMapping("/user/average-activity-duration")
    public ResponseEntity<Double> getAverageActivitiesPerProgram(KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");

        if (hasUserRole) {
            double averageDuration = activiteService.calculateAverageActivityDuration();
            return new ResponseEntity<>(averageDuration, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }


//
//    @GetMapping("/average-activities-per-day")
//    public ResponseEntity<Double> getAverageActivitiesPerDay() {
//        double averageActivitiesPerDay = activiteService.calculateAverageActivitiesPerDay();
//        return new ResponseEntity<>(averageActivitiesPerDay, HttpStatus.OK);
//    }

    @GetMapping("/user/average-activities-per-day")
    public ResponseEntity<Double> getAverageActivitiesPerDay(KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");

        if (hasUserRole) {
            double averageActivitiesPerDay = activiteService.calculateAverageActivitiesPerDay();
            return new ResponseEntity<>(averageActivitiesPerDay, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }



//    @GetMapping("/maximum-activity-duration")
//    public ResponseEntity<Long> getMaximumActivityDuration() {
//        long maxDuration = activiteService.calculateMaximumActivityDuration();
//        return new ResponseEntity<>(maxDuration, HttpStatus.OK);
//    }


    @GetMapping("/user/maximum-activity-duration")
    public ResponseEntity<Long> getMaximumActivityDuration(KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");

        if (hasUserRole) {
            long maxDuration = activiteService.calculateMaximumActivityDuration();
            return new ResponseEntity<>(maxDuration, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }




//    @GetMapping("/number-of-activities")
//    public ResponseEntity<Long> getNumberOfActivities() {
//        long numberOfActivities = activiteService.getNumberOfActivities();
//        return new ResponseEntity<>(numberOfActivities, HttpStatus.OK);
//    }

    @GetMapping("/user/number-of-activities")
    public ResponseEntity<Long> getNumberOfActivities(KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");

        if (hasUserRole) {
            long numberOfActivities = activiteService.getNumberOfActivities();
            return new ResponseEntity<>(numberOfActivities, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }



}
