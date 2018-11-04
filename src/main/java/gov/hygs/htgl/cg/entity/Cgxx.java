package gov.hygs.htgl.cg.entity;

import java.util.Date;
import java.util.List;


public class Cgxx {
	private Integer id;
	private String kh;
	private Date xdrq;
	private Date jhrq;
	private String dddh;
	private String lx;
	private List<CgxxMx> cgxxMxs;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getKh() {
		return kh;
	}
	public void setKh(String kh) {
		this.kh = kh;
	}

	public Date getXdrq() {
		return xdrq;
	}
	public void setXdrq(Date xdrq) {
		this.xdrq = xdrq;
	}
	public String getDddh() {
		return dddh;
	}
	public void setDddh(String dddh) {
		this.dddh = dddh;
	}
	public String getLx() {
		return lx;
	}
	public void setLx(String lx) {
		this.lx = lx;
	}
	public List<CgxxMx> getCgxxMxs() {
		return cgxxMxs;
	}
	public void setCgxxMxs(List<CgxxMx> cgxxMxs) {
		this.cgxxMxs = cgxxMxs;
	}
	public Date getJhrq() {
		return jhrq;
	}
	public void setJhrq(Date jhrq) {
		this.jhrq = jhrq;
	}
	
}
