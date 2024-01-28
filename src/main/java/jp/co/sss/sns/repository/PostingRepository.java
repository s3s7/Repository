package jp.co.sss.sns.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.co.sss.sns.entity.Posting;

public interface PostingRepository extends JpaRepository<Posting, Integer> {

	// 記事情報を新着順で検索
	public List<Posting> findAllByOrderByInsertDateAsc();

	// 記事情報を古い順で検索
	public List<Posting> findAllByOrderByInsertDateDesc();
	
	//商品名検索（新着順）
//	public List<Posting> findByNameLikeOrderByInsertDate(String name);

//	default String[] findAllDate(String date) {
//		List<String> list = new ArrayList<String>();
//		Object[] datesObj = getFindDate(date);
//		for (int i = 0; i < datesObj.length; i++) {
//			list.add(datesObj[i].toString());
//		}
//		String months[] = list.toArray(new String[list.size()]);
//		for (int i = 0; i < months.length; i++) {
//		}
//		return months;
//}
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
	
//
//		//商品名検索（売れ筋順）
//		@Query(value = JPQLConstant.FIND_ITEMS_BY_ITEMS_ORDER_BY_QUANTITY, nativeQuery = true)
//		public Page<Item> findByNameLikeAndDeleteFlagOrderByOrderNumDesc(String name,Pageable pageable);
//	
	}
