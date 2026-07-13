package kr.co.ictedu.projectBack.vo;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Alias("comvo")
@Getter
@Setter
public class CommunityVO {
	private int num;
	private String title;
	private String writer;
	private String content;
	private String imgn;
	private int hit;
	private String reip;
	private String cdate;
	// 이미지 저장용
	private MultipartFile mfile;
}
