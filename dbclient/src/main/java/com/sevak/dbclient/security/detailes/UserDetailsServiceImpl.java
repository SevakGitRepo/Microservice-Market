package com.sevak.dbclient.security.detailes;


import com.sevak.dbclient.models.User;
import com.sevak.dbclient.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service("myUser")
public class UserDetailsServiceImpl implements UserDetailsService {
    private static User user;
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        user = userRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new);
        return UserDetailsImpl.formUser(user);
    }

    public static User getUser() {
        return user;
    }
    //karanq sencel anenq
//    private List<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
//        return roles.stream().map(r->new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
//    }
}
