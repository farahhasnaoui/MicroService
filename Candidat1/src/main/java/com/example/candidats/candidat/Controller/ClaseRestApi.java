package com.example.candidats.candidat.Controller;

import com.example.candidats.candidat.Repo.ClaseRepository;
import com.example.candidats.candidat.Service.ClaseService;
import com.example.candidats.candidat.entity.clase;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RequestMapping("/Classe")
@RestController
@RequestMapping(value = "/api/candidats")
public class ClaseRestApi {

    @Autowired
    private ClaseService claseService;

    @Autowired
    private ClaseRepository claserep;


//    @GetMapping("/classes")
//    public List<clase> getAllClasse() {
//        return claseService.getAllClasse();
//    }






    @GetMapping("/user")
    public ResponseEntity<List<clase>> getAllClasse(KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");

        if (hasUserRole) {
            List<clase> classes = claseService.getAllClasse();
            return new ResponseEntity<>(classes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }









//
//    @PostMapping("/addClasse")
//    @ResponseBody /* serialisation de l’objet en json*/
//    public void addClasse(@RequestBody clase d) {
//        claseService.addClasse(d);
//    }

    @PostMapping
    @RequestMapping(value = "/user")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<clase> createCandidat(@RequestBody clase d, KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");

        if (hasUserRole) {
            return new ResponseEntity<>(claseService.addClasse(d), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }



//    @PutMapping("updateClasse/{id}")
//    @ResponseBody
//    public ResponseEntity<clase> updateClasse(@PathVariable("id") int id,
//                                                 @RequestBody clase classes){
//        return new ResponseEntity<>(claseService.updateClasse(id, classes), HttpStatus.OK);
//    }
@PutMapping("user/{id}")
@ResponseBody
public ResponseEntity<clase> updateClasse(@PathVariable("id") int id, @RequestBody clase classes, KeycloakAuthenticationToken auth) {
    KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
    KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
    boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");

    if (hasUserRole) {
        clase updatedClase = claseService.updateClasse(id, classes);
        return new ResponseEntity<>(updatedClase, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}

//    @DeleteMapping("/deleteClasse/{id}")
//    @ResponseBody
//    public void supprimerClasse(@PathVariable ("id") int id){
//        claseService.deleteClasses(id);
//    }
    @DeleteMapping(value = "/admin/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteCandidat(@PathVariable(value = "id") int id, KeycloakAuthenticationToken auth){
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("admin");
        if (hasUserRole) {
            return new ResponseEntity<>(claseService.deleteClasses(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

//    @GetMapping("/sort")
//    @ResponseBody
//    public Iterable<clase> sortCalsse() {
//        return claseService.sortClase();
//    }
//
//    @GetMapping("/classes-and-students")
//    public List<Object[]> getClassesAndStudents() {
//        return claseService.getClassesAndStudents();
//    }


    @GetMapping("/user/sort")
    @ResponseBody
    public ResponseEntity<Iterable<clase>> sortClase(KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");

        if (hasUserRole) {
            Iterable<clase> sortedClasses = claseService.sortClase();
            return new ResponseEntity<>(sortedClasses, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/user/classes-and-students")
    public ResponseEntity<List<Object[]>> getClassesAndStudents(KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");

        if (hasUserRole) {
            List<Object[]> classesAndStudents = claseService.getClassesAndStudents();
            return new ResponseEntity<>(classesAndStudents, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }



}
