package com.business.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Business_Orders")
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer oid;
	private String oName;
	private Double oPrice;
	private Integer oQuantity;
	private Date orderDate;
	private Double totalAmount;
	
	@ManyToOne
	@JoinColumn(name = "user_u_id")
	private User user;

	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public String getoName() {
		return oName;
	}

	public void setoName(String oName) {
		this.oName = oName;
	}

	public Double getoPrice() {
		return oPrice;
	}

	public void setoPrice(Double oPrice) {
		this.oPrice = oPrice;
	}

	public Integer getoQuantity() {
		return oQuantity;
	}

	public void setoQuantity(Integer oQuantity) {
		this.oQuantity = oQuantity;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Orders [oid=" + oid + ", oName=" + oName + ", oPrice=" + oPrice + ", oQuantity=" + oQuantity
				+ ", orderDate=" + orderDate + ", totalAmount=" + totalAmount + ", user=" + user + "]";
	}
	
	
}
