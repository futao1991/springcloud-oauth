package com.tao.spring.oauthserver.service;

import com.tao.spring.oauthserver.constant.MessageConstant;
import com.tao.spring.oauthserver.domain.SysPermission;
import com.tao.spring.oauthserver.domain.SysRole;
import com.tao.spring.oauthserver.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService {

    private SysRole admin = new SysRole("ADMIN", "管理员");

    private SysRole developer = new SysRole("DEVELOPER", "开发者");

    {
        SysPermission p1 = new SysPermission();
        p1.setCode("orderInfo");
        p1.setName("订单信息");
        p1.setUrl("/order/info");

        SysPermission p2 = new SysPermission();
        p2.setCode("orderDetail");
        p2.setName("订单详情");
        p2.setUrl("/order/detail");

        admin.setPermissionList(Arrays.asList(p1, p2));
        developer.setPermissionList(Arrays.asList(p1));
    }

    private List<SysUser> userList;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initData() {
        String password = passwordEncoder.encode("123456");
        userList = new ArrayList<>();

        SysUser user1 = new SysUser("admin", password);
        user1.setRoleList(Arrays.asList(admin));

        SysUser user2 = new SysUser("test", password);
        user2.setRoleList(Arrays.asList(developer));

        userList.add(user1);
        userList.add(user2);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<SysUser> sysUserOptional = userList.stream().filter(item -> item.getUsername().equals(userName))
                .findFirst();
        if (!sysUserOptional.isPresent()) {
            throw new UsernameNotFoundException(MessageConstant.USERNAME_PASSWORD_ERROR);
        }
        SysUser sysUser = sysUserOptional.get();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (SysRole role : sysUser.getRoleList()) {
            for (SysPermission permission : role.getPermissionList()) {
                authorities.add(new SimpleGrantedAuthority(permission.getCode()));
            }
        }

        return new User(sysUser.getUsername(), sysUser.getPassword(), authorities);
    }
}
