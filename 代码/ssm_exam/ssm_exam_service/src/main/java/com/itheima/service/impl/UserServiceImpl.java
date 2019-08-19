package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.UserInfoMapper;
import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 王磊
 * @Date 2019/8/15/015
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserDetailsService ,UserService {

    @Autowired
    private UserInfoMapper userInfoMapper ;

    @Autowired
    private PasswordEncoder passwordEncoder ;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1. 根据用户名称查询用户信息以及用户所拥有的角色信息
        UserInfo userInfo = userInfoMapper.findByUsername(username);
        if(userInfo==null){
            return  null ;
        }

        // username  用户名
        // password  密码
        // authorities  用户拥有的角色列表
        // public User(String username, String password, Collection<? extends GrantedAuthority> authorities)
        // username  用户名
        // password  密码
        // boolean enabled  是否开启  true 可用  false 不可用
        // accountNonExpired 账户是否过期   true 未过期   false 已过期
        // credentialsNonExpired 账户认证是否过期    true 未过期    false 已过期
        // accountNonLocked    账户是否被锁定   true 未锁定    false 已锁定
        // public User(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities)
        User user = new User(userInfo.getUsername(), userInfo.getPassword(),userInfo.getStatus()==1?true:false,true,true,true, getAuthorities(userInfo.getRoles()));
        return user;
    }

    /**
     * 根据用户角色列表,获取用户权限列表
     * @param roles
     * @return
     */
    private List<GrantedAuthority> getAuthorities(List<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }

        return authorities ;
    }


    @Override
    public List<UserInfo> findByPage(Integer pageNo, Integer rows) {
        PageHelper.startPage(pageNo,rows);
        return userInfoMapper.findAll();
    }

    @Override
    public void add(UserInfo userInfo) {
        //对密码进行加密
        String encode = passwordEncoder.encode(userInfo.getPassword());
        //使用加密之后的字符串覆盖原来的字符串
        userInfo.setPassword(encode);
        //保存用户数据到数据库
        userInfoMapper.add(userInfo);
    }

    @Override
    public UserInfo findDetail(String id) {
        return userInfoMapper.findById(id);
    }
}
