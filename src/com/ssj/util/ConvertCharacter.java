package com.ssj.util;


import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

import org.junit.Test;

public class ConvertCharacter 
{
	@Test
	public void  testDecimal(){
		 BigDecimal b1 = new BigDecimal("1");
         BigDecimal b2 = new BigDecimal("3");
//         System.out.println(b1.divide(b2,10,BigDecimal.ROUND_HALF_UP).doubleValue());
//         System.out.println(b1.compareTo(b2));
         System.out.println(b1.add(b2).doubleValue());
	}
}
