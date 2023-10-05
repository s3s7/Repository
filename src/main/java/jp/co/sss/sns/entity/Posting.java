package jp.co.sss.sns.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sns_posting")
public class Posting {

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	これをつけて解決した　.hibernate_sequence' doesn't exist
	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq__reviews_gen")
//	@SequenceGenerator(name = "seq_item_reviews_gen", sequenceName = "seq_item_reviews", allocationSize = 1)
    private int id;
    @Column
//    private LocalDateTime  insertDate;
    private Date  insertDate;
    @Column
    private String contents;

    @Column
    private String title;
    
//	@Column(insertable = false)
//    private Integer deleteFlag;
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public LocalDateTime getInsertDate() {
//		return insertDate;
//	}
//
//	public void setInsertDate(LocalDateTime insertDate) {
//		this.insertDate = insertDate;
//	}
	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	
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

//	public Integer getDeleteFlag() {
//		return deleteFlag;
//	}
//
//	public void setDeleteFlag(Integer deleteFlag) {
//		this.deleteFlag = deleteFlag;
//	}


}
