package com.ssj.bean;

import java.util.Date;

public class Book {
		private String name;
		private Date date;
		
		
		public Book() {}
		public Book(String name, Date date) {
			this.name = name;
			this.date = date;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		
		
}
