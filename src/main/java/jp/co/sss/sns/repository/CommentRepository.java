package jp.co.sss.sns.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jp.co.sss.sns.entity.Comment;
public interface  CommentRepository  extends JpaRepository<Comment,Integer> {

//		User findByUserIdAndPassword(String userId,String password);
//		List<User> findByUserIdNamedQuery(String name);
		
//		@Query("SELECT COUNT (u) FROM User u WHERE u.userId LIKE :keyword")
//		List<User> findByUserIdQuery(@Param("keyword")String userId);
//		public String userId
//		int countByUser(@Param("keyword")String keyword);
		
//		User findByUserIdAndPassword(String userId,String password);
	
	List<Comment> findByCommentContentsContaining(String commentContents);
	List<Comment> findByPostingId(int id);
	List<Comment> findByPostingIdOrderByInsertDateDesc(int id);
	
//	コメント数が多い順
	@Query(value = "select C.posting_id,count(C.posting_id) CO " + "FROM sns_comment C "
			+ "group by C.posting_id "+ "order by CO"
			, nativeQuery = true)
//	@Query(value = "select c.posting_id,count(c.posting_id) co" + "from sns_comment c"
//			+ "group by c.posting_id order by co desc")
	 List<Object[]> findAllByOrderByCoDesc();
//	@Query(value = "select *" + "from sns_posting p where (select c.comment_content from comment c order by c.comment desc"
//			+ "  order by 'p.insert_date' asc", nativeQuery = true) 
//	
//	 List<Object[]> findByPostingIdAndCountpOrderByCountpDesc();
	
}
