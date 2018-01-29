package gov.hygs.htgl.entity;

import java.io.Serializable;

/**
 * Model class of 题目选择项.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
public class Tkxzx implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** ID_. */
	private String id;

	/** 题目ID. */
	private String tkId;

	/** 选项 ABCD. */
	private String xzKey;

	/** 选项内容. */
	private String content;

	/**
	 * Constructor.
	 */
	public Tkxzx() {
	}

	/**
	 * Set the ID_.
	 * 
	 * @param id
	 *            ID_
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Get the ID_.
	 * 
	 * @return ID_
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Set the 题目ID.
	 * 
	 * @param tkId
	 *            题目ID
	 */
	public void setTkId(String tkId) {
		this.tkId = tkId;
	}

	/**
	 * Get the 题目ID.
	 * 
	 * @return 题目ID
	 */
	public String getTkId() {
		return this.tkId;
	}

	/**
	 * Set the 选项 ABCD.
	 * 
	 * @param xzKey
	 *            选项 ABCD
	 */
	public void setXzKey(String xzKey) {
		this.xzKey = xzKey;
	}

	/**
	 * Get the 选项 ABCD.
	 * 
	 * @return 选项 ABCD
	 */
	public String getXzKey() {
		return this.xzKey;
	}

	/**
	 * Set the 选项内容.
	 * 
	 * @param content
	 *            选项内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Get the 选项内容.
	 * 
	 * @return 选项内容
	 */
	public String getContent() {
		return this.content;
	}


}
