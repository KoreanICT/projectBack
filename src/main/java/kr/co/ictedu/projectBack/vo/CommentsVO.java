package kr.co.ictedu.projectBack.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("tomi")
@Getter
@Setter
public class CommentsVO {
private int cnum;
private String cwirter;
private String ccontent;
private String cregdate;

}
