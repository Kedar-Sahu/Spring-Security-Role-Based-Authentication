package in.main.config;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import in.main.entity.User;

public class MyUserDetails implements UserDetails{

	private User user;
	
	public MyUserDetails(User user) {
		super();
		this.user = user;
	}
	
	private List<SimpleGrantedAuthority> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
	}
	
	

	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public void setAuthorities(List<SimpleGrantedAuthority> authorities) {
		this.authorities = authorities;
	}



	@Override
	public String getPassword() {
		
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		
		return user.getUsername();
	}

}
