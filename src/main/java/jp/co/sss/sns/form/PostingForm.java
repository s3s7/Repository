package jp.co.sss.sns.form;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PostingForm {

//
//    private long id;
//    private Date  insertDate;
//    
	   @NotEmpty(message = "投稿内容を入力してください")
    private String contents;
    
    @NotEmpty(message = "タイトルを入力してください")
	@Size(min = 0,max = 20)
    private String title;
    

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
}
