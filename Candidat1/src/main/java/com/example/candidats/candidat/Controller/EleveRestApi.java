package com.example.candidats.candidat.Controller;



import com.example.candidats.candidat.Service.EleveService;
import com.example.candidats.candidat.entity.Eleves;
import com.example.candidats.candidat.entity.clase;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/candidats")

public class EleveRestApi {

    @Autowired
    private EleveService eleveService;


//    @GetMapping("/Eleves")
//    public List<Eleves> getAllEleves() {
//        return eleveService.getAllEleves();
//    }
//


    @GetMapping("/user/eleves")
    public ResponseEntity<List<Eleves>> getAllEleves(KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");

        if (hasUserRole) {
            List<Eleves> classes = eleveService.getAllEleves();
            return new ResponseEntity<>(classes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }


//    @PostMapping("/add")
//    @ResponseBody /* serialisation de l’objet en json*/
//    public void addEleves(@RequestBody Eleves d) throws MessagingException {
//        eleveService.addEleves(d);
//        String recipientEmail = d.getEmail();
//        String nom = d.getNom();
//        String messageContent = "l'éleve a été enregistrée avec succès.";
//        eleveService.sendEmail(recipientEmail,nom ,messageContent);
//
//    }


    @PostMapping
    @RequestMapping(value = "/user/addee")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Eleves> addEleves(@RequestBody Eleves d, KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");

        if (hasUserRole) {
            return new ResponseEntity<>(eleveService.addEleves(d), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }



    @PutMapping("user/update/{id}")
    @ResponseBody
    public ResponseEntity<Eleves> updateClasse(@PathVariable("id") int id, @RequestBody Eleves eleves, KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");

        if (hasUserRole) {
            Eleves updatedClase = eleveService.updateEleves(id, eleves);
            return new ResponseEntity<>(updatedClase, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }




//    @PutMapping("user/update/{id}")
//    @ResponseBody
//    public ResponseEntity<Eleves> updateCandidat(@PathVariable("id") int id,
//                                                   @RequestBody Eleves eleves){
//        return new ResponseEntity<>(eleveService.updateEleves(id, eleves), HttpStatus.OK);
//    }

    @DeleteMapping(value = "/admin/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> supprimerEleve(@PathVariable(value = "id") int id, KeycloakAuthenticationToken auth){
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("admin");
        if (hasUserRole) {
            return new ResponseEntity<>(eleveService.deleteEleves(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }


//    @DeleteMapping("/delete/{id}")
//    @ResponseBody
//    public void supprimerEleve(@PathVariable ("id") int id){
//        eleveService.deleteEleves(id);
//    }





//    @PostMapping("/addEleves/{id}")
//    @ResponseBody
//    public void addeleves(@RequestBody Eleves etudiant,@PathVariable("id") int id) {
//        eleveService.ajouter_Eleves(etudiant,id);
//    }


    @PostMapping
    @RequestMapping(value = "/user/add/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Eleves> addeleves(@RequestBody Eleves etudiant,@PathVariable("id") int id, KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");

        if (hasUserRole) {
            return new ResponseEntity<>( eleveService.ajouter_Eleves(etudiant,id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }





    @GetMapping("/user/search")
    public ResponseEntity<List<Eleves>> searchEleve(@RequestParam("keyword") String keyword,KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");

        if (hasUserRole) {
            List<Eleves> eleves = eleveService.searchEleve(keyword);
            return new ResponseEntity<>(eleves, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }



//
//    @GetMapping("/search")
//    public List<Eleves> searchEleve(@RequestParam("keyword") String keyword) {
//        List<Eleves> eleves = eleveService.searchEleve(keyword);
//        return eleves;
//    }

}
