package com.dxc.MongoTest;

public class Student {

	private String name;
	private String roll_no;
	private String dept;
	private double marks;

	public Student(String name, String roll_no, String dept, double marks) {
		super();
		this.name = name;
		this.roll_no = roll_no;
		this.dept = dept;
		this.marks = marks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoll_no() {
		return roll_no;
	}

	public void setRoll_no(String roll_no) {
		this.roll_no = roll_no;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public double getMarks() {
		return marks;
	}

	public void setMarks(double marks) {
		this.marks = marks;
	}

}
