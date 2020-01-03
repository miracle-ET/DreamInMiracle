package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ImageVo {
	private Integer error;
	private String url;
	private Integer width;
	private Integer height;
	
	public static ImageVo fail() {
		return new ImageVo(1,null,null,null);
	}
}
