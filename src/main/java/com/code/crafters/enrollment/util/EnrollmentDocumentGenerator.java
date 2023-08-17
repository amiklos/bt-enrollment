package com.code.crafters.enrollment.util;

import com.code.crafters.enrollment.model.IdentityCard;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

@Component
public class EnrollmentDocumentGenerator {
    public static final Font FONT_TITLE = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
    public static final Font FONT_HEADER = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
    public static final Font FONT_TEXT = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
    public static final Font FONT_VALUE = new Font(Font.FontFamily.HELVETICA, 14, Font.NORMAL);

    public InputStream generateEnrollmentDocument(IdentityCard identityCard) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();
            Paragraph title = new Paragraph("Bank Account Registration Form", FONT_TITLE);
            title.setSpacingAfter(10);
            document.add(title);
            Paragraph header1 = new Paragraph("Account Information", FONT_HEADER);
            header1.setSpacingBefore(20);
            header1.setSpacingAfter(10);
            document.add(header1);
            document.add(new Paragraph("Account type", FONT_TEXT));
            document.add(new Paragraph("Personal", FONT_VALUE));
            document.add(new Paragraph("Currency type", FONT_TEXT));
            document.add(new Paragraph("RON", FONT_VALUE));
            Paragraph header2 = new Paragraph("Personal Information", FONT_HEADER);
            header2.setSpacingBefore(20);
            header2.setSpacingAfter(10);
            document.add(header2);
            document.add(new Paragraph("Personal ID", FONT_TEXT));
            document.add(new Paragraph(identityCard.getPersonalId(), FONT_VALUE));
            document.add(new Paragraph("Lastname", FONT_TEXT));
            document.add(new Paragraph(identityCard.getLastName(), FONT_VALUE));
            document.add(new Paragraph("FirstName", FONT_TEXT));
            document.add(new Paragraph(identityCard.getFirstName(), FONT_VALUE));
            document.add(new Paragraph("Nationality", FONT_TEXT));
            document.add(new Paragraph(identityCard.getNationality(), FONT_VALUE));
            document.add(new Paragraph("Place of Birth", FONT_TEXT));
            document.add(new Paragraph(identityCard.getPlaceOfBirth(), FONT_VALUE));
            document.add(new Paragraph("Address", FONT_TEXT));
            document.add(new Paragraph(identityCard.getAddress(), FONT_VALUE));
            document.add(new Paragraph("Place of Birth", FONT_TEXT));
            document.add(new Paragraph(identityCard.getPlaceOfBirth(), FONT_VALUE));
            Paragraph header3 = new Paragraph("Signature", FONT_HEADER);
            header3.setSpacingBefore(20);
            header3.setSpacingAfter(10);
            document.add(header3);
            Paragraph clientSignature = new Paragraph("Client signature", FONT_TEXT);
            clientSignature.setSpacingAfter(20);
            document.add(clientSignature);
            Paragraph bankSignature = new Paragraph("Bank signature", FONT_TEXT);
            bankSignature.setSpacingAfter(20);
            document.add(bankSignature);
            document.close();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    public InputStream generateDenialDocument(String denialCause) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();
            Paragraph title = new Paragraph("Bank Account Registration Form", FONT_TITLE);
            title.setSpacingAfter(20);
            document.add(title);
            document.add(new Paragraph("Your registration was denied. Reason : " + denialCause));
            Paragraph header1 = new Paragraph("Signature", FONT_HEADER);
            header1.setSpacingBefore(20);
            header1.setSpacingAfter(10);
            document.add(header1);
            Paragraph clientSignature = new Paragraph("Client signature", FONT_TEXT);
            clientSignature.setSpacingAfter(20);
            document.add(clientSignature);
            Paragraph bankSignature = new Paragraph("Bank signature", FONT_TEXT);
            bankSignature.setSpacingAfter(20);
            document.add(bankSignature);
            document.close();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

}
