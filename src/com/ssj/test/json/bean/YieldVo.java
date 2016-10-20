package com.ssj.test.json.bean;

import java.math.BigDecimal;
import java.util.Date;

public class YieldVo {
		private int price_id;
		private String product_id;
		private String price_type;
		private BigDecimal min_investamt;
		private BigDecimal max_investamt;
		private int min_term;
		private int max_term;
		private int free_month;
		private BigDecimal expect_profit;
		private Date create_time;
		private Date modify_time;
		private String remark;
		
		
		public YieldVo() {
		}
		
		public String getPrice_type() {
			return price_type;
		}
		public void setPrice_type(String price_type) {
			this.price_type = price_type;
		}
		public int getFree_month() {
			return free_month;
		}
		public void setFree_month(int free_month) {
			this.free_month = free_month;
		}
		public BigDecimal getMax_investamt() {
			return max_investamt;
		}
		public void setMax_investamt(BigDecimal max_investamt) {
			this.max_investamt = max_investamt;
		}
		public Date getCreate_time() {
			return create_time;
		}
		public void setCreate_time(Date create_time) {
			this.create_time = create_time;
		}

		public int getPrice_id() {
			return price_id;
		}

		public void setPrice_id(int price_id) {
			this.price_id = price_id;
		}

		public String getProduct_id() {
			return product_id;
		}

		public void setProduct_id(String product_id) {
			this.product_id = product_id;
		}

		public BigDecimal getMin_investamt() {
			return min_investamt;
		}

		public void setMin_investamt(BigDecimal min_investamt) {
			this.min_investamt = min_investamt;
		}

		public int getMin_term() {
			return min_term;
		}

		public void setMin_term(int min_term) {
			this.min_term = min_term;
		}

		public int getMax_term() {
			return max_term;
		}

		public void setMax_term(int max_term) {
			this.max_term = max_term;
		}

		public BigDecimal getExpect_profit() {
			return expect_profit;
		}

		public void setExpect_profit(BigDecimal expect_profit) {
			this.expect_profit = expect_profit;
		}

		public Date getModify_time() {
			return modify_time;
		}

		public void setModify_time(Date modify_time) {
			this.modify_time = modify_time;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}
		
		
}
