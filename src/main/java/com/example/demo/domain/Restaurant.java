package com.example.demo.domain;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="gestaoRestaurante")
@Relation(collectionRelation = "restaurants")
public class Restaurant extends RepresentationModel<Restaurant> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable=false,length = 80)
	private String name;
	@Column(nullable=false,length = 80)
	private String url;
	@Column(nullable=false,length = 100)
	private String menu;
	@Column(nullable=false,length = 10)
	private String telephone;
	@Column(nullable=false,length = 20)
	private String priceRange;
	
	@Embedded
	private Address address;
	
	public Restaurant () {}

	public Restaurant(Long id, String name, String url, String menu, String telephone, String priceRange,
			Address address) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.menu = menu;
		this.telephone = telephone;
		this.priceRange = priceRange;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPriceRange() {
		return priceRange;
	}

	public void setPriceRange(String priceRange) {
		this.priceRange = priceRange;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
