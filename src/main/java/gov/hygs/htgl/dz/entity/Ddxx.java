package gov.hygs.htgl.dz.entity;

import java.util.Date;
import java.util.List;


public class Ddxx {
	private Integer id;
	private String kh;
	private String ddh;
	private Date xdrq;
	private String shdh;
	private Date shrq;
	private List<DdxxMx> ddxxMxs;
	
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
	public List<DdxxMx> getDdxxMxs() {
		return ddxxMxs;
	}
	public void setDdxxMxs(List<DdxxMx> ddxxMxs) {
		this.ddxxMxs = ddxxMxs;
	}
	public String getDdh() {
		return ddh;
	}
	public void setDdh(String ddh) {
		this.ddh = ddh;
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
	
}
