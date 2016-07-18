package com.hw.common.db;

public class BaseEntity {
	private Integer primaryKeyId;// 自增id
	private Long createTableDate;// 创建日期
	private Long modifyTableDate;// 修改日期
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getPrimaryKeyId() {
		return primaryKeyId;
	}

	public void setPrimaryKeyId(Integer primaryKeyId) {
		this.primaryKeyId = primaryKeyId;
	}

	public Long getCreateTableDate() {
		return createTableDate;
	}

	public void setCreateTableDate(Long createTableDate) {
		this.createTableDate = createTableDate;
	}

	public Long getModifyTableDate() {
		return modifyTableDate;
	}

	public void setModifyTableDate(Long modifyTableDate) {
		this.modifyTableDate = modifyTableDate;
	}
}
