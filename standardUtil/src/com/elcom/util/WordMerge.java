/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.util.IOUtils;

public class WordMerge {

    private final OutputStream result;
    private final List<InputStream> inputs;
    private XWPFDocument first;

    public WordMerge(OutputStream result) {
        this.result = result;
        inputs = new ArrayList<>();
    }

    public void add(InputStream stream) throws Exception {
        inputs.add(stream);
        OPCPackage srcPackage = OPCPackage.open(stream);
        XWPFDocument src1Document = new XWPFDocument(srcPackage);
        if (inputs.size() == 1) {
            first = src1Document;
        } else {
            CTBody srcBody = src1Document.getDocument().getBody();
            first.getDocument().addNewBody().set(srcBody);
        }
    }

    public void doMerge() throws Exception {
        first.write(result);
    }

    public void close() throws Exception {
        result.flush();
        result.close();
        for (InputStream input : inputs) {
            input.close();
        }
    }


    public static void main(String[] args) throws Exception {

        // Open a document.  
//    Document doc = new Document("D:\\MCredit\\GiayBienNhan_1_1000116120053411.doc"); 
        // Save document. 
//    doc.save("D:\\MCredit\\output.docx");
        FileOutputStream faos = new FileOutputStream("D:\\MCredit\\mergedFile.docx");

        WordMerge wm = new WordMerge(faos);

        wm.add(new FileInputStream("D:\\MCredit\\abc.docx"));
        wm.add(new FileInputStream("D:\\MCredit\\bcd.docx"));
//        wm.add(new FileInputStream("D:\\MCredit\\gh.docx"));

        wm.doMerge();
        wm.close();

    }
}
