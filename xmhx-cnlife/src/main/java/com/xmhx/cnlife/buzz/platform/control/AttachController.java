package com.xmhx.cnlife.buzz.platform.control;
//package com.xmhx.core.buzz.controller;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStreamWriter;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.xmhx.core.authority.LoginAuth;
//import com.xmhx.core.authority.NoLoginAuth;
//import com.xmhx.core.buzz.BaseController;
//import com.xmhx.core.buzz.model.AttachDTO;
//import com.xmhx.core.buzz.service.AttachService;
//import com.xmhx.core.utils.DateUtils;
//import com.xmhx.core.utils.FileUtils;
//import com.xmhx.example.model.syst.UserDTO;
//
//
///**
// * 附件
// * @author 吴进 by 20151006
// *
// */
//@Controller
//@RequestMapping(value="/")
//public class AttachController extends BaseController {
//
//	@Autowired
//	private AttachService attachService;
//	
//	/**
//	 * 上传附件
//	 * @param request
//	 * @param map
//	 * @return
//	 */
//	@LoginAuth
//	@RequestMapping(value="uploadattachment.do", method=RequestMethod.POST)
//	@ResponseBody
//	public Object uploadattachment(HttpServletRequest request, MultipartFile file, String attachmod, String attachfkid) {
//		try {
//			String originalFilename = file.getOriginalFilename();
//			String subfolder = "data/attach";
//			File srcFile = readfiles(request, file, subfolder);						// 源文件
//			String srcName = srcFile.getName();										// 源文件名
//			String directoryName = DateUtils.getFormatDateTimes();					// 上传时间戳做为目录
//			String targetDirectory = getUploadfiledir();							// 配置的上传目录
//			targetDirectory = targetDirectory + File.separatorChar + directoryName;	// 上传全目录
//			FileUtils.creatIfNotExsit(targetDirectory);								// 上传目录不存在则新建
//			File destFile = new File(targetDirectory, srcName);						// 上传目标目录
//			org.apache.commons.io.FileUtils.copyFile(srcFile, destFile);			// 上传
//			
//			UserDTO userDTO = getUser(request);
//			String umname = userDTO.getUmname();
//			AttachDTO attachDTO = new AttachDTO();
//			attachDTO.setAttachname(FileUtils.fileHead(srcName));
//			attachDTO.setAttachorigname(FileUtils.fileHead(originalFilename));
//			attachDTO.setAttachpath(directoryName);
//			attachDTO.setAttachtype(FileUtils.fileSuffix(originalFilename));
//			attachDTO.setAttachmod(attachmod);
//			attachDTO.setAttachfkid(attachfkid);
//			attachDTO.setData_state("Y");
//			attachDTO.setCreated_by(umname);
//			attachDTO.setUpdated_by(umname);
//			String id = attachService.saveAttachment(attachDTO);
//			return pushMsg("附件已上传", Boolean.TRUE, "id", id);
//		} catch (IOException e) {
//			return pushMsg("附件上传异常", Boolean.FALSE);
//		}
//	}
//	
//	@LoginAuth
//	@RequestMapping(value="queryattachByfkid.do", method=RequestMethod.POST)
//	@ResponseBody
//	public Object queryattachByfkid(AttachDTO attachDTO) {
//		List<AttachDTO> attachlist = attachService.queryAttachByfkid(attachDTO);
//		return attachlist;
//	}
//	
//	/**
//	 * 图片或文本附件查看
//	 * @param id
//	 * 
//	 * 	<option   value="image/bmp">BMP</option>   
//	 *	<option   value="image/gif">GIF</option>   
//	 * 	<option   value="image/jpeg">JPEG</option>   
//	 *	<option   value="image/tiff">TIFF</option>   
//	 *	<option   value="image/x-dcx">DCX</option>   
//	 *	<option   value="image/x-pcx">PCX</option>   
//	 *	<option   value="text/html">HTML</option>   
//	 *	<option   value="text/plain">TXT</option>   
//	 *	<option   value="text/xml">XML</option>   
//	 * 	<option   value="application/afp">AFP</option>   
//	 *	<option   value="application/pdf">PDF</option>   
//	 *	<option   value="application/rtf">RTF</option>   
//	 *	<option   value="application/msword">MSWORD</option>   
//	 *	<option   value="application/vnd.ms-excel">MSEXCEL</option>   
//	 *	<option   value="application/vnd.ms-powerpoint">MSPOWERPOINT</option>   
//	 *	<option   value="application/wordperfect5.1">WORDPERFECT</option>   
//	 *	<option   value="application/vnd.lotus-wordpro">WORDPRO</option>   
//	 *	<option   value="application/vnd.visio">VISIO</option>   
//	 *	<option   value="application/vnd.framemaker">FRAMEMAKER</option>   
//	 *	<option   value="application/vnd.lotus-1-2-3">LOTUS123</option>
//	 * 
//	 */
//	@LoginAuth
//	@RequestMapping(value="showattachmentById.do", method=RequestMethod.GET)
//	public void showattachmentById(HttpServletResponse response, int id) {
//		try {
//			AttachDTO attachDTO = attachService.queryAttachmentById(id);
//			String attachtype = attachDTO.getAttachtype();
//			String attachpath = attachDTO.getAttachpath();
//			String attachname = attachDTO.getAttachname() + attachtype;
//			String targetDirectory = getUploadfiledir();
//			targetDirectory = targetDirectory + File.separatorChar + attachpath + File.separatorChar + attachname;
//			if (attachtype.matches(".txt") || attachtype.matches(".TXT")) {
//				// 字符流处理文本，但好像没用，中文还是乱码，下次解决
//				response.setContentType("text/plain");
//				BufferedReader reader = new BufferedReader(new FileReader(targetDirectory));
//				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
//				FileUtils.copy(reader, writer);
//			} else {
//				// 字节流处理图片
//				InputStream in = FileUtils.inputStreamFile(targetDirectory);
//				if (in != null) {
//					if (attachtype.matches(".png") || attachtype.matches(".PNG")) {
//						response.setContentType("image/png");
//					} else if (attachtype.matches(".bmp") || attachtype.matches(".BMP")) {
//						response.setContentType("image/bmp");
//					} else if (attachtype.matches(".gif") || attachtype.matches(".GIF")) {
//						response.setContentType("image/gif");
//					} else if (attachtype.matches(".jpg") || attachtype.matches(".JPG")) {
//						response.setContentType("image/jpg");
//					} else if (attachtype.matches(".jpeg") || attachtype.matches(".JPEG")) {
//						response.setContentType("image/jpeg");
//					}
//					FileUtils.copy(in, response.getOutputStream());
//				}
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	/**
//	 * 下载
//	 * @param id
//	 */
//	@LoginAuth
//	@RequestMapping(value="postDownloadattach.do", method=RequestMethod.POST)
//	public void postDownloadattach(HttpServletResponse response, int id) {
//		attachdownload(response, id);
//	}
//	
//	/**
//	 * 下载，用于部署说明
//	 * @param id
//	 */
//	@NoLoginAuth
//	@RequestMapping(value="getDownloadattach.do", method=RequestMethod.GET)
//	public void getDownloadattach(HttpServletResponse response, int id) {
//		attachdownload(response, id);
//	}
//	
//	/**
//	 * 下载处理
//	 * @param response
//	 * @param id
//	 */
//	private void attachdownload(HttpServletResponse response, int id) {
//		try {
//			AttachDTO attachDTO = attachService.queryAttachmentById(id);
//			String attachtype = attachDTO.getAttachtype();
//			String attachname = attachDTO.getAttachname() + attachtype;
//			String attachorigname = attachDTO.getAttachorigname() + attachtype;
//			String attachpath = attachDTO.getAttachpath();
//			String targetDirectory = getUploadfiledir();
//			targetDirectory = targetDirectory + File.separatorChar + attachpath + File.separatorChar + attachname;
//			FileUtils.download(response, attachorigname, targetDirectory);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	/**
//	 * 删除附件
//	 * @param attachDTO
//	 * @return
//	 */
//	@LoginAuth
//	@RequestMapping(value="delattach.do", method=RequestMethod.POST)
//	@ResponseBody
//	public Object delattach(AttachDTO attachDTO) {
//		AttachDTO attach = attachService.queryAttachmentById(attachDTO.getId());
//		String attachpath = attach.getAttachpath();
//		String targetDirectory = getUploadfiledir();
//		targetDirectory = targetDirectory + File.separatorChar + attachpath;
//		boolean result = attachService.delAttachById(attachDTO.getId());
//		if (result) {
//			FileUtils.delAllFile(targetDirectory);
//			return pushMsg("附件删除成功", Boolean.TRUE);
//		} else {
//			return pushMsg("附件删除失败", Boolean.FALSE);
//		}
//	}
//}
