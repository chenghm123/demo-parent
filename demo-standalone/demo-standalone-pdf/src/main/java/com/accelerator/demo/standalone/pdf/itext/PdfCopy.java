package com.accelerator.demo.standalone.pdf.itext;

import com.accelerator.demo.standalone.pdf.Constants;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSmartCopy;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileOutputStream;

public class PdfCopy {

    public static void main(String[] args) throws Exception {
        ClassPathResource pdfSource = new ClassPathResource(Constants.PDF_SOURCE_FILE);
        Document document = new Document();
        PdfSmartCopy copy = new PdfSmartCopy(document, new FileOutputStream(new File(pdfSource.getFile().getParent(), "pdf-target.pdf")));
        document.open();

        PdfReader reader = new PdfReader(pdfSource.getFile().getCanonicalPath());
        int n = reader.getNumberOfPages();
        for (int j = 1; j <= n; j++) {
            PdfImportedPage page = copy.getImportedPage(reader, j);
            copy.addPage(page);
        }

        reader = new PdfReader(pdfSource.getFile().getCanonicalPath());
        n = reader.getNumberOfPages();
        for (int j = 1; j <= n; j++) {
            PdfImportedPage page = copy.getImportedPage(reader, j);
            copy.addPage(page);
        }

        document.close();
    }

}
