package com.accelerator.demo.standalone.common.web;

import eu.bitwalker.useragentutils.Browser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
public abstract class ExportUtils {

    public static final String XLS_CONTENT_TYPE = "application/vnd.ms-excel";

    public static final String XLSX_CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    public static final String PDF_CONTENT_TYPE = "application/pdf";

    // /** @see cn.afterturn.easypoi.excel.ExcelExportUtil#exportExcel */
    // public static void exportExcelByEasypoi(HttpServletRequest request, HttpServletResponse response, String exportName,
    //         ExportParams entity, Class<?> pojoClass, Collection<?> dataSet) throws IOException {
    //     IOException unknownIOEx = null;
    //     try (Workbook workbook = ExcelExportUtil.exportExcel(entity, pojoClass, dataSet);
    //          OutputStream out = response.getOutputStream()) {
    //         if (workbook instanceof HSSFWorkbook) {
    //             fillXlsExportHttpHeader(request, response, exportName + ".xls");
    //         } else {
    //             fillXlsxExportHttpHeader(request, response, exportName + ".xlsx");
    //         }
    //         workbook.write(out);
    //     } catch (IOException e) {
    //         unknownIOEx = e;
    //     } finally {
    //         try {
    //             response.flushBuffer();
    //         } catch (IOException e) {
    //             if (Objects.isNull(unknownIOEx)) {
    //                 unknownIOEx = e;
    //             } else {
    //                 unknownIOEx.addSuppressed(e);
    //             }
    //         }
    //         if (Objects.nonNull(unknownIOEx)) {
    //             throw unknownIOEx;
    //         }
    //     }
    // }

    public static void fillXlsExportHttpHeader(HttpServletRequest request, HttpServletResponse response, String fileName) {
        response.setContentType(XLS_CONTENT_TYPE);
        fillDownloadHttpHeader(request, response, fileName);
    }

    public static void fillXlsxExportHttpHeader(HttpServletRequest request, HttpServletResponse response, String fileName) {
        response.setContentType(XLSX_CONTENT_TYPE);
        fillDownloadHttpHeader(request, response, fileName);
    }

    public static void fillPDFExportHttpHeader(HttpServletRequest request, HttpServletResponse response, String fileName) {
        response.setContentType(PDF_CONTENT_TYPE);
        fillDownloadHttpHeader(request, response, fileName);
    }

    public static void fillDownloadHttpHeader(HttpServletRequest request, HttpServletResponse response, String fileName) {
        String encodeFileName;
        try {
            encodeFileName = URLEncoder.encode(fileName, UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            encodeFileName = fileName;
            log.warn("文件名称编码异常！", e);
        }

        String browserFileName = isIE(request) ?
                encodeFileName : new String(fileName.getBytes(UTF_8), ISO_8859_1);

        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment;filename=" + browserFileName);
        response.setHeader("filename", encodeFileName);
    }

    public static boolean isIE(HttpServletRequest request) {
        ServerHttpRequest serverRequest = new ServletServerHttpRequest(request);
        HttpHeaders headers = serverRequest.getHeaders();
        String userAgent = headers.getFirst(HttpHeaders.USER_AGENT);
        Browser browser = Browser.parseUserAgentString(userAgent);
        return browser.getGroup() == Browser.IE
                || browser.getGroup() == Browser.EDGE;
    }

}
