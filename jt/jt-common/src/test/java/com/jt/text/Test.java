package com.jt.text;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.pojo.ItemDesc;

public class Test {
	private static final ObjectMapper MAPPER=new ObjectMapper(); 
	@org.junit.Test
	public void testObj() throws IOException {
		ItemDesc desc=new ItemDesc();
		desc.setItemId(100L).setItemDesc("111").setCreated(new Date()).setUpdated(desc.getCreated());
		String S1 = MAPPER.writeValueAsString(desc);
		System.out.println(S1);
		desc=MAPPER.readValue(S1,ItemDesc.class);
		System.out.println(desc);
	}	
}
