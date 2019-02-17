package com.dengbin.practise;
/**
 * Description: this关键字，this调用本类方法
 * @author dengbin
 * @date 2019年2月17日
 * @time 下午3:10:30
 */
public class This_1 {

	public static void main(String[] args) {
		Person p = new Person("张三",25);
		p.tell();
		
		Dpt d = new Dpt(1001, "技术部", "北京");
		System.out.println(d.getDptInfo());

	}

}

class Person {
	private String name;
	private int age;
	public  Person() {
		System.out.println("***** 对象初始化 *****");
	}
	
	public Person(String name) {
		this();
		this.name = name;
	}
	
	public Person(String name, int age) {
		this(name);
		this.age = age;
	}
	
	public void tell() {
		System.out.println("姓名："+this.name+"；年龄："+this.age);
	}
	
}

class Dpt {
	private long dptId;
	private String dptName;
	private String dptLoc;
	public Dpt() {}
	public Dpt(long dptId, String dptName, String dptLoc) {
		this();
		this.dptId =dptId;
		this.dptName = dptName;
		this.dptLoc = dptLoc;
	}
	
	public void setDptId(long dptId) {
		this.dptId = dptId;
	}
	
	public long getDptId() {
		return this.dptId;
	}
	
	public void setDptName(String dptName) {
		this.dptName = dptName;
	}
	
	public String getDptName() {
		return this.dptName;
	}
	
	public void setDptLoc(String dptLoc) {
		this.dptLoc = dptLoc;
	}
	
	public String getDptLoc() {
		return this.dptLoc;
	}
	
	public String getDptInfo() {
		return"【部门信息】部门编号："+this.dptId+"；部门名称："+this.dptName+"；部门位置："+this.dptLoc;
	}
}














