package cn.it.shop.model;

/**
 * Status entity. @author MyEclipse Persistence Tools
 */

public class Status implements java.io.Serializable {

	// Fields

	private Integer id;
	private String status;

	// Constructors

	/** default constructor */
	public Status() {
	}

	/** full constructor */
	public Status(String status) {
		this.status = status;
	}

	public Status(Integer id) {
		this.id = id;
	}
	
	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}