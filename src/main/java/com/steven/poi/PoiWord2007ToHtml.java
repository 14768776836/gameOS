package com.steven.poi;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.core.FileURIResolver;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.util.List;

/**
 * Created by lanhaozhi on 2017/7/10.
 */
public class PoiWord2007ToHtml {
    public static void main(String[] args) throws IOException, OpenXML4JException {
        String filepath = "/Users/lanhaozhi/steven/experiment/tmp/" ;
        String fileName = "test.docx";
        String htmlName = "test.html";
        final String file = filepath + fileName;
        File f = new File(file);
        if (!f.exists()) {
            System.out.println("Sorry File does not Exists!");
        } else {
            if (f.getName().endsWith(".docx") || f.getName().endsWith(".DOCX")) {

                // 1) 加载word文档生成 XWPFDocument对象
                InputStream in = new FileInputStream(f);
                XWPFDocument document = new XWPFDocument(in);
                XWPFComment[] coms = document.getComments() ;

                for ( XWPFComment c : coms ) {
                    System.out.println(c.getText());
                }

                // 2) 解析 XHTML配置 (这里设置IURIResolver来设置图片存放的目录)
                /*File imageFolderFile = new File(filepath);
                XHTMLOptions options = XHTMLOptions.create().URIResolver(new FileURIResolver(imageFolderFile));
                options.setExtractor(new FileImageExtractor(imageFolderFile));
                options.setFragment(true);
                options.setIgnoreStylesIfUnused(false) ;*/
                //options.setOmitHeaderFooterPages(true) ;
                //options.setFragment(true) ;*/

                // 3) 将 XWPFDocument转换成XHTML
                /*OutputStream out = new FileOutputStream(new File(filepath + htmlName));
                XHTMLConverter.getInstance().convert(document, out, options);*/

                //也可以使用字符数组流获取解析的内容
                /*ByteArrayOutputStream baos = new ByteArrayOutputStream();
                XHTMLConverter.getInstance().convert(document, baos, options);
                String content = baos.toString();
                System.out.println(content);
                baos.close();*/
            } else {
                System.out.println("Enter only MS Office 2007+ files");
            }
        }
    }
}
