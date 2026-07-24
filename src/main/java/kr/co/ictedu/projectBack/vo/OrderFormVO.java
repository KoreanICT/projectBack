package kr.co.ictedu.projectBack.vo;

import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Alias("ofvo")
@Setterhttps://github.com/KoreanICT/projectBack/pull/21/conflict?name=src%252Fmain%252Fjava%252Fkr%252Fco%252Fictedu%252FprojectBack%252Fvo%252FOrderFormVO.java&ancestor_oid=e65b7aeb8f46769ae01d7d51aeb9df3db4f1ee28&base_oid=3b3ceb12cf534fcff4424a5c4137c5d4d975f51f&head_oid=cc5e88e5ca32a1a9805773bca6f888b6001a460d
@Getter
public class OrderFormVO {

	private int ofnum;
	private String oname;		// 대표자 이름
	private String oaddr;		// 주소
	private String ophone;		// 연락처
	private int membermnum;
	private String oimg;		// 서명 이미지
	private String ofcompany;	// 상호명
	// 이미지 파일 저장
	private MultipartFile ofile;
	private List<OrderItemVO> oList;
}
