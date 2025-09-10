package vn.iostar.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT")
    private int id;
    @Column(name = "email", columnDefinition = "NVARCHAR(100)")
    private String email;
    @Column(name = "username", columnDefinition = "NVARCHAR(50)")
    private String username;
    @Column(name = "fullname", columnDefinition = "NVARCHAR(100)")
    private String fullname;
    @Column(name = "password", columnDefinition = "NVARCHAR(255)")
    private String password;
    @Column(name = "avatar", columnDefinition = "NVARCHAR(255)")
    private String avatar;
    @Column(name = "roleid", columnDefinition = "INT")
    private int roleid;
    @Column(name = "phone", columnDefinition = "NVARCHAR(20)")
    private String phone;
    @Column(name = "createDate", columnDefinition = "DATETIME")
    private Date createDate;

    public Users() {
    }

    public Users(String email, String username, String fullname, String password, String avatar, int roleid, String phone, Date createDate) {
        this.email = email;
        this.username = username;
        this.fullname = fullname;
        this.password = password;
        this.avatar = avatar;
        this.roleid = roleid;
        this.phone = phone;
        this.createDate = createDate;
    }

    public Users(int id, String email, String username, String password, String fullname, String avatar, int roleid, String phone, Date createDate) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.avatar = avatar;
        this.roleid = roleid;
        this.phone = phone;
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
