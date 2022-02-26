package com.example.demo.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.User;

import lombok.Data;

//시큐리티가 주소요청을 낚아채서 로그인 진행 진행이 완료가 되면 session을 만들어 준다
//이 session은 시큐리티가 관리하는 session임 key값으로 구분함
//(Security context에 저장) => 이때 저장 할수 있는 객체 형태가 지정되어있음 이걸 맞춰주어함
// Authentication 객체에 저장할 수 있는 유일한 타입

// User를 UserDetails형태로 저장해야함

/// Authentication > Authentication > UserDetails[원래 이름이 이럼 고정임](implements 하여 사용)
@Data
public class PrincipalDetails implements UserDetails {

	private User user;

	public PrincipalDetails(User user) {
		super();
		this.user = user;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	// 해당 유저의 권한을 리턴함
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collet = new ArrayList<GrantedAuthority>();
		collet.add(() -> {
			return user.getRole();///// 우리가 지정한 유저의 권한
		});
		return collet;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}