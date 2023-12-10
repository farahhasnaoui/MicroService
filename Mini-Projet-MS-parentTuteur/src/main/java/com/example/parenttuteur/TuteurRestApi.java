package com.example.parenttuteur;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
public class TuteurRestApi {
    @Autowired
    private TuteurService tuteurService;

    @GetMapping("/tuteurs")

    public List<Tuteur> getTuteurs(KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("admin");
        if (hasUserRole){  List<Tuteur> list = tuteurService.getAllTuteurs();
            return list;
        }else {
            return null;
        }

    }

    @PostMapping("/addtuteur")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Tuteur> createTuteur(@RequestBody Tuteur tuteur,KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("admin");
        if (hasUserRole){ return new ResponseEntity<>(tuteurService.addTuteur(tuteur), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }

    @PutMapping("/updatetuteur/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Tuteur> updateTuteur(@PathVariable(value = "id") Long id,
                                               @RequestBody Tuteur tuteur,KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("admin");
        if (hasUserRole){ return new ResponseEntity<>(tuteurService.updateTuteur(id, tuteur), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }

    @DeleteMapping("/deletetuteur/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteTuteur(@PathVariable(value = "id") Long id,KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("admin");
        if (hasUserRole){ return new ResponseEntity<>(tuteurService.deleteTuteur(id), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }
}
