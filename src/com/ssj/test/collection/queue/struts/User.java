package com.ssj.test.collection.queue.struts;


public class User {
	private int userID;
	private String name;
	
	
	public User() {}
	
	public User(int userID, String name) {
		super();
		this.userID = userID;
		this.name = name;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + userID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userID != other.userID)
			return false;
		return true;
	}
	
	
	
	

}
