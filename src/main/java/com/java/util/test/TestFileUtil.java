package com.java.util.test;

import java.io.IOException;

import com.java.util.file.FileUtil;

public class TestFileUtil {

	
	public static void main(String[] args) throws IOException {
		deleteFile();
	}
	
	
	public static void creatFolder() {
		String folderName = "/C:/test/123/abc";
		boolean result = FileUtil.creatFolder(folderName);
		System.out.println("文件" + folderName +"创建是否成功:" + result);
	}
	
	
	public static void createFile() throws IOException {
		String folderName = "C:/test/1/2/3";
		String fileName = "abc.txt";
		boolean result = FileUtil.createFile(folderName,fileName);
		System.out.println("文件" + folderName + "/" + fileName +"创建是否成功:" + result);
	}
	
	public static void renameFile() {
		String folderName = "C:/test/1/2/3";
		String oldFileName = "abc.txt";
		String newFileName = "cba.txt";
		boolean result = FileUtil.renameFile(folderName, oldFileName, newFileName);
		System.out.println("文件" + folderName + "/" + oldFileName +"，修改是否成功:" + result);
	}
	
	public static void deleteFile() {
		String folderName = "C:/test/1/2/3";
		String newFileName = "cba.txt";
		boolean result = FileUtil.deleteFile(folderName, newFileName);
		System.out.println("文件" + folderName + "/" + newFileName +"，删除是否成功:" + result);
	}
	
	
	
}
