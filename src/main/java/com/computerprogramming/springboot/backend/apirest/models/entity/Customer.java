package com.computerprogramming.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "customers")
public class Customer implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;
	private String lastName;
	@Column(nullable = false, unique = true)
	private String email;

	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;

	@PrePersist
	public void prePersist()
		{
			createAt = new Date();
		}

	public Long getId()
		{
			return id;
		}

	public void setId(Long id)
		{
			this.id = id;
		}

	public String getName()
		{
			return name;
		}

	public void setName(String name)
		{
			this.name = name;
		}

	public String getLastName()
		{
			return lastName;
		}

	public void setLastName(String lastName)
		{
			this.lastName = lastName;
		}

	public String getEmail()
		{
			return email;
		}

	public void setEmail(String email)
		{
			this.email = email;
		}

	public Date getCreateAt()
		{
			return createAt;
		}

	public void setCreateAt(Date createAt)
		{
			this.createAt = createAt;
		}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
