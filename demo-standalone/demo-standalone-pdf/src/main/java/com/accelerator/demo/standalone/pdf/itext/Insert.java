package com.accelerator.demo.standalone.pdf.itext;

import com.accelerator.demo.standalone.pdf.Constants;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.File;

public class Insert {

    public static void main(String[] args) throws Exception {
        File pdfSource = new ClassPathResource(Constants.PDF_SOURCE_FILE).getFile();
        File pdfTarget = new File(pdfSource.getParent(), "demo-target.pdf");

        PdfReader reader = new PdfReader(FileUtils.readFileToByteArray(pdfSource));
        PdfStamper stamper = new PdfStamper(reader, FileUtils.openOutputStream(pdfTarget));

        PdfContentByte canvas = stamper.getOverContent(1);
        float height = 595;
        System.out.println(canvas.getHorizontalScaling());
        float x, y;
        x = 216;
        y = height - 49.09F;
        canvas.saveState();
        canvas.setColorFill(BaseColor.WHITE);
        canvas.rectangle(x, y - 5, 43, 15);

        canvas.fill();
        canvas.restoreState();
        //开始写入文本
        canvas.beginText();
        //BaseFont bf = BaseFont.createFont(URLDecoder.decode(CutAndPaste.class.getResource("/AdobeSongStd-Light.otf").getFile()), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
        Font font = new Font(bf, 10, Font.BOLD);
        //设置字体和大小
        canvas.setFontAndSize(font.getBaseFont(), 10);
        //设置字体的输出位置
        canvas.setTextMatrix(x, y);
        //要输出的text
        canvas.showText("多退少补");

        //设置字体的输出位置
        canvas.setFontAndSize(font.getBaseFont(), 20);
        canvas.setTextMatrix(x, y - 90);
        //要输出的text
        canvas.showText("多退少补");

        canvas.endText();
        stamper.close();
        reader.close();
        System.out.println("complete");
    }

}