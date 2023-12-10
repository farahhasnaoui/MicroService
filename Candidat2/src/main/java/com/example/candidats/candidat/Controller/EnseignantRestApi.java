package com.example.candidats.candidat.Controller;


import com.example.candidats.candidat.Service.ContratService;
import com.example.candidats.candidat.Service.EmailService;
import com.example.candidats.candidat.Service.EnseignantService;
import com.example.candidats.candidat.entity.Contrat;
import com.example.candidats.candidat.entity.Enseignant;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;



import java.io.ByteArrayOutputStream;

import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/api/candidats")
@RestController
@Slf4j
public class EnseignantRestApi {
    @Autowired
    private ContratService contratService;
    @Autowired
    private EnseignantService enseignantService;
    @Autowired
    private EmailService emailService;
    //@PostMapping
    //@ResponseStatus(HttpStatus.CREATED)
    //public ResponseEntity<Enseignant> createEnseignant(@RequestBody Enseignant enseignant) {
        //return new ResponseEntity<>(enseignantService.addEnseignant(enseignant), HttpStatus.OK);
   // }
    @GetMapping("/enseignants")
    public ResponseEntity<List<Enseignant>> getAllEnseignants() {
        List<Enseignant> enseignants = enseignantService.getAllEnseignantsWithContrats();
        return new ResponseEntity<>(enseignants, HttpStatus.OK);
    }

    @PostMapping
    @RequestMapping(value = "/user/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Enseignant> createEnseignantWithExistingContrat(
            @RequestBody Enseignant enseignant, @RequestParam Long contratId, KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context  = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");
        if (hasUserRole){
            return  new ResponseEntity<>(enseignantService.createEnseignantWithExistingContrat(enseignant, contratId),HttpStatus.OK);
        }
        else {
            return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
//    @PostMapping("/enseignants")
//
//    public ResponseEntity<Enseignant> createEnseignantWithExistingContrat(
//            @RequestBody Enseignant enseignant, @RequestParam Long contratId) {
//        Enseignant newEnseignant = enseignantService.createEnseignantWithExistingContrat(enseignant, contratId);
//        return ResponseEntity.ok(newEnseignant);
//    }


//    @PutMapping("/enseignants/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<Enseignant> updateEnseignant(@PathVariable(value = "id") Long id, @RequestBody Enseignant enseignant) {
//        return new ResponseEntity<>(enseignantService.updateEnseignant(id, enseignant), HttpStatus.OK);
//    }


    @PutMapping("user/update/{id}")
    @ResponseBody
    public ResponseEntity<Enseignant> updateEnseignant(@PathVariable(value = "id") Long id, @RequestBody Enseignant enseignant, KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");

        if (hasUserRole) {
            Enseignant updatedClase = enseignantService.updateEnseignant(id, enseignant);
            return new ResponseEntity<>(updatedClase, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }






//
//    @DeleteMapping("/enseignants/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<String> deleteEnseignant(@PathVariable Long id) throws EnseignantNotFoundException {
//        return new ResponseEntity<>(enseignantService.deleteEnseignant(id), HttpStatus.OK);
//    }

    @DeleteMapping(value = "/admin/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteCandidat(@PathVariable(value = "id") Long id, KeycloakAuthenticationToken auth)throws EnseignantNotFoundException{
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("admin");
        if (hasUserRole) {
            return new ResponseEntity<>(enseignantService.deleteEnseignant(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }




//
//    @PostMapping("/enseignants/{id}/send-contract")
//    public ResponseEntity<String> sendContractByEmail(@PathVariable Long id) {
//        try {
//            Enseignant enseignant = enseignantService.getEnseignantById(id);
//            Contrat contrat = enseignant.getContrat();
//
//            // Generate the PDF contract
//            byte[] pdfBytes = generateContractPDF(contrat);
//
//            // Send the contract via email
//            emailService.sendContract(enseignant.getEmail(), pdfBytes);
//
//            return new ResponseEntity<>("Contract sent via email", HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Error sending contract: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        } catch (EnseignantNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }


    @PostMapping
    @RequestMapping(value = "/user/{id}/send-contract")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> sendContractByEmail(
            @PathVariable Long id, KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context  = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");
        if (hasUserRole){

            try {
                Enseignant enseignant = enseignantService.getEnseignantById(id);
                Contrat contrat = enseignant.getContrat();

                // Generate the PDF contract
                byte[] pdfBytes = generateContractPDF(contrat);

                // Send the contract via email
                emailService.sendContract(enseignant.getEmail(), pdfBytes);

                return new ResponseEntity<>("Contract sent via email", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Error sending contract: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            } catch (EnseignantNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }




    private byte[] generateContractPDF(Contrat contrat) {
        // Implement PDF generation logic using a library like iText
        // Example: You need to create a PDF document and add content to it
        // Replace this example with your actual PDF generation code

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, baos);

            document.open();
            document.add(new Paragraph("Contract Type: " + contrat.getType()));
            document.add(new Paragraph("Start Date: " + contrat.getDateDebut()));
            document.add(new Paragraph("End Date: " + contrat.getDateFin()));
            document.add(new Paragraph("Salaire: " + contrat.getSalaire()));
            // Add more contract details as needed

            // Close the document
            document.close();

            return baos.toByteArray();
        } catch (DocumentException e) {
            // Handle document generation exception
            e.printStackTrace();
            throw new RuntimeException("Error generating PDF: " + e.getMessage());
        }


    }

//
//    @PostMapping("/contrats")
//    public ResponseEntity<Contrat> createContrat(@RequestBody Contrat contrat) {
//        return ResponseEntity.ok(contratService.addContrat(contrat));
//    }

    @PostMapping
    @RequestMapping(value = "/user")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Contrat> createContrat(
            @RequestBody Contrat contrat, KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context  = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");
        if (hasUserRole){
            log.info("aaaaaa",hasUserRole);
            return  new ResponseEntity<>(contratService.addContrat(contrat),HttpStatus.OK);
        }
        else {
            return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }






//    @GetMapping("/contrats")
//    public ResponseEntity<List<Contrat>> getAllContrats() {
//        return ResponseEntity.ok(contratService.getAllContrats());
//    }


    @GetMapping("/user/contrats")
    public ResponseEntity<List<Contrat>> getAllContrats(KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");

        if (hasUserRole) {
            List<Contrat> classes = contratService.getAllContrats();
            return new ResponseEntity<>(classes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }




//
//    @GetMapping("/contrats/{id}")
//    public ResponseEntity<Contrat> getContrat(@PathVariable Long id) {
//        return contratService.getContratById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }



    @GetMapping("/user/contrats/{id}")
    public ResponseEntity<Contrat> getContrat(@PathVariable Long id,KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");

        if (hasUserRole) {
            return   contratService.getContratById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());

        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }



//
//
//    @PutMapping("/contrats/{id}")
//    public ResponseEntity<Contrat> updateContrat(@PathVariable Long id, @RequestBody Contrat contrat) {
//        return contratService.updateContrat(id, contrat)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//




    @PutMapping("user/contrats-update/{id}")
    @ResponseBody
    public ResponseEntity<Contrat> updateContrat(@PathVariable Long id, @RequestBody Contrat contrat, KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");

        if (hasUserRole) {
            return contratService.updateContrat(id, contrat)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }





//
//    @DeleteMapping("/contrats/{id}")
//    public ResponseEntity<String> deleteContrat(@PathVariable Long id) {
//        return ResponseEntity.ok(contratService.deleteContrat(id));
//    }

    @DeleteMapping(value = "/admin/delete-contrats/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteContrat(@PathVariable(value = "id") Long id, KeycloakAuthenticationToken auth){
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("admin");
        if (hasUserRole) {
            return new ResponseEntity<>(contratService.deleteContrat(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }


}
