package gov.hygs.htgl.order.entity;

import java.util.Date;
import java.util.List;


public class Order {
	private Integer id;
	private String kh;
	private String ddh;
	private Date xdrq;
	private List<OrderDetails> ddxxMxs;
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
	public String getDdh() {
		return ddh;
	}
	public void setDdh(String ddh) {
		this.ddh = ddh;
	}
	public Date getXdrq() {
		return xdrq;
	}
	public void setXdrq(Date xdrq) {
		this.xdrq = xdrq;
	}
	public List<OrderDetails> getDdxxMxs() {
		return ddxxMxs;
	}
	public void setDdxxMxs(List<OrderDetails> ddxxMxs) {
		this.ddxxMxs = ddxxMxs;
	}
	
	
}
