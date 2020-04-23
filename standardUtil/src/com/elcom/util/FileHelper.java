package com.elcom.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileHelper {

	public static void copyInputStreamToFile( InputStream in, File file ) throws IOException {
		OutputStream out = new FileOutputStream(file);
        byte[] buf = new byte[1024];
        int len;
        while((len=in.read(buf))>0){
            out.write(buf,0,len);
        }
        out.close();
        in.close();
	}
	
	public static void createFolder(String folderPath) throws Exception {

		Path path = Paths.get(folderPath);
		// if directory exists?
		if (!Files.exists(path)) {
			Files.createDirectories(path);
		}
	}

	public static ArrayList<String> read(String path) {

		ArrayList<String> result = new ArrayList<String>();
		BufferedReader br = null;
		FileReader fr = null;

		try {

			fr = new FileReader(path);
			br = new BufferedReader(fr);

			String sCurrentLine;

			br = new BufferedReader(new FileReader(path));

			while ((sCurrentLine = br.readLine()) != null) {
				result.add(sCurrentLine);
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			if (fr != null)
				try {
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}

		return result;

	}

	public static void delete(String path) {
		File file1 = new File(path);

		if (file1.exists()) {
			file1.delete();
		}
	}

	public static void write(String path, ArrayList<String> message)
			throws Exception {

		File file1 = new File(path);

		try {

			if (!file1.exists()) {
				file1.createNewFile();
			} else {
				file1.delete();
				file1.createNewFile();
			}

			FileWriter fw = new FileWriter(file1, true);

			BufferedWriter bw = new BufferedWriter(fw);

			for (int i = 0; i < message.size(); i++) {

				bw.write(message.get(i));
				bw.newLine();
			}

			bw.close();
			fw.close();

			FileReader fr = new FileReader(file1);
			BufferedReader br = new BufferedReader(fr);

			fw = new FileWriter(file1, true);

			bw = new BufferedWriter(fw);

			while (br.ready()) {

				String line = br.readLine();

				bw.write(line);
				bw.newLine();

			}
			br.close();
			fr.close();

		} catch (Exception ex) {
			throw ex;
		}
	}

	public static void main(String[] args) throws Exception {

		ArrayList<String> a = new ArrayList<String>();
		a.add("aaa");

		FileHelper.write("D:\\a.txt", a);

	}
}
