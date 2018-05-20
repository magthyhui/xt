package gov.hygs.htgl.order.entity;

import java.util.Date;
import java.util.List;


public class Sell {
	private Integer id;
	private String shdh;
	private String lx;
	private String kh;
	private Date shrq;
	private List<SellDetails> chdMx;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getShdh() {
		return shdh;
	}
	public void setShdh(String shdh) {
		this.shdh = shdh;
	}
	public Date getShrq() {
		return shrq;
	}
	public void setShrq(Date shrq) {
		this.shrq = shrq;
	}
	public List<SellDetails> getChdMx() {
		return chdMx;
	}
	public void setChdMx(List<SellDetails> chdMx) {
		this.chdMx = chdMx;
	}
	public String getLx() {
		return lx;
	}
	public void setLx(String lx) {
		this.lx = lx;
	}
	
	
	
}
