package jp.co.sss.sns.repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.co.sss.sns.entity.Like;

public interface LikeRepository  extends JpaRepository<Like,Long> {
	//SQLのcount関数で、投稿ごとの「いいね」数を集計しています。結果をMapクラスで受けていますが、KeyとValueは以下の割り当てになってます。Key・・・投稿ID Value・・・「いいね」の総数
	 @Query(value = "select post_id, count(*) from sns_like group by post_id", nativeQuery = true)
	  public List<Object[]> getLikeCount();

	  default Map<Integer, BigInteger> findLikeCount() {
	    Map<Integer, BigInteger> map = new HashMap<Integer, BigInteger>();
	    for (int i = 0; i < getLikeCount().size(); i++) {
	      int key = (int) getLikeCount().get(i)[0];
	      BigInteger value = (BigInteger) getLikeCount().get(i)[1];
	      map.put(key, value);
	    }
	    return map;
	  }
	  
	 // こちらでは、LikeRepositoryのfindMyLikesメソッドで自分がいいねした投稿IDを検索しています。Integer型のListで結果を取得する形にしました。
	  @Query(value = "select post_id from sns_like where user_id = :username", nativeQuery = true)
	  public Object[] getMyLikes(String username);
	  	
	  default List<Integer> findMyLikes(String username) {
	    List<Integer> myLikes = new ArrayList<Integer>();
	    Object[] myLikesObj = getMyLikes(username);
	      for (int i = 0; i < myLikesObj.length; i++) {
	        myLikes.add((Integer)myLikesObj[i]);
	      }
	      
	    return myLikes;
	  }
	  boolean existsByUserAndPosting(String user,String posting);
	  
}
