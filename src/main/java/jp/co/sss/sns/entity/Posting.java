package jp.co.sss.sns.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

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
	

	@DateTimeFormat(pattern="dd/MM/yyyy")
    private LocalDateTime  insertDate;
//	@Temporal(TemporalType.TIMESTAMP)
   
	@Column
    private String contents;

    @Column
    private String title;

}
