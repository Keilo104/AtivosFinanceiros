package br.edu.ifsp.domain.usecases.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class CreatePDF {
    public void create( List<String> relatorio, String nomeArquivo ) {
        Document document = new Document();
        try {
            PdfWriter.getInstance( document, new FileOutputStream( nomeArquivo + ".pdf" ) );
            document.open();
            for ( String linha : relatorio ) {
                document.add( new Paragraph( linha ) );
            }
        } catch ( DocumentException | IOException de ) {
            System.err.println( de.getMessage() );
        }
        document.close();
    }
}
