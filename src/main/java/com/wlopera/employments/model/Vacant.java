package com.wlopera.employments.model;

import java.util.Date;

public class Vacant {

	private Integer id;
	private String name;
	private String description;
	private Date date;
	private double salary;
	private Integer outstanding = 1;
	private String image = "without-image.png";
	private String status;
	private String details;
	private String category;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Integer getOutstanding() {
		return outstanding;
	}

	public void setOutstanding(Integer outstanding) {
		this.outstanding = outstanding;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Vacant [id=" + id + ", name=" + name + ", description=" + description + ", date=" + date + ", salary="
				+ salary + ", outstanding=" + outstanding + ", image=" + image + ", status=" + status + ", details="
				+ details + ", category=" + category + "]";
	}

}
