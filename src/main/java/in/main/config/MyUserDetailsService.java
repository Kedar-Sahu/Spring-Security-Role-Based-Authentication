package in.main.config;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.main.entity.User;
import in.main.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepository.findByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("user not found");
		}
		MyUserDetails userDetails=new MyUserDetails(user);
		
		List<SimpleGrantedAuthority> authorities=user.getRole().getPrivillege().stream().map(priv->new SimpleGrantedAuthority(priv.getName()))
				.collect(Collectors.toList());
		authorities.add(new SimpleGrantedAuthority("ROLR_"+user.getRole().getName()));
		userDetails.setAuthorities(authorities);
		return userDetails;
	}

}
