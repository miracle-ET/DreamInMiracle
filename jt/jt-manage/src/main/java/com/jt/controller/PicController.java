package com.jt.controller;

import java.awt.image.BufferedImage;
import java.io.File;

import java.text.SimpleDateFormat;

import java.util.Date;

import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jt.vo.ImageVo;

@RestController
@RequestMapping("/pic")
public class PicController {
	@Value("${uploadpic.path}")
	private String path;
	@Value("${uploadpic.httpUrl}")
	private String httpUrl;

	@PostMapping("/upload")
	@ResponseBody
	public ImageVo upload(@RequestParam("uploadFile") MultipartFile file) throws Exception {
		// 存储图片到本地
		return storePic(file);
	}

	private ImageVo storePic(MultipartFile file) throws Exception {
		// 校验后缀
		String filename = StringUtils.cleanPath(file.getOriginalFilename()).toLowerCase();
		if (!filename.matches("^.+\\.(jpg|gif|png)$")) {
			return ImageVo.fail();
		}

		// 校验安全性
		try {
			BufferedImage image = ImageIO.read(file.getInputStream());
			if (image == null) {
				return ImageVo.fail();
			}
			int width = image.getWidth();
			int height = image.getHeight();
			if (width == 0 || height == 0) {
				return ImageVo.fail();
			}

			// 生成最终路径
			String datePath = new SimpleDateFormat("/yyyy/MM/dd/").format(new Date());
			String finalPath = path + datePath;
			File dirfile = new File(finalPath);
			if (!dirfile.exists()) {
				dirfile.mkdirs();
			}

			// 生成最终文件名
			String finalName = UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."));

			file.transferTo(new File(finalPath + finalName));
			return new ImageVo(0,httpUrl+datePath+finalName, width, height);
		} catch (Exception e) {
			e.printStackTrace();
			return ImageVo.fail();
		}
	}
}
