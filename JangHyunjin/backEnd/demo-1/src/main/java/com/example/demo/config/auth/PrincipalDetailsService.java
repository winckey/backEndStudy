package com.example.demo.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;


// .loginProcessingUrl("/login") ��û�� ���� �ڵ����� userDetailsService Ÿ������ ioc �Ǿ��ִ�
// ��Ŭ���� �� loadUserByUsername�� �ڵ����� ȣ���̵� (��Ģ��)
// ���� �Ѿ���� username�� ã��



@Service
public class PrincipalDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//����Ī�� ���� username���� ���߾�� �� �ȴٸ� ��ť��Ƽ �������� userNameParam���� �ٲپ� �־����
		User user = userRepository.findByUsername(username);// �α��� �˻� ����
		if(user == null) {
			return null;
		}
		return new PrincipalDetails(user);// ���̵� �ִٸ� UserDetails�� ����� PrincipalDetailsȣ��
										  // �̰� ������ ���� �Ǵ��� authentication���� ���ϵȴ�
										  // �׸��� �̰� sesstion�� �޾Ƽ� �����Ѵ�.
	}

}