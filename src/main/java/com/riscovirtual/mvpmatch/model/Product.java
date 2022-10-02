package com.riscovirtual.mvpmatch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "products", uniqueConstraints = { @UniqueConstraint(columnNames = { "productName" }) })
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "productName", nullable = false)
	private String productName;

	@Column(name = "cost", nullable = false)
	private int cost;

	@Column(name = "amountAvailable", nullable = false)
	private int amountAvailable;

	@Column(name = "sellerName", nullable = false)
	private String sellerName;

	public Product() {

	}

	public Product(String productName, int cost, int amountAvailable, String sellerName) {
		this.productName = productName;
		this.cost = cost;
		this.amountAvailable = amountAvailable;
		this.sellerName = sellerName;
	}

	public Integer getId() {
		return id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getAmountAvailable() {
		return amountAvailable;
	}

	public void setAmountAvailable(int amountAvailable) {
		this.amountAvailable = amountAvailable;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerId) {
		this.sellerName = sellerId;
	}

	@Override
	public String toString() {
		return "Product [productName=" + productName + ", cost=" + cost + ", amountAvailable=" + amountAvailable
				+ ", sellerName=" + sellerName + "]";
	}

}
