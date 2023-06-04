package jp.co.sss.sns.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.sns.entity.Posting;

public interface PostingRepository  extends JpaRepository<Posting,Integer> {

//	@Query("SELECT pos FROM Posting pos WHERE pos.date >= :date1 AND pos.date < :date2")
//	List<Posting> findByMonth(@Param("date1") Date date1, @Param("date2") Date date2);
	
}
