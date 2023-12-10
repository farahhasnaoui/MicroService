package com.example.parenttuteur;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.lowagie.text.*;

import com.lowagie.text.pdf.PdfPCell;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;
public class ParentsTuteurPDF {
    private List<Parent> listParent;

    public ParentsTuteurPDF(List<Parent> listUsers) {
        this.listParent = listUsers;
    }

    private void writeTableHeader(Table table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase(" ID", font));

        table.addCell( "ID").setBackgroundColor(com.itextpdf.kernel.color.Color.GRAY).setFontColor(com.itextpdf.kernel.color.Color.RED);

        cell.setPhrase(new Phrase("Nom", font));
        table.addCell("Nom");

        cell.setPhrase(new Phrase("Prenom", font));
        table.addCell("Prenom");
        cell.setPhrase(new Phrase("Tuteurs", font));
        table.addCell("Tuteurs");


    }

    private void writeTableData(Table table) {
        String tuteurs="";
        for (Parent c : listParent) {
            table.addCell(String.valueOf(c.getId())).setBackgroundColor(com.itextpdf.kernel.color.Color.LIGHT_GRAY).setFontColor(com.itextpdf.kernel.color.Color.BLACK);
            table.addCell(String.valueOf(c.getFirstName())).setBackgroundColor(com.itextpdf.kernel.color.Color.LIGHT_GRAY).setFontColor(com.itextpdf.kernel.color.Color.BLACK);
            table.addCell(String.valueOf(c.getLastName())).setBackgroundColor(com.itextpdf.kernel.color.Color.LIGHT_GRAY).setFontColor(com.itextpdf.kernel.color.Color.BLACK);
            for (Tuteur t:c.getTuteurs()
                 ) {
                 tuteurs += t.getFirstName()+t.getLastName()+" ";
            }
            table.addCell(tuteurs).setBackgroundColor(com.itextpdf.kernel.color.Color.LIGHT_GRAY).setFontColor(com.itextpdf.kernel.color.Color.BLACK);

        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {


        PdfWriter writer = new PdfWriter(response.getOutputStream());
        PdfDocument pdfDocument;
        pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);




        // Creating an ImageData object
        String imFile = "C:\\Users\\LENOVO\\Documents\\logoE.png";
        ImageData data = ImageDataFactory.create(imFile);

        // Creating an Image object
        Image image = new Image(data);

        // Adding image to the document
        document.add(image);



        com.itextpdf.layout.element.Paragraph p1 = new com.itextpdf.layout.element.Paragraph("List of Parents");
        p1.setFontColor(com.itextpdf.kernel.color.Color.RED).setCharacterSpacing((float)2.5);
        p1.setItalic();
        p1.setTextAlignment(TextAlignment.CENTER);
        document.add(p1);
        com.itextpdf.layout.element.Table table = new com.itextpdf.layout.element.Table(4);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);


        document.close();

        System.out.println("Image added");
    }





}
