

package com.example.candidats.candidat.Controller;
import com.example.candidats.candidat.Service.ProgrammeService;
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
@RequestMapping(value = "/api/candidats")

public class ProgrammeRestApi {
    @Autowired
    private ProgrammeService programmeService;

//    @PostMapping
//    public ResponseEntity<Programme> createProgramme(@RequestBody Programme programme) {
//        Programme createdProgramme = programmeService.addProgramme(programme);
//        return new ResponseEntity<>(createdProgramme, HttpStatus.CREATED);
//    }

    @PostMapping
    @RequestMapping(value = "/user")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Programme> createCandidat(@RequestBody Programme programme, KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");

        if (hasUserRole) {
            return new ResponseEntity<>(programmeService.addProgramme(programme), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }


//    @GetMapping
//    public ResponseEntity<List<Programme>> getAllProgrammes() {
//        List<Programme> programmes = programmeService.findAllProgrammes();
//        return new ResponseEntity<>(programmes, HttpStatus.OK);
//    }

    @GetMapping("/user")
    public ResponseEntity<List<Programme>> getAllProgrammes(KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");

        if (hasUserRole) {
            List<Programme> programmes = programmeService.findAllProgrammes();
            return new ResponseEntity<>(programmes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Programme> getProgrammeById(@PathVariable Long id) {
        Optional<Programme> programme = programmeService.findProgrammeById(id);
        if (programme.isPresent()) {
            return new ResponseEntity<>(programme.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Programme> updateProgramme(@PathVariable Long id, @RequestBody Programme newProgramme) {
//        Programme updatedProgramme = programmeService.updateProgramme(id, newProgramme);
//        if (updatedProgramme != null) {
//            return new ResponseEntity<>(updatedProgramme, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }


    @PutMapping("user/{id}")
    @ResponseBody
    public ResponseEntity<Programme> updateClasse(@PathVariable("id") Long id, @RequestBody Programme newProgramme, KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");

        if (hasUserRole) {
            Programme updatedProgramme = programmeService.updateProgramme(id, newProgramme);
            return new ResponseEntity<>(updatedProgramme, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }



//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteProgramme(@PathVariable Long id) {
//        String result = programmeService.deleteProgramme(id);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }

    @DeleteMapping(value = "/admin/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteCandidat(@PathVariable(value = "id") Long id, KeycloakAuthenticationToken auth){
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("admin");
        if (hasUserRole) {
            return new ResponseEntity<>(programmeService.deleteProgramme(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
//    @GetMapping("/count")
//    public long getProgramCount() {
//        return programmeService.getNumberOfPrograms();
//    }


    @GetMapping("/user/count")
    public ResponseEntity<Long> getProgramCount(KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");

        if (hasUserRole) {
            Long programmes = programmeService.getNumberOfPrograms();
            return new ResponseEntity<>(programmes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }





//    @GetMapping("/average-activities")
//    public double getAverageActivitiesPerProgram() {
//        return programmeService.calculateAverageActivitiesPerProgram();
//    }

    @GetMapping("/user/average-activities")
    public ResponseEntity<Double> getAverageActivitiesPerProgram(KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");

        if (hasUserRole) {
            double programmes = programmeService.calculateAverageActivitiesPerProgram();
            return new ResponseEntity<>(programmes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }





//    @GetMapping("/longest-duration")
//    public ResponseEntity<List<Programme>> getProgramsWithLongestDuration() {
//        List<Programme> programsWithLongestDuration = programmeService.findProgramsWithLongestDuration();
//
//        if (programsWithLongestDuration.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } else {
//            return new ResponseEntity<>(programsWithLongestDuration, HttpStatus.OK);
//        }
//    }


    @GetMapping("/user/longest-duration")
    public ResponseEntity<List<Programme>> getProgramsWithLongestDuration(KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");

        if (hasUserRole) {
            List<Programme> programsWithLongestDuration = programmeService.findProgramsWithLongestDuration();
            return new ResponseEntity<>(programsWithLongestDuration, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

}
