package kr.co.ictedu.projectBack.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("pvo")
@Setter
@Getter
public class ProductVO {

	private String pstartdate;
	private String penddate;
	private String pname;
	private String pisbn;
	private int pnum;
	private int snum;
	private int ofnum;
}
