package com.java.util.test;

import java.io.File;
import java.io.IOException;

import com.java.util.file.FileIoUtil;

public class TestFileIoUtil {

	public static void main(String[] args) throws IOException {
		
		//writeText();
		
		readeText();
		
		
	}
	
	
	public static void writeText() throws IOException {
		String folderName = "/abc/test/1/2/3";
		String fileName = "abc.txt";
		FileIoUtil.writeText(folderName, fileName, "123", true);
	}
	
	
	public static void readeText() throws IOException {
		String folderName = "C:/test/1/2/3";
		String fileName = "abc.txt";
		String content = FileIoUtil.readText(folderName + File.separator + fileName);
		System.out.println("文件内容：" + content);
	}
	
}
