package com.xmhx.cnlife.buzz.platform.control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xmhx.cnlife.buzz.common.SessionSettings;
import com.xmhx.cnlife.buzz.platform.model.manages.attach.AttachDTO;
import com.xmhx.cnlife.buzz.platform.model.manages.user.UserDTO;
import com.xmhx.cnlife.buzz.platform.service.manages.attach.AttachService;
import com.xmhx.cnlife.buzz.utils.DateUtils;
import com.xmhx.cnlife.buzz.utils.FileUtils;
import com.xmhx.cnlife.buzz.utils.TextUtils;
import com.xmhx.cnlife.core.authority.LoginAuth;
import com.xmhx.cnlife.core.authority.NoLoginAuth;
import com.xmhx.cnlife.core.buzz.BaseController;


/**
 * 附件
 * @author 吴进 by 20151006
 *
 */
@Controller("AttachController")
@RequestMapping(value="/")
public class AttachController extends BaseController {

	@Autowired
	private AttachService attachService;
	
	/**
	 * 上传附件(后台管理)
	 * @param request
	 * @param map
	 * @return
	 */
	@LoginAuth
	@RequestMapping(value="uploadattachment.hx", method=RequestMethod.POST)
	@ResponseBody
	public Object uploadattachment(HttpServletRequest request, MultipartFile file, String attachmod, String attachfkid) {
		try {
			logger.info("正在上传附件...");
			UserDTO userDTO = getUser(request);
			String umname = userDTO.getMember_name();
			String id = attachmentUpload(request, file, attachmod, attachfkid, umname);
			if(TextUtils.notEmpty(id)){
				logger.info("附件上传成功===>id="+id);
				return pushMsg(Boolean.TRUE,"附件已上传", "id", id);
			}else{
				logger.error("附件上传失败===>id为NULL");
				return pushMsg(Boolean.FALSE,"附件上传失败","id",null);
			}
		} catch (Exception e) {
			logger.error("附件上传异常===>"+e.getMessage());
			return pushMsg(Boolean.FALSE,"附件上传异常");
		}
	}
	/**
	 * 上传附件(前端网站平台)
	 * @param request
	 * @param file
	 * @param attachmod
	 * @param attachfkid
	 * @return
	 * 创建日期：2017-2-4  创建人：yyp
	 */
	@LoginAuth
	@RequestMapping(value="uploadattachment2.hx", method=RequestMethod.POST)
	@ResponseBody
	public Object uploadattachment2(HttpServletRequest request, HttpSession session, MultipartFile file, String attachmod, String attachfkid) {
		try {
			logger.info("正在上传附件...");
			UserDTO userDTO = (UserDTO) session.getAttribute(SessionSettings.VIEWUSER_SESSION);
			String umname = userDTO.getMember_name();
			String id = attachmentUpload(request, file, attachmod, attachfkid, umname);
			if(TextUtils.notEmpty(id)){
				logger.info("附件上传成功===>id="+id);
				return pushMsg(Boolean.TRUE,"附件已上传", "id", id);
			}else{
				logger.error("附件上传失败===>id为NULL");
				return pushMsg(Boolean.FALSE,"附件上传失败","id",null);
			}
		} catch (Exception e) {
			logger.error("附件上传异常===>"+e.getMessage());
			return pushMsg(Boolean.FALSE,"附件上传异常");
		}
	}
	
	public String attachmentUpload(HttpServletRequest request, MultipartFile file, String attachmod, String attachfkid, String umname){
		try {
			String originalFilename = file.getOriginalFilename();
			String subfolder = "data/attach";
			File srcFile = readfiles(request, file, subfolder);						// 源文件
			String srcName = srcFile.getName();										// 源文件名
			String directoryName = DateUtils.getFormatDateTimes();					// 上传时间戳做为目录
			String targetDirectory = getUploadfiledir();							// 配置的上传目录
			targetDirectory = targetDirectory + File.separatorChar + directoryName;	// 上传全目录
			FileUtils.creatIfNotExsit(targetDirectory);								// 上传目录不存在则新建
			File destFile = new File(targetDirectory, srcName);						// 上传目标目录
			org.apache.commons.io.FileUtils.copyFile(srcFile, destFile);			// 上传
			
			
			AttachDTO attachDTO = new AttachDTO();
			attachDTO.setAttachName(FileUtils.fileHead(srcName));
			attachDTO.setAttachOrigName(FileUtils.fileHead(originalFilename));
			attachDTO.setAttachPath(directoryName);
			attachDTO.setAttachType(FileUtils.fileSuffix(originalFilename));
			attachDTO.setAttachMod(attachmod);
			attachDTO.setAttachfkId(attachfkid);
			attachDTO.setData_state("Y");
			attachDTO.setCreated_by(umname);
			attachDTO.setUpdated_by(umname);
			String id = attachService.saveAttachment(attachDTO);
			return id;
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("附件上传异常" + e.getMessage());
		}
		return null;
	}
	
	@LoginAuth
	@RequestMapping(value="queryattachByfkid.hx", method=RequestMethod.POST)
	@ResponseBody
	public Object queryattachByfkid(String attachfkid) {
		List<AttachDTO> attachlist = attachService.queryAttachByfkid(attachfkid);
		return attachlist;
	}
	
	/**
	 * 图片或文本附件查看
	 * @param id
	 * 
	 * 	<option   value="image/bmp">BMP</option>   
	 *	<option   value="image/gif">GIF</option>   
	 * 	<option   value="image/jpeg">JPEG</option>   
	 *	<option   value="image/tiff">TIFF</option>   
	 *	<option   value="image/x-dcx">DCX</option>   
	 *	<option   value="image/x-pcx">PCX</option>   
	 *	<option   value="text/html">HTML</option>   
	 *	<option   value="text/plain">TXT</option>   
	 *	<option   value="text/xml">XML</option>   
	 * 	<option   value="application/afp">AFP</option>   
	 *	<option   value="application/pdf">PDF</option>   
	 *	<option   value="application/rtf">RTF</option>   
	 *	<option   value="application/msword">MSWORD</option>   
	 *	<option   value="application/vnd.ms-excel">MSEXCEL</option>   
	 *	<option   value="application/vnd.ms-powerpoint">MSPOWERPOINT</option>   
	 *	<option   value="application/wordperfect5.1">WORDPERFECT</option>   
	 *	<option   value="application/vnd.lotus-wordpro">WORDPRO</option>   
	 *	<option   value="application/vnd.visio">VISIO</option>   
	 *	<option   value="application/vnd.framemaker">FRAMEMAKER</option>   
	 *	<option   value="application/vnd.lotus-1-2-3">LOTUS123</option>
	 * 
	 */
	@LoginAuth
	@RequestMapping(value="showattachmentById.hx", method=RequestMethod.GET)
	public void showattachmentById(HttpServletResponse response, String id) {
		try {
			AttachDTO attachDTO = attachService.queryAttachmentById(id);
			String attachtype = attachDTO.getAttachType();
			String attachpath = attachDTO.getAttachPath();
			String attachname = attachDTO.getAttachName() + attachtype;
			String targetDirectory = getUploadfiledir();
			targetDirectory = targetDirectory + File.separatorChar + attachpath + File.separatorChar + attachname;
			if (attachtype.matches(".txt") || attachtype.matches(".TXT")) {
				// 字符流处理文本，但好像没用，中文还是乱码，下次解决
				response.setContentType("text/plain");
				BufferedReader reader = new BufferedReader(new FileReader(targetDirectory));
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
				FileUtils.copy(reader, writer);
			} else {
				// 字节流处理图片
				InputStream in = FileUtils.inputStreamFile(targetDirectory);
				if (in != null) {
					if (attachtype.matches(".png") || attachtype.matches(".PNG")) {
						response.setContentType("image/png");
					} else if (attachtype.matches(".bmp") || attachtype.matches(".BMP")) {
						response.setContentType("image/bmp");
					} else if (attachtype.matches(".gif") || attachtype.matches(".GIF")) {
						response.setContentType("image/gif");
					} else if (attachtype.matches(".jpg") || attachtype.matches(".JPG")) {
						response.setContentType("image/jpg");
					} else if (attachtype.matches(".jpeg") || attachtype.matches(".JPEG")) {
						response.setContentType("image/jpeg");
					}
					FileUtils.copy(in, response.getOutputStream());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 下载
	 * @param id
	 */
	@LoginAuth
	@RequestMapping(value="postDownloadattach.hx", method=RequestMethod.POST)
	public void postDownloadattach(HttpServletResponse response, String id) {
		attachdownload(response, id);
	}
	
	/**
	 * 下载，用于部署说明
	 * @param id
	 */
	@NoLoginAuth
	@RequestMapping(value="getDownloadattach.hx", method=RequestMethod.GET)
	public void getDownloadattach(HttpServletResponse response, String id) {
		attachdownload(response, id);
	}
	
	/**
	 * 下载处理
	 * @param response
	 * @param id
	 */
	private void attachdownload(HttpServletResponse response, String id) {
		try {
			AttachDTO attachDTO = attachService.queryAttachmentById(id);
			String attachtype = attachDTO.getAttachType();
			String attachname = attachDTO.getAttachName() + attachtype;
			String attachorigname = attachDTO.getAttachOrigName() + attachtype;
			String attachpath = attachDTO.getAttachPath();
			String targetDirectory = getUploadfiledir();
			targetDirectory = targetDirectory + File.separatorChar + attachpath + File.separatorChar + attachname;
			FileUtils.download(response, attachorigname, targetDirectory);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除附件
	 * @param attachDTO
	 * @return
	 */
	@LoginAuth
	@RequestMapping(value="delattach.hx", method=RequestMethod.POST)
	@ResponseBody
	public Object delattach(AttachDTO attachDTO) {
		AttachDTO attach = attachService.queryAttachmentById(attachDTO.getHxuuid());
		String attachpath = attach.getAttachPath();
		String targetDirectory = getUploadfiledir();
		targetDirectory = targetDirectory + File.separatorChar + attachpath;
		int result = attachService.delAttachById(attachDTO.getHxuuid());
		if (result == 1) {
			FileUtils.delAllFile(targetDirectory);
			return pushMsg(Boolean.TRUE,"附件删除成功");
		} else {
			return pushMsg(Boolean.FALSE,"附件删除失败");
		}
	}
	
	/**
	 * 查看图片
	 * @param request
	 * @param attrId
	 * @return
	 * 创建日期：2017-2-4  创建人：yyp
	 */
	@NoLoginAuth 
	@RequestMapping(value="queryAttrPath.hx", method=RequestMethod.POST)
	@ResponseBody
	public Object queryAttrPath(HttpServletRequest request,String attrId){
		try{
			AttachDTO attachDTO = attachService.queryAttachmentById(attrId);
			String attachtype = attachDTO.getAttachType();
			String attachname = attachDTO.getAttachName() + attachtype;
			String attachpath = attachDTO.getAttachPath();
			String targetDirectory = getUploadfiledir();
			targetDirectory = getDisplayphotodir() + File.separatorChar + attachpath + File.separatorChar + attachname;
			return pushMsg(Boolean.TRUE, "附件路径查询成功", "attachPath",targetDirectory);
		}catch(Exception e){
			logger.error(e);
			return pushMsg(Boolean.FALSE, "附件路径查询失败");
		}
	}
}
