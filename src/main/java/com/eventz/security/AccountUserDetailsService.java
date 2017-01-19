package com.eventz.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.eventz.model.Role;
import com.eventz.service.AccountService;

@Service
public class AccountUserDetailsService implements UserDetailsService {

	@Autowired
	private AccountService accountService;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.eventz.model.User user=accountService.findByUserName(username);
		
		if(user==null){
			return null;
		}
		
		Collection<GrantedAuthority> grantedAuthorities= new ArrayList<GrantedAuthority>();
		for(Role role: user.getRoles()){
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getCode()));
		}
		
		User userDetails= new User(user.getUserName(),user.getPassword(),grantedAuthorities);
		return userDetails;
	}

}
