package jp.co.sss.sns.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.sns.entity.User;

public interface  UserRepository  extends JpaRepository<User,Integer>{

//	List<User> findByUserIdNamedQuery(String name);
	
//	@Query("SELECT COUNT (u) FROM User u WHERE u.userId LIKE :keyword")
//	List<User> findByUserIdQuery(@Param("keyword")String userId);
//	public String userId
//	int countByUser(@Param("keyword")String keyword);
	
	User findByUserNameAndPassword(String userName,String password);

	User getReferenceById(Integer id);
String getReferenceByUserName(String userName);
	void deleteByUserName(String userName);
//	User deleteByUserName(String userName);
	
}
