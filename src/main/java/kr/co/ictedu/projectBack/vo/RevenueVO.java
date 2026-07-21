package kr.co.ictedu.projectBack.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("rvo")
@Setter
@Getter
public class RevenueVO {

	private String rmonth;
	private int rtotalqty;
	private int rtotalsales;
	private int rtotalcost;
	private int rtotalmargin;
	private int pnum;
}
