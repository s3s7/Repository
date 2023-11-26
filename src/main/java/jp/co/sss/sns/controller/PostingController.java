package jp.co.sss.sns.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

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
import jp.co.sss.sns.repository.CommentRepository;
import jp.co.sss.sns.repository.PostingRepository;
import jp.co.sss.sns.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
class PostingController {

	// DI
	private final PostingRepository postingRepository;
	private final HttpSession session;
	private final CommentRepository commentRepository;

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

		// 記事情報をViewへ渡す
		model.addAttribute("posting", postingList);
		model.addAttribute("sortNumber", 2);
		return "index/index";
	}

	// 記事情報を全件検索(古い順)
//	@RequestMapping(path = "/sns/oldSort")
//	public String oldSort(Model model) {
//
//		List<Object[]> postingList = postingRepository.findAllByOrderByInsertDateDesc();
//
//		// 記事情報をViewへ渡す
//		model.addAttribute("posting", postingList);
//		model.addAttribute("sortNumber", 3);
//		return "index/index";
//	}

	// コメントが多い順検索
	@RequestMapping(path = "/sns/commentManySort")
	public String commentManySort(Model model) {

//				List<Object[]> commentList = commentRepository.findByPostingIdAndCountpOrderByCountpDesc();
		List<Object[]> commentList = commentRepository.findAllByOrderByCoDesc();

		// 記事情報をViewへ渡す
		model.addAttribute("posting", commentList);
		model.addAttribute("sortNumber", 4);
		return "index/index";
	}

	// コメントが少ない順検索
	@RequestMapping(path = "/sns/commentLessSort")
	public String commentLessSort(Model model) {

//							List<Object[]> commentList = commentRepository.findByPostingIdAndCountpOrderByCountpDesc();
		List<Object[]> commentList = commentRepository.findAllByOrderByCoDesc();

		// 記事情報をViewへ渡す
		model.addAttribute("posting", commentList);
		model.addAttribute("sortNumber", 5);
		return "index/index";
	}

}
