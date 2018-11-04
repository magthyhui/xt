package gov.hygs.htgl.mj.entity;

import java.util.Date;
import java.util.List;


public class Chd {
	private Integer id;
	private String shdh;
	private String lx;
	private Date shrq;
	private List<ChdMx> chdMx;
	
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
	public List<ChdMx> getChdMx() {
		return chdMx;
	}
	public void setChdMx(List<ChdMx> chdMx) {
		this.chdMx = chdMx;
	}
	public String getLx() {
		return lx;
	}
	public void setLx(String lx) {
		this.lx = lx;
	}
	
	
	
}
