/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Vector;

public class WordHandling 
{

	public static String concatenate2(PrintWriter oos, Vector<String> vFiles) {
        String fileName = null;
        String lastLine = "";
        BufferedReader file = null;
        String line = null;
        String sNewPage = "\\page";
         
        try {
            for(int i=0; i<vFiles.size(); i++) {
                file = new BufferedReader(new FileReader(vFiles.get(i)));
                 
                file.readLine();
                 
                Vector<String> lines = new Vector<String>();
                    while ((line = file.readLine()) != null) {
                      if(line!=null && line.trim().length()>0)
                        lines.add(line);
                    }
                 
                    if(i>0){
                        oos.println(sNewPage);
                    }                      
                    int iSize = lines.size();         
                    for(int iIdx = 0; iIdx <iSize - 1; iIdx++) {                      
                      line = lines.elementAt(iIdx);
                      oos.println(line);
                    }
                    lastLine = lines.elementAt(lines.size()-1);
                 
            }
             
        } catch (FileNotFoundException fnf) {
            System.err.println("File: " + fileName + " not found.");
        } catch (Exception e) {
            System.err.println(e.toString());
        } finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException io) {
                }
            }
        }
         
        return lastLine;
    }
     
    public static void main(String[] args) {
        try {
            PrintWriter os = new PrintWriter(new File("D:\\MCredit\\merdoc.doc"));
             
            Vector<String> vFiles = new Vector<String>();
            vFiles.add("D:\\MCredit\\GiayBienNhan_1000317120000261.doc");
            vFiles.add("D:\\MCredit\\GiayBienNhan_1000318060001330.doc");
            vFiles.add("D:\\MCredit\\GiayBienNhan_1000318100003109.doc");
             
            // Writes the first line of the first file
            BufferedReader file = new BufferedReader(new FileReader(vFiles.get(0)));
            String firstLine = file.readLine();
 
            os.println(firstLine);
            file.close();
             
            String lastLine = WordHandling.concatenate2(os, vFiles);
             
            // Writes the last line of the last file
            os.println(lastLine);
             
            os.close();
             
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    } 
	

}