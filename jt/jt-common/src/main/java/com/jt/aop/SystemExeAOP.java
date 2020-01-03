package com.jt.aop;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jt.vo.SysResult;

@RestControllerAdvice
public class SystemExeAOP {
		@ExceptionHandler(RuntimeException.class)
		public SysResult fail(RuntimeException e) {
			e.printStackTrace();
			return SysResult.fail();
		}
}
