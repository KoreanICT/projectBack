package kr.co.ictedu.projectBack.vo;

import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Alias("ofvo")
@Setter
@Getter
public class OrderFormVO {

	private int ofnum;
	private String oname;
	private String oaddr;
	private String ophone;
	private String ofdate;
	private int membermnum;
	private String oimg;
	private String ofcompany;
	// 이미지 파일 저장
	private MultipartFile ofile;
	private List<OrderItemVO> oList;
}
