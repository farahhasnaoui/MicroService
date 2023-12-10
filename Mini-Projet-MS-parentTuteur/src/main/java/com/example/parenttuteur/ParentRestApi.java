package com.example.parenttuteur;


import com.lowagie.text.DocumentException;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class ParentRestApi {
    @Autowired
    private ParentService parentService;
    @GetMapping("/parents")

    public List<Parent> getContrats( KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("admin");
        if (hasUserRole){ return parentService.Parents();}else {
            return null;
        }

    }
    @PostMapping("addparent")
    @ResponseStatus(HttpStatus.CREATED)
        public ResponseEntity<Parent> createParent(@RequestBody Parent parent,KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("admin");
        if (hasUserRole){  return new ResponseEntity<>(parentService.addParent(parent), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }


    @PutMapping(value = "updateparent/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Parent> updateParent(@PathVariable(value = "id") Long id,
                                                   @RequestBody Parent parent,KeycloakAuthenticationToken auth){
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("admin");
        if (hasUserRole){  return new ResponseEntity<>(parentService.updateParent( id, parent), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }


    @DeleteMapping(value = "deleteparent/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteParent(@PathVariable(value = "id") Long id,KeycloakAuthenticationToken auth){
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("admin");
        if (hasUserRole){ return new ResponseEntity<>(parentService.deleteParent(id), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }
    @PostMapping(value = "affectertuteurparent/{idP}/{idT}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> affecterParentTuteur(@PathVariable(value = "idP") Long idP,@PathVariable(value = "idT") Long idT,KeycloakAuthenticationToken auth){
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("admin");
        if (hasUserRole){return new ResponseEntity<>(parentService.AffecterTuteurParent(idP,idT),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }
    @GetMapping("/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Contrats_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Parent> listParents = parentService.Parents();

        ParentsTuteurPDF exporter = new ParentsTuteurPDF(listParents);
        exporter.export(response);

    }

}
