package com.ssj.util;

import java.math.BigDecimal;

import org.junit.Test;

/***数值精度计算*/
public class CaculateAccuracy {
	
		@Test
		public void testFloat(){
			//121094.79
				float buy_amount=120000.00f;
				float expected_yield_amount=1006.02f;
				float t_extra_earnings=88.77f;
				float localTxAmount=expected_yield_amount+t_extra_earnings+buy_amount;
				System.out.println(localTxAmount);//121094.8
				BigDecimal buy_amount_b=new BigDecimal(buy_amount);
				BigDecimal expected_yield_amount_b=new BigDecimal(expected_yield_amount);
				BigDecimal t_extra_earnings_b=new BigDecimal(t_extra_earnings);
				BigDecimal localTxAmount_b=buy_amount_b.add(expected_yield_amount_b).add(t_extra_earnings_b);
				System.out.println(localTxAmount_b.floatValue());
				
		}

}
