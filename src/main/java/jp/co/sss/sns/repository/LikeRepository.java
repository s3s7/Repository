package jp.co.sss.sns.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.sns.entity.Like;

public interface LikeRepository  extends JpaRepository<Like,Long> {  
}
