package com.xmhx.cnlife.buzz.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 递归文件夹
 * @author 吴进  by 20151109
 */
public class ScanDirUtils {
	/**
	 * 递归查找路径strPath下所有的文件
	 * @param filelist
	 * @param strPath
	 * @return
	 */
	public static List<File> refreshFileList(List<File> filelist, String strPath) {
		if (TextUtils.notEmpty(strPath)) {
			// 文件夹dir
			File dir = new File(strPath);
			if (dir.exists()) {
				// 文件夹下的所有文件或文件夹
				File[] files = dir.listFiles();
				if (files != null && files.length > 0) {
					for (File f : files) {
						if (f.isDirectory()) {
							// 递归文件夹
							refreshFileList(filelist, f.getAbsolutePath());
						} else {
							// 文件
							filelist.add(f);
						}
					}
					return filelist;
				}
			}
		}
		return null;
	}
	
	/**
	 * 递归指定文件所有的文件数据
	 * @param filelist		保存的文件对象
	 * @param strPath		待递归的文件目录
	 * @param filename		文件名
	 * @return
	 */
	public static List<File> refreshFileList(List<File> filelist, String strPath, String filename) {
		if (TextUtils.notEmpty(strPath)) {
			// 文件夹dir
			File dir = new File(strPath);
			if (dir.exists()) {
				// 文件夹下的所有文件或文件夹
				File[] files = dir.listFiles();
				if (files != null && files.length > 0) {
					for (File f : files) {
						if (f.isDirectory()) {
							// 递归文件夹
							refreshFileList(filelist, f.getAbsolutePath(), filename);
						} else {
							// 文件
							if (filename.equals(f.getName()))
								filelist.add(f);
						}
					}
					return filelist;
				}
			}
		}
		return null;
	}
	
	public static void main(String[] args) throws IOException {
		String autodetectpath = "D:\\logs";
		autodetectpath = TextUtils.removelastSymbol(autodetectpath);
		List<File> fileinfolist = new ArrayList<File>();
		fileinfolist = refreshFileList(fileinfolist, autodetectpath, "\\aaa.txt");
		for (File f : fileinfolist) {
			String absolutePath = f.getAbsolutePath();
			String canonicalpath = absolutePath.replace(autodetectpath + File.separator, "");
			long filelength = f.length();
			System.out.println(autodetectpath + "--->" + absolutePath + "--->" + canonicalpath + "--->" + FileUtils.getFileSize(filelength));
		}
	}
}
