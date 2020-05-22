package com.java.util.file;

import java.io.File;
import java.io.IOException;

/**
 * 
 * 文件的基本操作：创建，删除等
 * 
 * @author 喻聪
 * @date 2016-06-10
 *
 */
public class FileUtil {	

			
	/**
	 * 创建文件夹
	 * 
	 * @param folderName	文件路径名称
	 */
	public static boolean creatFolder (String folderName ) {
		File folder = new File( folderName );
		boolean result = false;
		if( ! folder.exists( ) ) {
			result =  folder.mkdirs( );//创建多级文件夹
			if(result) {
				System.out.println("创建文件夹成功,folderName:" + folderName);
			} else {
				System.err.println("创建文件夹失败,folderName:" + folderName);
			}
		} else { 
			result = true;
			System.err.println( "文件夹已存在,folderName:" + folderName );
		}
		return result;
	}
	
	/**
	 * 创建文件
	 * 
	 * @param folderName	文件路径
	 * @param fileName		文件名称
	 */
	public static boolean createFile(String folderName, String fileName ) throws IOException {
		boolean result = false;
		if(creatFolder( folderName )) {
			File file = new File( folderName + File.separator + fileName );
			if( ! file.exists( ) ) { //文件不存在
				try {
					result = file.createNewFile( );
					System.out.println("创建文件成功,filePath:" + file.getAbsolutePath() );
				} catch (IOException e) {
					System.out.println("创建文件失败 , filePath : " + file.getAbsolutePath());
				}
			}  else { //文件存在
				System.out.println("文件已经存在 , filePath：" + file.getAbsolutePath() );
			}
		} else {
			//do nothing
		}
		return result;
	}
	
	
	
	/**
	 * 文件重命名
	 * 
	 * @param folderName	文件路径
	 * @param oldFileName	文件旧名称
	 * @param newFileName	文件新名称
	 */
	public static boolean renameFile(String folderName,String oldFileName, String newFileName ) {
		boolean result = false;
		File file = new File( folderName + File.separator + oldFileName  );
		if( file.exists( ) ) { // 文件存在
			File newFile = new File(folderName + File.separator + newFileName);
			result = file.renameTo( newFile );
			System.out.println("文件重命名成功,filePath:" + file.getAbsolutePath() );
		} else { //文件不存在
			System.out.println("文件不存在，无法重命名,filePath:" + file.getAbsolutePath() );
		}
		return result;
	}
	
	/**
	 * 删除文件
	 * 
	 * @param folderName	文件路径
	 * @param fileName		文件名称
	 */
	public static boolean deleteFile(String folderName, String fileName ) {
		boolean result = false;
		File file = new File( folderName + File.separator + fileName );
		if( file.exists( ) ) { // 文件存在
			result = file.delete( );
			System.out.println("文件删除成功,filePath:" + file.getAbsolutePath());
		} else { //文件不存在
			System.out.println("文件不存在，无法删除,filePath:" + file.getAbsolutePath() );
		}
		return result;
	}				
	
}
