/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFiles {

	List<String> filesListInDir = new ArrayList<String>();

	public static void main(String[] args) {
		File file = new File("/Users/pankaj/sitemap.xml");
		String zipFileName = "/Users/pankaj/sitemap.zip";

		File dir = new File("/Users/pankaj/tmp");
		String zipDirName = "/Users/pankaj/tmp.zip";

		zipSingleFile(file, zipFileName);

		ZipFiles zipFiles = new ZipFiles();
		zipFiles.zipDirectory(dir, zipDirName);
	}

	public void zipDirectory(File dir, String zipDirName) {
		try {
			populateFilesList(dir);
			FileOutputStream fos = new FileOutputStream(zipDirName);
			ZipOutputStream zos = new ZipOutputStream(fos);
			for (String filePath : filesListInDir) {
				System.out.println("Zipping " + filePath);
				ZipEntry ze = new ZipEntry(filePath.substring(dir.getAbsolutePath().length() + 1, filePath.length()));
				zos.putNextEntry(ze);
				FileInputStream fis = new FileInputStream(filePath);
				byte[] buffer = new byte[1024];
				int len;
				while ((len = fis.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}
				zos.closeEntry();
				fis.close();
			}
			zos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void populateFilesList(File dir) throws IOException {
		File[] files = dir.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				filesListInDir.add(file.getAbsolutePath());
			} else {
				populateFilesList(file);
			}
		}
	}

	public static void zipSingleFile(File file, String zipFileName) {
		try {
			FileOutputStream fos = new FileOutputStream(zipFileName);
			ZipOutputStream zos = new ZipOutputStream(fos);
			ZipEntry ze = new ZipEntry(file.getName());
			zos.putNextEntry(ze);
			FileInputStream fis = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int len;
			while ((len = fis.read(buffer)) > 0) {
				zos.write(buffer, 0, len);
			}

			zos.closeEntry();
			zos.close();
			fis.close();
			fos.close();
			System.out.println(file.getCanonicalPath() + " is zipped to " + zipFileName);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void deleteFiles(String urlFolderFile) {
		File file = new File(urlFolderFile);
		String[] myFiles;
		if (file.isDirectory()) {
			myFiles = file.list();
			for (int i = 0; i < myFiles.length; i++) {
				File myFile = new File(file, myFiles[i]);
				myFile.delete();
			}
		}

	}

	public boolean deleteFile(String urlFolderFile) {
		File file = new File(urlFolderFile);
		String[] myFiles;
		if (file.exists()) {
			if (file.delete())
				return true;
			else
				return false;
		}
		return true;

	}
}
