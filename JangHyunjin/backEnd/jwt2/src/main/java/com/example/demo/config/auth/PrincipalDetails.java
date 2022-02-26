package com.example.demo.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.User;

import lombok.Data;

//��ť��Ƽ�� �ּҿ�û�� ����ä�� �α��� ���� ������ �Ϸᰡ �Ǹ� session�� ����� �ش�
//�� session�� ��ť��Ƽ�� �����ϴ� session�� key������ ������
//(Security context�� ����) => �̶� ���� �Ҽ� �ִ� ��ü ���°� �����Ǿ����� �̰� �����־���
// Authentication ��ü�� ������ �� �ִ� ������ Ÿ��

// User�� UserDetails���·� �����ؾ���

/// Authentication > Authentication > UserDetails[���� �̸��� �̷� ������](implements �Ͽ� ���)
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

	// �ش� ������ ������ ������
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collet = new ArrayList<GrantedAuthority>();
		collet.add(() -> {
			return user.getRole();///// �츮�� ������ ������ ����
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