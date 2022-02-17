package com.example.catlearn.catlearn.model;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


@Entity
@Data
public class Lesson  implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(length=50)
	private  String code ;
	@Column(length=100)
	private String name ;
	@Column(length=500)
	private String description;
	@Column(length=5)
	private String number;
	@Column
	@Temporal(TemporalType.DATE)	
	private Date dateCreated;
	@Column
	@Temporal(TemporalType.DATE)	
	private Date lastUpdate;
	@Column
	@Temporal(TemporalType.DATE)	
	private Date dateRelease;
	@Column(length=50)
	private String createdBy;
	@Column(length=15)
	private String category;
	@Column(length=50)
	private String duration;
	
	@Column(length=500)
	private String contentFilePath;
	
	@Column(length=300)
	private String contentFileName;
	
	
	@JoinColumn(name = "idModule")
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@JsonBackReference
	private Module module;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
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


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public Date getDateCreated() {
		return dateCreated;
	}


	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}


	public Date getLastUpdate() {
		return lastUpdate;
	}


	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}


	public Date getDateRelease() {
		return dateRelease;
	}


	public void setDateRelease(Date dateRelease) {
		this.dateRelease = dateRelease;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getDuration() {
		return duration;
	}


	public void setDuration(String duration) {
		this.duration = duration;
	}


	public String getContentFilePath() {
		return contentFilePath;
	}


	public void setContentFilePath(String contentFilePath) {
		this.contentFilePath = contentFilePath;
	}


	public String getContentFileName() {
		return contentFileName;
	}


	public void setContentFileName(String contentFileName) {
		this.contentFileName = contentFileName;
	}


	public Module getModule() {
		return module;
	}


	public void setModule(Module module) {
		this.module = module;
	}

		
	
		
}
