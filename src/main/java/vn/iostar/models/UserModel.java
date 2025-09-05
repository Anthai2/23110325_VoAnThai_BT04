package vn.iostar.models;

import java.io.Serializable;
import java.sql.Date;

public class UserModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String username;
	private String password;
	private String images;
	private String fullname;
	private String email;
	private String avatar;
	private int roleid;
	private String phone;
	private Date createDate;

	public UserModel() {
		super();
	}

	public UserModel(int id, String username, String password, String images, String fullname, String email,
			String avatar, int roleid, String phone, Date createdDate) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.images = images;
		this.fullname = fullname;
		this.email = email;
		this.avatar = avatar;
		this.roleid = roleid;
		this.phone = phone;
		this.createDate = createdDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreatedDate() {
		return createDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createDate = createdDate;
	}

	@Override
	public String toString() {
		return "UserModel [id=" + id + ", username=" + username + ", password=" + password + ", images=" + images
				+ ", fullname=" + fullname + ", email=" + email + ", avatar=" + avatar + ", roleid=" + roleid
				+ ", phone=" + phone + ", createdDate=" + createDate + "]";
	}

}
