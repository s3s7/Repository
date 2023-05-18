package jp.co.sss.sns.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.co.sss.sns.entity.Posting;

public interface PostingRepository  extends JpaRepository<Posting,Integer> {

//	@Query("SELECT pos FROM Posting pos WHERE pos.date >= :date1 AND pos.date < :date2")
//	List<Posting> findByMonth(@Param("date1") Date date1, @Param("date2") Date date2);
	
}
