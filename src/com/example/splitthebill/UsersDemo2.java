package com.example.splitthebill;

public class UsersDemo2 {
	private String name;
	private int amount;
	private int id;
	private String condition;
	
	public UsersDemo2(String name, int amount, int id, String condition) {
		super();
		this.name = name;
		this.amount = amount;
		this.id = id;
		this.condition = condition;
	}
	
	public String getName() {
		return name;
	}
	public int getAmount() {
		return amount;
	}
	public int getId() {
		return id;
	}
	public String getCondition() {
		return condition;
	}

}
