package jp.co.sss.sns.util;

public class Constant {

	/**
	 * 定数定義用クラス
	 *
	 * 
	 */

		/** 削除フラグの値(未削除状態) */
		public static final int		NOT_DELETED			= 0;

		/** 削除フラグの値(削除状態) */
		public static final int		DELETED				= 1;

		/** インデックス番号の初期値 */
		public static final int		DEFAULT_INDEX		= 1;

		/** 表示順の初期値(新着順) */
		public static final int		DEFAULT_SORT_TYPE	= 1;

		/** 戻るフラグの値（戻るボタン押下時） */
		public static final int 		BACK_FLG_TRUE = 1;
		
		
		/**
		 * 
		 */
		public static final String FILE_UPLOAD_PATH = "/Users/shuntoyuuga/Documents/workspace-spring-tool-suite-4-4.12.1.RELEASE/sns/src/main/resources/static/image";
		
		/** CSS保存用フォルダの名前 */
		public static final String CSS_FOLDER = "/css/";
		
		/** 画像ファイル保存用フォルダの名前 */
		public static final String IMAGE_FOLDER = "/img/";
	
}
