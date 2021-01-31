package br.edu.ifsp.domain.usecases.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public class CreatePDF {
    public static void main(String[] argv) {
        Document document = new Document();
        try {

            PdfWriter.getInstance(document, new FileOutputStream("relatorio.pdf"));
            document.open();

            // adicionando um parágrafo no documento
            document.add(new Paragraph("Gerando PDF - Java"));
            document.add(new Paragraph("teste324"));
            document.add(new Paragraph("teste34324"));
        }
        catch(DocumentException | IOException de) {
            System.err.println(de.getMessage());
        }
        document.close();
    }
}
