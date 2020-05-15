package com.sll.blog.service;

import com.sll.blog.po.User;

import javax.jws.soap.SOAPBinding.Use;

public interface UserService {
    User checkUser(String username, String password);

    User checkUser(String username, String password,String role);

    User save(String username,String password1,String password2,String email,String nickname);

    User checkUser(String username);
}
