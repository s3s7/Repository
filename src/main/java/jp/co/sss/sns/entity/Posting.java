package jp.co.sss.sns.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sns_posting")
public class Posting {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column
//    private LocalDateTime  insertDate;
    private Date  insertDate;
    @Column
    private String contents;

    @Column
    private String title;

}
