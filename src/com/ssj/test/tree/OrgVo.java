package com.ssj.test.tree;

public class OrgVo {
 
		private int id;
		private String org_code;
		private String name;
		private String parent_code;
		private String available;
		private int createIndex;
		private int orderIndex;
		private int level;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getOrg_code() {
			return org_code;
		}
		public void setOrg_code(String org_code) {
			this.org_code = org_code;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getParent_code() {
			return parent_code;
		}
		public void setParent_code(String parent_code) {
			this.parent_code = parent_code;
		}
		public String getAvailable() {
			return available;
		}
		public void setAvailable(String available) {
			this.available = available;
		}
		public int getCreateIndex() {
			return createIndex;
		}
		public void setCreateIndex(int createIndex) {
			this.createIndex = createIndex;
		}
		public int getOrderIndex() {
			return orderIndex;
		}
		public void setOrderIndex(int orderIndex) {
			this.orderIndex = orderIndex;
		}
		public int getLevel() {
			return level;
		}
		public void setLevel(int level) {
			this.level = level;
		}
}
