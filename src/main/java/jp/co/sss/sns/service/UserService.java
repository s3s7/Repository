package jp.co.sss.sns.service;

import org.springframework.stereotype.Service;

import jp.co.sss.sns.repository.UserRepository;

@Service
public class UserService {
	
//	DI
	private final UserRepository userRepository;
	
	
	public UserService(UserRepository userRepository) {
		
		this.userRepository = userRepository;
	}
	
	
	//ユーザー削除
		public void deleteUserInfo(String username) {
			
			//ユーザーに関する全情報を削除
			userRepository.deleteByUsername(username);
//			postRepository.deleteByUsername(username);
//			commentRepository.deleteByUsername(username);
		}
	
}
