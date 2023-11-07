package jp.co.sss.sns.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.sns.entity.User;
import jp.co.sss.sns.repository.UserRepository;

@Service
public class UserService {

	// DI
	@Autowired
	UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	// ユーザー削除
	public void deleteUserInfo(String userName) {

		// ユーザーに関する全情報を削除
		userRepository.deleteByUserName(userName);
//			postRepository.deleteByUsername(username);
//			commentRepository.deleteByUsername(username);
	}

}
