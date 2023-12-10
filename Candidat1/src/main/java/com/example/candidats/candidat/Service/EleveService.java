package com.example.candidats.candidat.Service;


import com.example.candidats.candidat.Repo.ClaseRepository;
import com.example.candidats.candidat.Repo.ElevesRepository;
import com.example.candidats.candidat.entity.Eleves;
import com.example.candidats.candidat.entity.clase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class EleveService {

    @Autowired
    private ElevesRepository eleveRepository;

    @Autowired
    private ClaseRepository claseRepository;




    public List<Eleves> getAllEleves() {
        return eleveRepository.findAll();
    }



    public Eleves addEleves(Eleves eleve) {
        return eleveRepository.save(eleve);
    }



    public Eleves updateEleves(int id, Eleves newEleves) {
        if (eleveRepository.findById(id).isPresent()) {
            Eleves existingCandidat = eleveRepository.findById(id).get();
            existingCandidat.setNom(newEleves.getNom());
            existingCandidat.setPrenom(newEleves.getPrenom());
            existingCandidat.setEmail(newEleves.getEmail());

            return eleveRepository.save(existingCandidat);
        } else
            return null;
    }

    public String deleteEleves(int id) {
        if (eleveRepository.findById(id).isPresent()) {
            eleveRepository.deleteById(id);
            return "eleve supprimé";
        } else
            return "eleve non supprimé";
    }

    public Eleves ajouter_Eleves(Eleves e, int id) {

        clase d = claseRepository.findById(id).orElse(null);
        e.setClas(d);
        return eleveRepository.save(e);
    }


    public void sendEmail(String recipientEmail, String subject, String body) throws javax.mail.MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        javax.mail.Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("amed14170@gmail.com", "stnbkdvzoflpnqyj");
            }
        });

        try {
            javax.mail.Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("amed14170@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
        } catch (javax.mail.MessagingException e) {
            throw new MessagingException("Failed to send email.", e);
        }
    }


    public List<Eleves> searchEleve(String keyword) {
        return eleveRepository.search(keyword);
    }


}
