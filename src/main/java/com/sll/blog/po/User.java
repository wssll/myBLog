package com.sll.blog.po;

import lombok.Data;
import sun.rmi.runtime.Log;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/*
uniqueConstraints = {@UniqueConstraint(columnNames = "username")}设置属性唯一
 */
@Data
@Entity
@Table(name = "t_user",uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String nickname;
    @Column(name = "username")
    private String username;
    private String password;
    private String email;
    private String avatar;
    private Integer type;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    private String role;

    @OneToMany(mappedBy = "user")
    private List<Blog> blogs = new ArrayList<>();

    public User() {
    }
}
