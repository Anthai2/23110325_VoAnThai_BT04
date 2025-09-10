package vn.iostar.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
@NamedQuery(
	    name = "Category.findAll",
	    query = "SELECT c FROM Category c ORDER BY c.cate_id DESC"
	)
@Entity
@Table(name = "Category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cate_id", columnDefinition = "INT")
	private int cate_id;
	@Column(name = "cate_name", columnDefinition = "NVARCHAR(255)")
	private String cate_name;
	@Column(name = "description", columnDefinition = "NVARCHAR(MAX)")
	private String description;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private Users user;

	private LocalDateTime created_at = LocalDateTime.now();
	private LocalDateTime updated_at = LocalDateTime.now();

	public Category() {
	}

	public Category(int cate_id, String cate_name, String description, Users user, LocalDateTime created_at,
			LocalDateTime updated_at) {
		this.cate_id = cate_id;
		this.cate_name = cate_name;
		this.description = description;
		this.user = user;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	@PreUpdate
	public void preUpdate() {
		updated_at = LocalDateTime.now();
	}

	public int getCate_id() {
		return cate_id;
	}

	public void setCate_id(int cate_id) {
		this.cate_id = cate_id;
	}

	public String getCate_name() {
		return cate_name;
	}

	public void setCate_name(String cate_name) {
		this.cate_name = cate_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public LocalDateTime getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public void setStatus(int int1) {
		// TODO Auto-generated method stub
		
	}

	public void setImages(String newImg) {
		// TODO Auto-generated method stub
		
	}
}
