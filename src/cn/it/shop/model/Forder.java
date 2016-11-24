package cn.it.shop.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Forder entity. @author MyEclipse Persistence Tools
 */

public class Forder implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String phone;
	private String remark;
	private BigDecimal total;
	private String post;
	private String address;
	private User user;
	private Status status;
	private Set<Sorder> sorderSet;
	
	// Constructors

	/** default constructor */
	public Forder() {
	}

	public Forder(Set<Sorder> sorderSet) {
		super();
		this.sorderSet = sorderSet;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getPost() {
		return this.post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Set<Sorder> getSorderSet() {
		return sorderSet;
	}

	public void setSorderSet(Set<Sorder> sorderSet) {
		this.sorderSet = sorderSet;
	}

}