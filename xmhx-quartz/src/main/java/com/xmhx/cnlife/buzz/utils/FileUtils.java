package com.xmhx.cnlife.buzz.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.xmhx.cnlife.buzz.utils.encrypt.SecurityUtils;

public class FileUtils {
	private static Logger logger = Logger.getLogger(FileUtils.class);
	static final Pattern RANGE_PATTERN = Pattern.compile("bytes \\d+-\\d+/\\d+");

	/**
	 * 根据ID获取文件路径
	 * 
	 * @param id
	 * @return
	 */
	public static String path() {
		String key = "www.straw-soft.com";
		String hash = SecurityUtils.MD5(key + "\t" + System.currentTimeMillis()
				+ "\t" + String.valueOf(System.currentTimeMillis()).length()
				+ "\t" + System.currentTimeMillis() % 10);
		String uuid = UUID.randomUUID().toString();
		hash += uuid.substring(uuid.length()-8,uuid.length());
		hash = hash.substring(hash.length() - 24, hash.length());
		return hash;
	}

	/**
	 * 获取为文件生成唯一路径
	 * @param fileName
	 * @return
	 */
	public static String getFileName(String fileName) {
		String exc = fileName.substring(fileName.lastIndexOf("."), fileName.length());
		String filename = path();
		return filename + exc;
	}
	
	/**
	 * 获取为文件生成唯一路径
	 * @param fileName
	 * @return
	 */
	public static String getFileName(String fileName, String appendPath) {
		int index = fileName.lastIndexOf(".");
		String exc = fileName.substring(index,fileName.length());
		fileName = fileName.substring(0,index);
		return fileName + appendPath + exc;
	}

	/**
	 * 递归删除指定文件夹下的所有文件
	 * 
	 * @param filepath
	 * @return
	 */
	public static boolean delFileInfo(String filepath) {
		try {
			File file = new File(filepath);
			if (file.exists()) {
				if (file.isDirectory()) {
					File[] filelist = file.listFiles();
					for (File fileinfo : filelist) {
						delFileInfo(fileinfo.getAbsolutePath());
					}
				} else {
					file.delete();
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 递归删除指定文件夹下的所有文件
	 * @param filedesc	文件夹目录
	 * @param filename	文件名
	 * @return
	 */
	public static boolean delScanDirFile(String filedesc, String filename) {
		if (TextUtils.notEmpty(filedesc) && TextUtils.notEmpty(filename)) {
			File fdesc = new File(filedesc);
			if (fdesc.exists()) {
				List<File> filelist = new ArrayList<File>();
				
				String regex = "^.+\\\\.+$";
				String regex2 = "^.*\\\\.+$";
				if (filename.matches(regex)) {
					int idx = filename.lastIndexOf("\\");
					String fpath = filename.substring(0, idx);
					String fname = filename.substring(idx + 1);
					filedesc += fpath;
					filename = fname;
				}
				
				if (!filename.matches(regex) && filename.matches(regex2)) {
					filename = filename.replace("\\", "");
				}
				
				filelist = ScanDirUtils.refreshFileList(filelist, filedesc, filename);
				if (filelist != null && !filelist.isEmpty()) {
					for (File f : filelist) {
						f.delete();
					}
				}
			} else {
				return Boolean.FALSE;
			}
		}
		return Boolean.FALSE;
	}

	/**
	 * 删除指定文件夹下所有文件
	 * @param path
	 * @return
	 */
	// param path 文件夹完整绝对路径
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);	// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);	// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}
	
	/**
	 * 删除文件夹
	 * @param folderPath 文件夹完整绝对路径
	 */
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); 	// 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除指定文件
	 * @param filename	文件名
	 * @return
	 */
	public static boolean delFile(String filename) {
		try {
			File file = new File(filename);
			if (file.exists() && !file.isDirectory()) {
				file.delete();
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 指定路径文件删除
	 * @param request
	 */
	public static void delRequestFile(HttpServletRequest request) {
		String path = request.getSession().getServletContext().getRealPath("");
		String fatherpath = path.substring(0, path.lastIndexOf(File.separator));
		File filepath = new File(fatherpath);
		for (File file : filepath.listFiles()) {
			file.delete();
		}
	}

	/**
	 * 如果不存在该目录，则创建
	 * @param filepath
	 */
	public static void creatIfNotExsit(String filepath) {
		File file = new File(filepath);
		if (file.isAbsolute() && !file.exists()) {
			file.mkdirs();
		}
	}

	/**
	 * 判断该目录或者文件是否存在
	 * 
	 * @param filepath
	 * @return
	 */
	public static boolean isExsit(String filepath) {
		File file = new File(filepath);
		if (file.exists())
			return true;
		return false;
	}
	
	/**
	 * 判断文件是不是目录
	 * @param dir
	 * @return
	 */
	public static List<String> getDirList(String dir) {
		if (TextUtils.notEmpty(dir)) {
		    List<String> file = new ArrayList<String>();
		    File[] files = new File(dir).listFiles();
		    for (int i = 0, n = files.length; i < n; i++) {
		    	if (files[i].isDirectory()) {
		    		file.add(files[i].getAbsolutePath());
		    	}
		    }
		    return file;
		} else {
			return null;
		}
	}
	
	/**
	 * 文件不为空
	 * @param file MultipartFile
	 * @return
	 */
	public static boolean fileNotEmpty(MultipartFile file){
		if(file!=null && !file.isEmpty() && file.getSize()>0){
			return true;
		}
		return false;
	}

	/**
	 * 列出指定目录下的所有文件
	 * 
	 * @param fliepath
	 * @return 日期：Sep 5, 2012
	 */
	public String[] fileList(String fliepath) {
		String[] myList = null;
		try {
			File path = new File(fliepath);// 定义一个File对象
			// 定义一个字符串数组
			myList = path.list();
			for (int i = 0, n = myList.length; i < n; i++)// 输出文件列表
			{
				System.out.println(myList[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return myList;
	}

	/**
	 * 检查文件是否合法
	 * 
	 * @param fileName  文件名称
	 * @param extendesc 适配文件类型 jpg....
	 * 使用方式：FileUtil.checkFile(file.getOriginalFilename(), "jpg", "jpeg","png")
	 * @return
	 */
	public static boolean validateFile(String fileName, String... extendesc) {
		if (fileName.contains(".") && fileName.matches("^.+\\..+$")) {
			String exc = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
			for (String exten : extendesc) {
				if (exc.toLowerCase().equals(exten)) {
					return Boolean.TRUE;
				}
			}
		}
		return Boolean.FALSE;
	}
	
	/**
	 * 检测文件是否属于图片资源
	 * @param originalFilename
	 * @return
	 */
	public static boolean validatePicture(String originalFilename) {
		return FileUtils.validateFile(originalFilename, "png", "jpg", "jpeg", "gif");
	}
	
	/**
	 * 检测文件是否属于Excel
	 * @param originalFilename
	 * @return
	 */
	public static boolean validateExcel(String originalFilename) {
		return FileUtils.validateFile(originalFilename, "csv", "xls", "xlsx");
	}
	
	/**
	 * 文件头
	 */
	public static String fileHead(String originalFilename) {
		if (originalFilename.contains(".") && originalFilename.matches("^.+\\..+$")) {
			String filehead = originalFilename.substring(0, originalFilename.lastIndexOf("."));
			return filehead;
		} else {
			return originalFilename;
		}
	}
	
	/**
	 * 文件后缀
	 * @param originalFilename
	 * @return
	 */
	public static String fileSuffix(String originalFilename) {
		if (originalFilename.contains(".") && originalFilename.matches("^.+\\..+$")) {
			String suffix = originalFilename.substring(originalFilename.lastIndexOf("."),
					originalFilename.length());
			return suffix;
		} else {
			return originalFilename;
		}
	}
	
	/**
	 * 返回byte的数据大小对应的文本
	 * @param size 
	 * @return
	 */
	public static String getFileSize(long size) {
		DecimalFormat formater = new DecimalFormat("####.00");
		if (size<1024) {
			return size+"bytes";
		} else if(size<1024*1024) {
			float kbsize = size/1024f;  
			return formater.format(kbsize)+"KB";
		} else if(size<1024*1024*1024) {
			float mbsize = size/1024f/1024f;  
			return formater.format(mbsize)+"MB";
		} else if(size<1024*1024*1024*1024) {
			float gbsize = size/1024f/1024f/1024f;  
			return formater.format(gbsize)+"GB";
		} else {
			return "size: error";
		}
	}
	
	/**
	 * 文件流读
	 * @param localFile
	 * @return
	 */
	public static InputStream inputStreamFile(String localFile) {
		InputStream in = null;
		try {
			in = new FileInputStream(new File(localFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return in;
	}
	
	/**
	 * 字节流传递
	 * @param in
	 * @param out
	 */
	public static void copy(InputStream in, OutputStream out) {
		try {
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = in.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(in);
			close(out);
		}
	}
	
	/**
	 * 字符流传递
	 * @param reader
	 * @param writer
	 */
	public static void copy(BufferedReader reader, BufferedWriter writer) {
		try {
			String str = null;
			while ((str = reader.readLine()) != null) {
				writer.write(str);
				writer.newLine();
			}
			reader.close();
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * 关闭字节输入流
	 * @param in
	 */
	public static void close(InputStream in) {
		if (in != null) {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				in = null;
			}
		}
	}
	
	/**
	 * 关闭字节输出流
	 * @param out
	 */
	public static void close(OutputStream out) {
		if (out != null) {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				out = null;
			}
		}
	}
	
	/**
	 * 写文件内容
	 * @param str
	 * @param filepath
	 * @param encoding
	 * @return
	 */
	public static boolean saveToFile(String str, String filepath, String encoding) {
		InputStream bis = null;
		try {
			if (encoding == null || "".equals(encoding)) {
				bis = new ByteArrayInputStream(str.getBytes("GBK"));
			} else {
				bis = new ByteArrayInputStream(str.getBytes(encoding));
			}
			OutputStream os = new FileOutputStream(filepath);
			IOUtils.copy(bis, os);
			bis.close();
			os.close();
			System.out.println("保存文件:" + filepath);
			return true;
		} catch (IOException e) {
			System.out.println("保存文件失败:" + filepath);
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 手工写日志
	 * @param logPath
	 * @param timeString
	 * @return
	 */
	public static int createLogFile(String logPath, String timeString) {
	    String filePath = logPath + "\\" + timeString + "_log.txt";
	    File logpathname = new File(logPath);
	    if (!logpathname.isDirectory()) {
	    	try {
	    		logpathname.mkdirs();
	    	} catch (Exception e) {
	    		System.out.println("创建log目录失败：" + e.getMessage());
	    		return -1;
	    	}
	    	File logFile = new File(filePath);
	    	if (!logFile.exists()) {
		        try {
		        	logFile.createNewFile();
		        } catch (IOException e) {
		        	System.out.println("创建文件失败！");
		        	return -1;
		        }
	    	}
	    }
	    return 1;
	}
	
	/**
	 * 复制文件
	 * 
	 * @param sourceFile
	 * @param targetFile
	 * @throws IOException
	 */
	public static void copyFile(File sourceFile, File targetFile)
			throws IOException {
		BufferedInputStream inBuff = null;
		BufferedOutputStream outBuff = null;
		try {
			// 新建文件输入流并对它进行缓冲
			inBuff = new BufferedInputStream(new FileInputStream(sourceFile));
			// 新建文件输出流并对它进行缓冲
			outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));
			// 缓冲数组
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			// 刷新此缓冲的输出流
			outBuff.flush();
		} finally {
			// 关闭流
			if (inBuff != null)
				inBuff.close();
			if (outBuff != null)
				outBuff.close();
		}
	}

	/**
	 * 复制文件夹
	 * 
	 * @param sourceDir
	 * @param targetDir
	 * @throws IOException
	 */
	public static void copyDirectiory(String sourceDir, String targetDir)
			throws IOException {
		// 新建目标目录
		(new File(targetDir)).mkdirs();
		// 获取源文件夹当前下的文件或目录
		File[] file = (new File(sourceDir)).listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].isFile()) {
				// 源文件
				File sourceFile = file[i];
				// 目标文件
				File targetFile = new File(
						new File(targetDir).getAbsolutePath() + File.separator
								+ file[i].getName());
				copyFile(sourceFile, targetFile);
			}
			if (file[i].isDirectory()) {
				// 准备复制的源文件夹
				String dir1 = sourceDir + "/" + file[i].getName();
				// 准备复制的目标文件夹
				String dir2 = targetDir + "/" + file[i].getName();
				copyDirectiory(dir1, dir2);
			}
		}
	}
	
	/**
	 * 对文件进行下载操作
	 * @param response
	 * @param attachorigname
	 * @param realpath
	 * @return
	 * @throws Exception
	 */
	public static String download(HttpServletResponse response, String attachorigname, String realpath) throws Exception {
		return download(response, attachorigname, realpath, "GBK");
	}
	
	/**
	 * 对文件进行下载操作
	 * @param response
	 * @param realpath
	 * @throws Exception
	 */
	public static String download(HttpServletResponse response, String attachorigname, String realpath, String unicode)
			throws Exception {
		ServletOutputStream out = null;
		FileInputStream in = null;
		try {
			response.setCharacterEncoding(unicode);
			response.setContentType("text/html");
			out = response.getOutputStream();
			File file = new File(realpath);
			if (!file.exists()) {
				logger.info("路径:"+file.getAbsolutePath() + " 文件不存在!");
				return (file.getAbsolutePath() + " 文件不存在!");
			}
			// 读取文件流
			in = new FileInputStream(file);
			// 下载文件
			// 设置响应头和下载保存的文件名
			response.setContentType("application/x-msdownload");	// 弹出下载的框
			response.setContentLength((int) file.length());			// 下载统计文件大小的进度
			response.setHeader("Content-Disposition", "attachment; filename="+ new String(attachorigname.getBytes(unicode),"ISO-8859-1"));//fileName
			// 下载框的信息
			if (in != null) {
				int filelen = in.available();
				byte[] buf = new byte[filelen];
				int len = 0;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				out.flush();
			}
		} catch (Exception e) {
			logger.info("下载文档出错："+e);
			e.printStackTrace();
			return e.getMessage();
		} finally {
			if (in != null)
				in.close();
			if (out != null)
				out.close();
			if (response != null)
				response.flushBuffer();
		}
		return "";
		// 解决完成后使用一切正常,但是总抛出java.lang.IllegalStateException异常主要是流还存在
	}
	
	/**
	 * 文件重命名
	 * @param srcfilename
	 * @return
	 */
	public static int renameTo(String srcfilename) {
		long curtime = new Date().getTime();
		File file = new File(srcfilename);
		if (file.exists()) {
			if (file.renameTo(new File(srcfilename + "_" + curtime))) {
				return 1;
			} else {
				return -2;
			}
		} else {
			return -1;
		}
	}
	
	/**
	 * 远程服务访问
	 * @param map
	 * @param url
	 * @return
	 */
	public static String remoteServerVisit(Map<String, String> map, String url) {
		if (map != null && TextUtils.notEmpty(url)) {
			HttpClient httpclient = new DefaultHttpClient();
			try {
				List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
				Set<Map.Entry<String, String >> set = map.entrySet();
				for (Map.Entry<String, String > entry : set) {
					String key = entry.getKey();
					String value = entry.getValue();
					nvps.add(new BasicNameValuePair(key, value));
				}
				HttpPost httpPost = new HttpPost(url.trim());
				httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
				HttpResponse response = httpclient.execute(httpPost);
				HttpEntity entity = response.getEntity();
				String result = entity != null ? EntityUtils.toString(entity) : null;
				return result;
			} catch (UnsupportedEncodingException e) {
				logger.error("CipTransferController closecause UnsupportedEncodingException " + e);
				return "UnsupportedEncodingException";
			} catch (ClientProtocolException e) {
				logger.error("CipTransferController closecause ClientProtocolException " + e);
				return "ClientProtocolException";
			} catch (IOException e) {
				logger.error("CipTransferController closecause IOException " + e);
				return "IOException";
			} finally {
				httpclient.getConnectionManager().shutdown();
			}
		}
		return "Input Parameter empty";
	}
	
}
