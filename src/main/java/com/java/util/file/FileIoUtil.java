package com.java.util.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * 文件IO操作：写文本，读文本
 * 
 * @author 喻聪
 * @date 2016-06-10
 *
 */
public class FileIoUtil {

			
	/**
	 * 将文本内容写入文件
	 * 
	 * @param folderName	文件夹名称
	 * @param fileName 		文件名称
	 * @param cotent		文本内容
	 * @param append 		追加(true)
	 */
	public static void writeText(String folderName,String fileName,String text,boolean append ) throws IOException {
		try {
			if(FileUtil.creatFolder(folderName)) {
				FileWriter fw = new FileWriter(folderName + File.separator + fileName , append);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(text);
				System.out.println("写入文件成功!");
				bw.flush();
				bw.close();	
			} else {
				//do nothing
			}	
		} catch (IOException e) {
			System.out.println("folderName:" + folderName + ",fileName:" + fileName + ", text:" + text + ", append:" + append);
			throw e;
		}
	}
	
	/**
	 * 从文件中读取出文本
	 * 
	 * @param filePath		文件绝对路径
	 * @throws IOException 
	 * 
	 * */
	public static String readText(String filePath) throws IOException {
		String result = null;
		try {
			StringBuilder buffer = new StringBuilder();
			FileReader fileReader = new FileReader(filePath);
			BufferedReader bufferReader = new BufferedReader(fileReader);
			String readStr = null;
			while ((readStr=bufferReader.readLine()) != null) {
				buffer.append(readStr);
			}
			bufferReader.close();			
			result = buffer.toString();
			buffer.setLength(0);
		} catch (FileNotFoundException e) {
			System.out.println("文件不存在，filePath:" + filePath);
			throw e;
		} catch (IOException e) {
			System.out.println("文件IO异常，filePath:" + filePath);
			throw e;
		}
		return result;
	}
	
}
