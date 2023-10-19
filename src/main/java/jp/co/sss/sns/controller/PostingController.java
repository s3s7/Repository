package jp.co.sss.sns.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sss.sns.entity.Posting;
import jp.co.sss.sns.form.PostingForm;
import jp.co.sss.sns.repository.PostingRepository;
import jp.co.sss.sns.repository.UserRepository;


@Controller
class PostingController {
	@Autowired
	PostingRepository postingRepository;
	@Autowired
	HttpSession session;
	@Autowired
	UserRepository userRepository;

	// 投稿記事一覧表示機能
	@RequestMapping("/snssns/findAll")
	public String showList(Model model) {

		List<Posting> posting = postingRepository.findAll();
		if (!posting.isEmpty()) {
			session.setAttribute("posting", posting);
		} else {
			model.addAttribute("errMessage", "投稿記事は存在しません。");
		}
		return "index/index";
	}

	// 投稿ページ遷移処理
	@RequestMapping("/snssns/posts")
	public String postInput(Model model, @ModelAttribute PostingForm form) {
		return "posting/posting_page";
	}

	// 投稿確認画面へ（登録）機能 入力チェックあり
	@RequestMapping("/snssns/posting")
	public String postCheck(Model model, @Valid @ModelAttribute PostingForm form, BindingResult result) {
		if (result.hasErrors()) {
			// エラーリスト
			List<ObjectError> errorList = result.getAllErrors();
			model.addAttribute("errorList", errorList);
			return postInput(model, form);
		}
		String content = form.getContents();
		content = form.getTitle();

		if (content != null) {
			model.addAttribute("contents", content);
			// 条件を満たした場合投稿内容確認画面へ
			return "posting/posting_check";

		} else {
			return postInput(model, form);
		}

	}

	// 投稿するボタン（記事登録処理）
	@RequestMapping("/snssns/dopost")
	public String postRegist(Model model, @ModelAttribute PostingForm form) {
		// 投稿内容情報の生成
		Posting posting = new Posting();

		// 入力値をリポジトリ保存
		posting.setContents(form.getContents());
		posting.setTitle(form.getTitle());
		// 投稿時間の取得
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		Date date = new Date();
//		LocalDateTime date = LocalDateTime.now();
		// SimpleDateFormatクラスのparseメソッドを使うにはthrows句を使ってParseExceptionなどに例外を投げるか、try-catch構文で例外処理を行う必要
		try {
			date = sdf.parse(DateTimeFormatter.ofPattern("yyyyMMddHHmm").format(LocalDateTime.now()));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		posting.setInsertDate(date);
		postingRepository.save(posting);
		return "redirect:/snssns/complete";
	}

	// 投稿完了画面表示
	@RequestMapping("/snssns/complete")
	public String postComplete() {
		return "posting/posting_complete";
	}

	// 記事情報を全件検索(新着順)
	@RequestMapping(path = "/sns/newSort")
	public String newSort(Model model, Pageable pageable, Integer postingId) throws Exception {
		Posting posting = new Posting();
		// セッションにカテゴリIDが保存されている場合は取得
		try {
			if (session.getAttribute("sortPostingId") != null) {

				postingId = (Integer) session.getAttribute("sortPostingId");
				// カテゴリ情報をセット
				posting.setId(postingId);
			}

		} catch (Exception e) {
			// nullの場合は検索から取得し記事IDを使用
			posting.setId(postingId);
		}
		List<Posting> postingList = postingRepository.findAllByOrderByInsertDateAsc();

		System.out.println("s");
		// 記事情報をViewへ渡す
		model.addAttribute("posthing", postingList);
		model.addAttribute("sortNumber", 2);
		return "index/index";
	}

	// 記事情報を全件検索(古い順)
	@RequestMapping(path = "/sns/oldSort")
	public String oldSort(Model model) {

		List<Object[]> postingList = postingRepository.findAllByOrderByInsertDateDesc();

		// 記事情報をViewへ渡す
		model.addAttribute("posthing", postingList);
		model.addAttribute("sortNumber", 3);
		return "index/index";
	}
	
	// 記事情報を全件検索(古い順)
		@RequestMapping(path = "/sns/commentSort")
		public String commentSort(Model model) {

			List<Object[]> commentList = postingRepository.findAllByOrderByCommentDesc();

			// 記事情報をViewへ渡す
			model.addAttribute("posting", commentList);
			model.addAttribute("sortNumber", 3);
			return "index/index";
		}

		
//	/**
//	 * 商品一覧画面（カテゴリ検索、売れ筋順） 表示処理
//	 *
//	 * @param model    Viewとの値受渡し
//	 * @param pageable ページング情報
//	 * @return "/item/list/item_list" 商品一覧画面へ
//	 *
//	 */
//	@RequestMapping(path = "/item/list/category/2")
//	public String showPostFOrderByInsertDateDesc(Integer postingId, Model model, Pageable pageable) {
//		Posting posting = new Posting();
//
//		// セッションにカテゴリIDが保存されている場合は取得
//		if (session.getAttribute("sortPostingId") != null) {
//			postingId = (Integer) session.getAttribute("sortPostingId");
//			// カテゴリ情報をセット
//			posting.setId(postingId);
//		} else {
//			// nullの場合はカテゴリ検索から取得したカテゴリIDを使用
//			posting.setId(postingId);
//		}
//
//		// カテゴリに該当する商品情報を検索(売れ筋順)
//		Page<Posting> postList = PostingRepository.findBypostingOrderByInsertDatedesc(postingId, pageable);
//
//		// 商品情報をViewへ渡す
//		model.addAttribute("pages", postList);
//		model.addAttribute("sortNumber", 4);
//
//		// セッションにカテゴリIDを一時保存
//		session.setAttribute("sortpostingId", postingId);
//
//		return "item/list/item_list";
//	}

	// 履歴画面へ遷移(日付昇順)
//		@GetMapping("/money-record/history/date-asc/{categoryCode}/{startDate}/{endDate}")
//	@GetMapping("/snssns/dateAsc{endDate}")
//	public String showRecordsOrderByDateAsc(@ModelAttribute("postingCode") String posthingCode,
//				@ModelAttribute("startDate") String startDate, @ModelAttribute("endDate") String endDate,
//				Model model) {
//
////			// 並び替え未選択の場合
////			if (categoryCode.equals("all")) {
////				categoryCode = "%";
////			}
//		if (postingCode.equals(null)) {
//			postingCode = "%";
//		}



//
//			// カテゴリ一覧を取得
//			Map<Integer, String> categories = CategoryCodeToName.Categories;
//			Map<Integer, String> categoriesToIcon = CategoryCodeToIcon.CategoriesToIcon;

	// Map<Integer, String> postings = PostingCodeToName.Postings;
//		Map<Integer, String> postingsToIcon = PostingsCodeToIcon.PostingsToIcon;

//
//			// 上記の条件で絞り込み検索を実施
//			List<MoneyRecordList> records = PostingRepository.findPostingRecordListOrderByDateAsc(loginUser.getName(),
//					categoryCode, startDate, endDate);
//
//			for (int i = 0; i < records.size(); i++) {
//				if (records.get(i).getNote().length() > 13) {
//					records.get(i).setNote(records.get(i).getNote().substring(0, 10) + "…");
//				}
//			}
//
//			// 履歴データがあるかチェック用
//			boolean historyDataExists = mrService.existsHistoryData(records);

	// boolean historyData = mrService.existsHistoryData(records);

//			model.addAttribute("user", userRepository.findByUsername(loginUser.getName()));
//			model.addAttribute("records", records);
//			model.addAttribute("postingToIcon", postingToIcon);
//			model.addAttribute("historyDataExists", historyDataExists);
//			model.addAttribute("categories", categories);
//
//			return "record-history";
}

//}
