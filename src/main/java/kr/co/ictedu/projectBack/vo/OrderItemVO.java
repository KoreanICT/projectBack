package kr.co.ictedu.projectBack.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("oivo")
@Setter
@Getter
public class OrderItemVO {

	private int oinum;
	private String oiname;
	private String oipublisher;
	private int oiprice;
	private int oisumprice;
	private int oiamount;
	private int ofnum;
	private int stocknum;
}
