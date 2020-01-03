package com.cy.pj.sys.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import org.springframework.util.StringUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cy.pj.sys.entity.Commodity;
import com.cy.pj.sys.entity.User;
import com.cy.pj.sys.service.SysCommodityService;
import com.cy.pj.sys.vo.JsonResult;



@Controller
/*需要提供一个访问路径*/
@RequestMapping("/commodity")

public class SysCommodityController {
	
	
	  // 这里的是application.properties中配置的地址
	  @Value("${uploadpic.path}")
	  private String uploadPicPath;
	  private String storePic(MultipartFile file) throws Exception {
		    String filename = StringUtils.cleanPath(file.getOriginalFilename());
		    try {
		      try (InputStream inputStream = file.getInputStream()) {
		    	  System.out.println(Paths.get(uploadPicPath + filename).toString());
		        Files.copy(inputStream, Paths.get(uploadPicPath + filename), // 这里指定了下载的位置
		          StandardCopyOption.REPLACE_EXISTING);
		      }
		    }
		    catch (IOException e) {
		      throw new Exception("失败！" + filename, e);
		    }
		    return filename;
	}
	
	
	
	
	/*可能需要自动注入一个service接口*/
	@Autowired
	private SysCommodityService sysCommodityService;
	
	/*可能需要处理一个请求*/
	
	
	/*用户*/
	@RequestMapping("/doSearch")
	@ResponseBody
	public JsonResult doSearch(String key) {
			return new JsonResult(sysCommodityService.findIDByKey(key));
	}
	@RequestMapping("/doSearchUrl")
	@ResponseBody
	public JsonResult doSearchUrl(Integer id) {
			return new JsonResult(sysCommodityService.findUrlById(id));
	}
	@ResponseBody
	@RequestMapping("/doFindCommodity")
	public JsonResult doFindCommodity(Integer id) {
			return new JsonResult(sysCommodityService.findObjById(id));
	}

	
	
	/*商家&管理*/
	  @PostMapping("/doInsert")
	  @ResponseBody
	  public JsonResult doInsert(@RequestParam("file") MultipartFile file,Commodity commodity) throws Exception {
	    // 存储图片到本地
	    storePic(file);
	    commodity.setUrl("/images/"+file.getOriginalFilename());
	    if(sysCommodityService.insert(commodity,((User) SecurityUtils.getSubject().getPrincipal()).getId())==1) {
			return new JsonResult("上传成功");
		}
		else {
			return new JsonResult("上传失败");
		}
	  }
	
	  	
	  
	  @PostMapping("/doUpdate")
	  @ResponseBody
	  public JsonResult doUpdate(@RequestParam("file") MultipartFile file,Commodity commodity) throws Exception {
	    // 存储图片到本地
	    storePic(file);
	    commodity.setUrl("/images/"+file.getOriginalFilename());
	    if(sysCommodityService.update(commodity,((User) SecurityUtils.getSubject().getPrincipal()).getId())==1) {
			return new JsonResult("修改成功");
		}
		else {
			return new JsonResult("修改失败");
		}
	  }
	
	
	
	
	@RequestMapping("/doDelete")
	@ResponseBody
	public JsonResult doDelete(Integer id) {
		if(sysCommodityService.delete(id)==1) {
			return new JsonResult("删除成功");
		}
		else {
			return new JsonResult("删除失败");
		}
	}
	@ResponseBody
	@RequestMapping("/doFindallCommodity")
	public JsonResult doFindallCommodity(Integer userid) {
			return new JsonResult(sysCommodityService.findObjByUserid(userid));
	}
	
}