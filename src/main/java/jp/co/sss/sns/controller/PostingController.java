package jp.co.sss.sns.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sss.sns.entity.Comment;
import jp.co.sss.sns.entity.Posting;
import jp.co.sss.sns.form.PostingForm;
import jp.co.sss.sns.repository.CommentRepository;
import jp.co.sss.sns.repository.PostingRepository;
import jp.co.sss.sns.service.PostingService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
class PostingController {

	// DI
	private final PostingRepository postingRepository;
	private final HttpSession session;
	private final CommentRepository commentRepository;
	private final PostingService postingService;

	// 投稿記事一覧表示機能
	@RequestMapping("/snssns/findAll")
	public String showList(Model model) {

		List<Posting> posting = postingRepository.findAll();
		if (!posting.isEmpty()) {
			model.addAttribute("posting", posting);
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

	// 投稿確認画面へ（登録）機能
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm");
		Date date = new Date();
		LocalDateTime ldt = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
		
		// DateからInstantを取得し、それをLocalDateTimeに変換
		ldt = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		// DateTimeFormatterを作成
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

		// LocalDateTimeを指定した形式でフォーマット
		String formattedDateTime = ldt.format(formatter);
		LocalDateTime localDateTime = LocalDateTime.parse(formattedDateTime, formatter);

		// 結果を表示
		posting.setInsertDate(localDateTime);
		postingRepository.save(posting);
		return "redirect:/snssns/complete";
	}

	// 投稿完了画面表示
	@RequestMapping("/snssns/complete")
	public String postComplete() {
		return "posting/posting_complete";
	}

	// 記事情報を全件検索(新着順)
	@RequestMapping("/sns/newSort")
	public String newSort(Model model, Integer postingId) throws Exception {
		Posting posting = new Posting();
		// セッションにカテゴリIDが保存されている場合は取得
		try {
			if (session.getAttribute("sortPostingId") != null) {

				postingId = (Integer) session.getAttribute("sortPostingId");
				// 情報をセット
				posting.setId(postingId);
			}

		} catch (Exception e) {
			// nullの場合は検索から取得し記事IDを使用
			posting.setId(postingId);
		}

		List<Posting> postingList = postingRepository.findAllByOrderByInsertDateDesc();

		// 記事情報をViewへ渡す
		model.addAttribute("posting", postingList);
		model.addAttribute("sortNumber", 2);
		return "index/index";
	}

	// 記事情報を全件検索(古い順)
	@RequestMapping(path = "/sns/oldSort")
	public String oldSort(Model model, Integer postingId) throws Exception {
		Posting posting = new Posting();
		// セッションにカテゴリIDが保存されている場合は取得
		try {
			if (session.getAttribute("sortPostingId") != null) {

				postingId = (Integer) session.getAttribute("sortPostingId");
				// 情報をセット
				posting.setId(postingId);
			}

		} catch (Exception e) {
			// nullの場合は検索から取得し記事IDを使用
			posting.setId(postingId);
		}
		List<Posting> postingList = postingRepository.findAllByOrderByInsertDateAsc();

		// 記事情報をViewへ渡す
		model.addAttribute("posting", postingList);
		model.addAttribute("sortNumber", 3);
		return "index/index";
	}

	// コメントが多い順検索
	@RequestMapping("/sns/commentManySort")
	public String commentManySort(Model model) {

//				List<Object[]> commentList = commentRepository.findByPostingIdAndCountpOrderByCountpDesc();
		List<Comment> commentList = commentRepository.findAllByOrderByCoDesc();
//		List<Comment> commentList = commentRepository.findAll();

		// 記事情報をViewへ渡す
		model.addAttribute("postings", commentList);
		model.addAttribute("sortNumber", 4);
		return "index/index";
	}

	// コメントが少ない順検索
	@RequestMapping("/sns/commentLessSort")
	public String commentLessSort(Model model) {

//							List<Object[]> commentList = commentRepository.findByPostingIdAndCountpOrderByCountpDesc();
		List<Comment> commentList = commentRepository.findAllByOrderByCoDesc();

		// 記事情報をViewへ渡す
		model.addAttribute("posting", commentList);
		model.addAttribute("sortNumber", 5);
		return "index/index";
	}

	// タイトル検索 新着順
	@RequestMapping("/postingTitle/search")
	public String searchPostingTitle(Model model, String title) {

		Posting posting = new Posting();

		// セッションに近況タイトル検索ワードが保存されている場合は取得
		if (session.getAttribute("titles") != null) {
			title = (String) session.getAttribute("titles");
			// 前画面の商品名検索欄で入力された商品名検索ワードをセット
			posting.setTitle(title);
		} else {
			// nullの場合は前画面の近況タイトル検索欄に入力された近況タイトル検索ワードを使用
			posting.setTitle(title);
		}

		List<Posting> postingList = postingRepository.findByTitleLikeOrderByInsertDateDesc("%" + title + "%");

		// エンティティ内の検索結果をJavaBeansにコピー
//		List<PostingBean> postingBeanList = BeanCopy.copyEntityToPostingBean(postingList.getContent());

		// 近況タイトルをViewへ渡す
		model.addAttribute("posting", postingList);
		return "index/index";
	}

}
