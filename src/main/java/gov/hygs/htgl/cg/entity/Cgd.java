package gov.hygs.htgl.cg.entity;

import java.util.Date;
import java.util.List;


public class Cgd {
	private Integer id;
	private String cgdh;
	private Date jhrq;
	private String kh;
	private String lx;
	private List<CgdMx> chdxxMxs;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCgdh() {
		return cgdh;
	}
	public void setCgdh(String cgdh) {
		this.cgdh = cgdh;
	}
	public Date getJhrq() {
		return jhrq;
	}
	public void setJhrq(Date jhrq) {
		this.jhrq = jhrq;
	}


	public List<CgdMx> getChdxxMxs() {
		return chdxxMxs;
	}
	public void setChdxxMxs(List<CgdMx> chdxxMxs) {
		this.chdxxMxs = chdxxMxs;
	}
	public String getKh() {
		return kh;
	}
	public void setKh(String kh) {
		this.kh = kh;
	}
	public String getLx() {
		return lx;
	}
	public void setLx(String lx) {
		this.lx = lx;
	}
	
}
