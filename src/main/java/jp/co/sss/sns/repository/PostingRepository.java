package jp.co.sss.sns.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.sns.entity.Posting;

public interface PostingRepository extends JpaRepository<Posting, Integer> {

//	@Query("SELECT pos FROM Posting pos WHERE pos.date >= :date1 AND pos.date < :date2")
//	List<Posting> findByMonth(@Param("date1") Date date1, @Param("date2") Date date2);
	// 記事情報を新着順で検索
//		public Page<Posting> findByDeleteFlagOrderByInsertDateDesc(int deleteFlag, Pageable pageable);
	public Page<Posting> findByOrderByInsertDateDesc(Pageable pageable);
//		// 商品情報を売れ筋順で検索
//		@Query(value = JPQLConstant.FIND_ALL_ITEMS_ORDER_BY_ORDERNUM, nativeQuery = true)
//		public Page<Item> findByDeleteFlagOrderByOrderNumDesc(Pageable pageable);
//
//		// 商品情報を新着順で検索（カテゴリ検索）
//		public Page<Item> findByCategoryAndDeleteFlagOrderByInsertDateDesc(Category category,int deleteFlag, Pageable pageable);
//
//		// 商品情報を売れ筋順で検索（カテゴリ検索）
//		@Query(value = JPQLConstant.FIND_ITEMS_BY_CATEGORIES_ORDER_BY_QUANTITY, nativeQuery = true)
//		public Page<Item> findByCategoryAndDeleteFlagOrderByOrderNumDesc(Integer categoryId, Pageable pageable);
//
//		//オリジナル機能 商品名検索（新着順）
//		public Page<Item> findByDeleteFlagAndNameLikeOrderByInsertDateDesc(int deleteFlag,String name,Pageable pageable);
//
//		//オリジナル機能 商品名検索（売れ筋順）
//		@Query(value = JPQLConstant.FIND_ITEMS_BY_ITEMS_ORDER_BY_QUANTITY, nativeQuery = true)
//		public Page<Item> findByNameLikeAndDeleteFlagOrderByOrderNumDesc(String name,Pageable pageable);
//	
}
