package jp.co.sss.sns.controller.comment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sss.sns.entity.Comment;
import jp.co.sss.sns.form.CommentForm;
import jp.co.sss.sns.repository.CommentRepository;

@Controller
public class CommentRegistController {
	// DI
	@Autowired
	CommentRepository commentRepository;

	// コメントページ遷移処理
	@RequestMapping("/snssns/commentPage")
	public String commentPage(Model model, @ModelAttribute CommentForm form) {
		return "comment/comment_page";
	}

	// コメント機能
	@RequestMapping("/snssns/commentInput")
	public String commentInput(Model model, @Valid @ModelAttribute CommentForm form, BindingResult result) {
		if (result.hasErrors()) {
			// エラーが発生した場合
			List<ObjectError> errorList = result.getAllErrors();
			model.addAttribute("errorList", errorList);
			return commentPage(model, form);
		}
		String commentContent = form.getCommentContents();
		if (commentContent != null) {
			model.addAttribute("contents", commentContent);
			// 条件を満たした場合コメント内容確認画面へ
			return "comment/comment_check";
		} else {
			return commentPage(model, form);
		}
	}

	// コメント投稿ボタン
	@RequestMapping("/snssns/comment/complete")
	public String commentComplete(Model model, @ModelAttribute CommentForm form) {
		// コメント内容情報の生成
		Comment comment = new Comment();

		// 入力値をリポジトリ保存
		comment.setCommentContents(form.getCommentContents());
		// 投稿時間の取得
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm");
		Date date = new Date();
		// SimpleDateFormatクラスのparseメソッドを使うにはthrows句を使ってParseExceptionなどに例外を投げるか、try-catch構文で例外処理を行う必要
		try {
			date = sdf.parse(DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		comment.setInsertDate(date);
		commentRepository.save(comment);
		return "redirect:/snssns/commentComplete";
	}

	// コメント完了画面表示
	@RequestMapping("/snssns/commentComplete")
	public String commentComplete() {
		return "comment/comment_complete";
	}

	// コメント数カウント処理
	@RequestMapping("/snssns/commentCount")
	public String commentCount(int commentId) {
		Comment commentContents = new Comment();
		commentContents.setCommentId(commentId);

	
		try (Scanner sc = new Scanner(System.in)) {
			int N;
			int M;
			String A;
			int i; // iは (1 ≦ i ≦ N)
			int G=0;
			{
				N = sc.nextInt();
				M = sc.nextInt();
				i = N - 1;
				if (1 <= i && i <= N - 1) {
//			最後の行読み取り
					String L = sc.nextLine();
					G = N + M;
//					A_iの最後まで読み取りのため繰り返し
					for (; i <= N - 1; i++) {
						A = sc.next();
						G = G +Integer.parseInt(A);

					}
					int A_i = N * i;
					if (1 <= N && N <= 1000 && 1 <= M && M <= 100 && 1 <= A_i && A_i <= 200)
					{
//            表示処理
						System.out.println(G+"\n");
					}
				}
			}
//            System.out.println("hello = " + token1 + " , world = " + token2);
		}
//ここまで
		try (Scanner sc = new Scanner(System.in)) {
		String S;
		String KL = "+";
		String KR= "+";
		int SK;
//		入力された内容
		S=sc.nextLine();
		SK = S.length();
//		K= String.valueOf(SK * "+") ;
// 		int KL2= Integer.parseInt(KL);
//		for(int i =1; i<=SK+2;i++) {
// 		System.out.println(KL2 * SK);
// 		String str = StringUtils.repeat(S, SK);
		
//		K= String.valueOf(SK * "+") ;
		int KL2= Integer.parseInt(KL);
//		for(int i =1; i<=SK+2;i++) {
		System.out.println(KL2 * SK);
//		}
		System.out.println("\n");
		System.out.println(KL+S+KR+"\n");
//		for(int i =1; i<=SK+2;i++) {
		System.out.println(KL2 * SK);
//			}
		
		}//tryの
//		B109
		try (Scanner sc = new Scanner(System.in)) {
			 int N;
				int M;
				int H;
				int W;
				int P;
				int Q;
				String A;
				int i; // iは (1 ≦ i ≦ N)
				String p_i;
				List <String> pl = new ArrayList<>();
				List <String> ql = new ArrayList<>();
				String q_i;
				int a_i;
				int b_i;
				
//					入力内容読み取り
					N = sc.nextInt();
					H = sc.nextInt();
					W = sc.nextInt();
					P = sc.nextInt();
					Q = sc.nextInt();
					
					i = 1;
					if (1 <= i && i <= N ) {
//				最後の行読み取り
						// String L = sc.nextLine();
//						A_iの最後まで読み取りのため繰り返し
						for (; i <= N ; i++) {
//							A = sc.next();
							p_i = sc.next() + "";
							pl.add(p_i);
							q_i = sc.next();
							ql.add(q_i);
						}
						int A_i = N * i;
//						if (1 <= H && W <= 100&& 1<=N && N< H*W && 0<= P && P<H && 0<= Q && Q < W && 0<= p_i && p_i< H && 0<= q_i && q_i < W )
//						{
//		        表示処理
						for (int ii = 0; i <= N ; i++) {
							System.out.println(pl.get(ii) + ql.get(ii));
						}
							System.out.println("\n");
//						}
					}
		    
		}//try
		
//	B73
	try (Scanner sc = new Scanner(System.in)) {
		String A; 
		String N;
		String M;
		String Q;
			int i; // iは (1 ≦ i ≦ N)
			String A_i;
			List <String> Al = new ArrayList<>();
			List <String> Sl = new ArrayList<>();
			List <String> El = new ArrayList<>();
			String S_i = "0";
			String E_i;
			Boolean b;
			Boolean AGAndM;
			String AG ="0";
			int AG2;
			String KukanFirst;
			String KukanLast;
			String str;
//			AlTotalに入れ直す？ いやその後の
			String AlTotal ="0";
			List <String> SlTotal = new ArrayList<>();
//				入力内容読み取り
				N = sc.next();
				Q = sc.next();
				M= sc.next();
				
				i = 1;
				
				if (1 <= i && i <= Integer.parseInt(N) ) {
//			最後の行読み取り
					// String L = sc.nextLine();
//					A_iの最後まで読み取りのため繰り返し
					for (; i <= Integer.parseInt(N); i++) {
//						A = sc.next();
						A_i = sc.next() + "";
						Al.add(A_i);
//						平均用に合計を出す
						AG = AG + S_i;
					}
//					S_iの最後まで読み取りのため繰り返し
					for (; i <= Integer.parseInt(Q); i++) {
//						A = sc.next();
						S_i = sc.next() + "";
						b = Integer.parseInt(Q) < Integer.parseInt(S_i);
						Sl.add(S_i);

						E_i = sc.next() + "";
						b = Integer.parseInt(Q) < Integer.parseInt(E_i);
						El.add(E_i);
//						平均用に合計を出す
//						SG = SG + S_i;
//						EG = EG + E_i;
					}
					AG2 = Integer.parseInt(AG) / Integer.parseInt(M);
//					このあとi番目のS_iとE_iのlistがMを上回っていたら足さない。下回っていたら、上回るまでループで足す
					
					AGAndM = AG2 <= Integer.parseInt(M);
//						 合計以下の場合
						if(AGAndM) {
					for(int i2=0; i2 <= Integer.parseInt(Q); i2++)
					{
						KukanFirst = Al.get(Integer.parseInt(Sl.get(i2)));
						KukanLast = Al.get(Integer.parseInt(El.get(i2)));
//						S_iとE_iの指定の範囲を足す
						for(int KukanFirstCount =Integer.parseInt(KukanFirst); Integer.parseInt(KukanFirst)   < Integer.parseInt(KukanLast) ;  KukanFirstCount++){
							
//							AlTotal = Arrays.asList(str.split(Al.get(i3) + 1));
//							Al,Elに一つずつ足していき
							AlTotal  = Al.get(KukanFirstCount - 1) + 1;
							Al.set(KukanFirstCount - 1,Al.get(KukanFirstCount - 1) + 1);
							El.set(KukanFirstCount - 1,Al.get(KukanFirstCount - 1) + 1);
//							平均がMを上回っていたら抜ける
							if (Integer.parseInt(AlTotal) / Integer.parseInt(M) >= Integer.parseInt(M))
							{
								break;
								
							}
						}
						}
					}
					}
//					もしQより大きければ
			int QAndAve =	Integer.parseInt(AlTotal) / Integer.parseInt(M);
				b = Integer.parseInt(Q) > QAndAve;
					if(b) {
//					int A_i = N * i;
//					if (1 <= H && W <= 100&& 1<=N && N< H*W && 0<= P && P<H && 0<= Q && Q < W && 0<= p_i && p_i< H && 0<= q_i && q_i < W )
//					{
//	        表示処理
					for (int ii = 0; ii < Integer.parseInt(N) ; ii++) {
						System.out.print(Al.get(ii) + El.get(ii));
					}
					
					}
//					もしQより小さければ
					else {
						for (int ii = 0; ii < Integer.parseInt(N)  ; ii++) {
							System.out.print(Al.get(ii));
						}
					}
					
						System.out.print("\n");
//					}
				}
	    
	// try
	
	try (Scanner sc = new Scanner(System.in)) {
		String A;
		String N;
		String M;
		String X;
		int i; // iは (1 ≦ i ≦ N)
		String A_i;
		List<String> Al = new ArrayList<>();
		String[] A_i_j;
		List<String> Sl = new ArrayList<>();
		List<String> El = new ArrayList<>();
		String R_X = "0";
		String S_X;
		Boolean b;
		Boolean AGAndM;
		String AG = "0";
		int AG2;
		String KukanFirst;
		String KukanLast;
		String str;
//			AlTotalに入れ直す？ いやその後の
		String AlTotal = "0";
		List<String> SlTotal = new ArrayList<>();
//				入力内容読み取り
		N = sc.next();
		M = sc.next();

		i = 1;

//				if (1 <= i && i <= Integer.parseInt(N) ) {

//					A_iの最後まで読み取りのため繰り返し
		for (int ii = 0; ii <
				Integer.parseInt(N); ii++) {
			//	A = sc.next();
			A_i = sc.next() + "";
			Al.add(A_i);
			// Mがもし2以上であればM回繰り返す
			if (Integer.parseInt(M) > 2) {
				for (int iii = 0; iii < Integer.parseInt(M); iii++) {
					A_i = sc.next() + "";
					Al.add(A_i);
				}
			}
			
//						平均用に合計を出す
			AG = AG + R_X;
		}
		X = sc.next();

//					R_Xの最後まで読み取りのため繰り返し
		for (int iq = 0; iq < Integer.parseInt(X); iq++) {
//						A = sc.next();
			R_X = sc.next() + "";
			b = Integer.parseInt(X) < Integer.parseInt(R_X);
			Sl.add(R_X);

			S_X = sc.next() + "";
			b = Integer.parseInt(X) < Integer.parseInt(S_X);
			El.add(S_X);
//						平均用に合計を出す
//						SG = SG + R_X;
//						EG = EG + S_X;
		}
//					最後の行読み取り
		String L = sc.nextLine();
		AG2 = Integer.parseInt(AG) / Integer.parseInt(M);
//					このあとi番目のR_XとS_XのlistがMを上回っていたら足さない。下回っていたら、上回るまでループで足す

		AGAndM = AG2 <= Integer.parseInt(M);
//						 合計以下の場合
		if (AGAndM) {
			for (int i2 = 0; i2 < Integer.parseInt(X); i2++) {

				KukanFirst = Al.get(Integer.parseInt(Sl.get(i2)));

				KukanLast = Al.get(Integer.parseInt(El.get(i2)) - 1);
//						R_XとS_Xの指定の範囲を足す
				for (int KukanFirstCount = Integer.parseInt(KukanFirst); Integer.parseInt(KukanFirst) < Integer
						.parseInt(KukanLast); KukanFirstCount++) {

//							AlTotal = Arrays.asList(str.split(Al.get(i3) + 1));
//							Al,Elに一つずつ足していき
					AlTotal = Al.get(KukanFirstCount - 1) + 1;
					Al.set(KukanFirstCount - 1, Al.get(KukanFirstCount - 1) + 1);
					El.set(KukanFirstCount - 1, Al.get(KukanFirstCount - 1) + 1);
//							平均がMを上回っていたら抜ける
					if (Integer.parseInt(AlTotal) / Integer.parseInt(M) >= Integer.parseInt(M)) {
						break;
					}
				}
			}
		}
//					}
//					もしXより大きければ
		System.out.print(Al.get(0) + El.get(0));
		int XAndAve = Integer.parseInt(AlTotal) / Integer.parseInt(M);
		b = Integer.parseInt(X) > XAndAve;
		if (b) {
//					int A_i = N * i;
//					if (1 <= H && W <= 100&& 1<=N && N< H*W && 0<= P && P<H && 0<= X && X < W && 0<= p_i && p_i< H && 0<= q_i && q_i < W )
//					{
//	        表示処理
			for (int ii = 0; ii < Integer.parseInt(N); ii++) {
				System.out.print(Al.get(ii) + El.get(ii));
			}

		}
//					もしXより小さければ
		else {
			for (int ii = 0; ii < Integer.parseInt(N); ii++) {
				System.out.print(Al.get(ii));
			}
		}

		System.out.print("\n");
//					}
	}
//********
	{
		 var sc = new Scanner(System.in);
	     var N = sc.nextInt();
	     var X = sc.nextInt();
	     var K = sc.nextInt();
	     
	     var Total4N = 4 * N ; 
	     var Matagu = 1;
	     var ZanK = K - Total4N;
	     var TotalCm = 0;
	     
	     var PlusCm = 0;
	     
//	     K　- 4 * N -1　このときに 3 + 4 回があった分だけ6cm　か3cm 3.equal(To) || 3+4.equal(To)  3,6増えていく 
	     var H = 4* K;
//	     K <= 4 は　最低Xcm +  ZanK / 3 　4*NはK以下で３以上は確定
	     
	    	 TotalCm = X ;
	  	 for(var i = 0; i < ZanK ;i++)
    	 {
    		 TotalCm = X + TotalCm;
    	 }
//     {
//    	 TotalCm = X + TotalCm;
//     }
//    あまり 0の時も必要？　　3のとき
     if(X > 1&&TotalCm % 3 == 1)
     {
    	PlusCm =X / 3 ;
    	PlusCm = PlusCm + 3;
     }
//     Xが3以上で、Xのあまりが0と2の時は足さない。　いらないかこの処理1以外はこれらしかないから。
     else if (X % 3 == 0 || X % 3 == 2 )
     {
    	 PlusCm =X / 3 ;
     }
     System.out.println(PlusCm);
     System.out.println();
	}
	
	
	
	return"comment/comment_complete";
}}
