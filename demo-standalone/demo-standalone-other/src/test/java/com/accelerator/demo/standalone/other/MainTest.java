package com.accelerator.demo.standalone.other;


import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class MainTest {

    public static String splitPdf(int pageNum, String filePath, String fileName, String outPath) {
        File indexFile = new File(filePath);// 这是对应文件名
        PDDocument document = null;
        try {
            document = PDDocument.load(indexFile);
            Splitter splitter = new Splitter();
            splitter.setStartPage(pageNum);
            splitter.setEndPage(pageNum);
            java.util.List<PDDocument> pages = splitter.split(document);
            ListIterator<PDDocument> iterator = pages.listIterator();
            while (iterator.hasNext()) {
                File file = new File(outPath);
                if (!file.exists()) {
                    file.mkdirs();
                }
                PDDocument pd = iterator.next();
                File newFile = new File(outPath + fileName);
                if (newFile.exists()) {
                    newFile.delete();
                }
                pd.save(outPath + fileName);
                pd.close();
                if (newFile.exists()) {
                    return newFile.getPath();
                }
            }
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static File somePdfToOne(List<File> files, String targetPath) throws IOException {
        // pdf合并工具类
        PDFMergerUtility mergePdf = new PDFMergerUtility();
        for (File f : files) {
            if(f.exists() && f.isFile()){
                // 循环添加要合并的pdf
                mergePdf.addSource(f);
            }
        }
        // 设置合并生成pdf文件名称
        mergePdf.setDestinationFileName(targetPath);
        // 合并pdf
        mergePdf.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
        return new File(targetPath);
    }

    public static void main(String[] args) throws Exception {
//        splitPdf(7, "C:\\Users\\Accelerator\\Documents\\工作\\Deppon\\移动端\\德邦MOA_2020052埃森哲7_v0.91.pdf",
//                "德邦MOA_2020052埃森哲7_7.pdf", "C:\\Users\\Accelerator\\Documents\\工作\\Deppon\\移动端\\");

        List<File> files = new ArrayList<>();
        files.add(new File("C:\\Users\\Accelerator\\Documents\\工作\\Deppon\\移动端\\德邦MOA_2020052埃森哲7_7.pdf"));
        files.add(new File("C:\\Users\\Accelerator\\Documents\\工作\\Deppon\\移动端\\德邦MOA_2020052埃森哲7_v0.91.pdf"));
        somePdfToOne(files, "C:\\Users\\Accelerator\\Documents\\工作\\Deppon\\移动端\\a.pdf");

    }


}
