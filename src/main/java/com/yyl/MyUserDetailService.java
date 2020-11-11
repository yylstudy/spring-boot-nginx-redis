package com.yyl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020-11-10
 */
@Component
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select id,name,age,password from " +
                "user where name='"+username+"'");
        if(list.size()==0){
            throw new UsernameNotFoundException("用户名不存在");
        }
        String password = (String)list.get(0).get("password");
        Integer age = (Integer)list.get(0).get("age");
        String id = (String)list.get(0).get("id");
        List<GrantedAuthority> authority = new ArrayList<>();
        MyUser user = new MyUser(username,password,authority);
        user.setAge(age.toString());
        user.setId(id);
        return user;
    }
}
