package com.furious.oauth2.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static final String PASSWORD_FIELD = "password";
    public static final String USER_SQL = "SELECT * FROM user WHERE username = ?";
    public static final String ROLE_SQL = "SELECT r.code AS role FROM role r,user_role ur,user u WHERE " +
            "r.id = ur.role_id AND ur.user_id = u.id AND u.username = ?";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) {
            throw new UsernameNotFoundException(username);
        }
        Map<String, Object> map;
        try {
            map = jdbcTemplate.queryForMap(USER_SQL, username);
        } catch (DataAccessException e) {
            throw new UsernameNotFoundException(username);
        }
        String password = String.valueOf(map.get(PASSWORD_FIELD));
        List<SimpleGrantedAuthority> authoritys = new ArrayList<>();
        try {
            List<Map<String, Object>> roles = jdbcTemplate.queryForList(ROLE_SQL, username);
            if (!roles.isEmpty()) {
                roles.forEach(m -> {
                    authoritys.add(new SimpleGrantedAuthority(String.valueOf(m.get("role"))));
                });
            }
        } catch (DataAccessException ignored) {
        }
        return new User(username, password, authoritys);
    }
}
