package gov.hygs.htgl.xt.entity;

import java.util.Date;
import java.util.List;


public class Ddxx {
	private Integer id;
	private String kh;
	private String ddBh;
	private Date fkdjsj;
	private Date khjq;
	private Date hyjq;
	private List<Ddcpxx> cpxxs;
	private String lx ;
	
	
	
	public String getLx() {
		return lx;
	}
	public void setLx(String lx) {
		this.lx = lx;
	}
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
	public String getDdBh() {
		return ddBh;
	}
	public void setDdBh(String ddBh) {
		this.ddBh = ddBh;
	}
	public Date getFkdjsj() {
		return fkdjsj;
	}
	public void setFkdjsj(Date fkdjsj) {
		this.fkdjsj = fkdjsj;
	}
	public Date getKhjq() {
		return khjq;
	}
	public void setKhjq(Date khjq) {
		this.khjq = khjq;
	}
	public Date getHyjq() {
		return hyjq;
	}
	public void setHyjq(Date hyjq) {
		this.hyjq = hyjq;
	}
	public List<Ddcpxx> getCpxxs() {
		return cpxxs;
	}
	public void setCpxxs(List<Ddcpxx> cpxxs) {
		this.cpxxs = cpxxs;
	}
	
	
	
}
