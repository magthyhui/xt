package gov.hygs.htgl.xt.entity;

import java.math.BigDecimal;


public class Kcxx {
	private Integer id;
	private String clMc;
	private String clBh;
	private String ghs;
	private Double dqkc;
	private Double zkc;
	private String bz;
	private Double clmd;
	private Double hd;
	private Double lk;
	private Double bj;
	private Double 	ms; 
	private Double dgjz; 
	private Double dgcpmj; 
	private Double cldj;
	private Double sh;
	//
	private Double dgmz;
	private Double dgfl;// 
	private Double cllyl;// 
	private Double dj;  //
	
	public Double getDgfl() {
		return dgfl;
	}
	public void setDgfl() {
		this.dgfl = dgmz-dgjz;
	}
	public Double getCllyl() {
		return cllyl;
	}
	public void setCllyl() {
		if(dgmz!=0){
			BigDecimal b = new BigDecimal(dgjz/dgmz);
			this.cllyl = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		}
	}
	public Double getDj() {
		return dj;
	}
	public void setDj() {
		BigDecimal b = new BigDecimal(dgmz*cldj/1000);
		this.dj = b.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	public Double getDgmz() {
		return dgmz;
	}
	public void setDgmz() {
		if(ms!=0){
			BigDecimal b = new BigDecimal(clmd*hd*lk*bj/1000/ms);
			this.dgmz = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		}
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getClMc() {
		return clMc;
	}
	public void setClMc(String clMc) {
		this.clMc = clMc;
	}
	public String getClBh() {
		return clBh;
	}
	public void setClBh(String clBh) {
		this.clBh = clBh;
	}
	public String getGhs() {
		return ghs;
	}
	public void setGhs(String ghs) {
		this.ghs = ghs;
	}
	public Double getDqkc() {
		return dqkc;
	}
	public void setDqkc(Double dqkc) {
		this.dqkc = dqkc;
	}
	public Double getZkc() {
		return zkc;
	}
	public void setZkc(Double zkc) {
		this.zkc = zkc;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public Double getClmd() {
		return clmd;
	}
	public void setClmd(Double clmd) {
		this.clmd = clmd;
	}
	public Double getHd() {
		return hd;
	}
	public void setHd(Double hd) {
		this.hd = hd;
	}
	public Double getLk() {
		return lk;
	}
	public void setLk(Double lk) {
		this.lk = lk;
	}
	public Double getBj() {
		return bj;
	}
	public void setBj(Double bj) {
		this.bj = bj;
	}
	public Double getMs() {
		return ms;
	}
	public void setMs(Double ms) {
		this.ms = ms;
	}
	public Double getDgjz() {
		return dgjz;
	}
	public void setDgjz(Double dgjz) {
		this.dgjz = dgjz;
	}
	public Double getDgcpmj() {
		return dgcpmj;
	}
	public void setDgcpmj(Double dgcpmj) {
		this.dgcpmj = dgcpmj;
	}
	public Double getCldj() {
		return cldj;
	}
	public void setCldj(Double cldj) {
		this.cldj = cldj;
	}
	public Double getSh() {
		return sh;
	}
	public void setSh(Double sh) {
		this.sh = sh;
	}

	
}
