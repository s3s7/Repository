package jp.co.sss.sns.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.sns.entity.User;

public interface  UserRepository  extends JpaRepository<User,Long>{

//	User findByUserIdAndPassword(String userId,String password);
//	List<User> findByUserIdNamedQuery(String name);
	
//	@Query("SELECT COUNT (u) FROM User u WHERE u.userId LIKE :keyword")
//	List<User> findByUserIdQuery(@Param("keyword")String userId);
//	public String userId
//	int countByUser(@Param("keyword")String keyword);
	
	User findByUserIdAndPassword(String userId,String password);
}
