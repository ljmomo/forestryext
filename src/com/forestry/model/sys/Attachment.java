package com.forestry.model.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.forestry.model.sys.param.AttachmentParameter;
import com.google.common.base.Objects;

/**
 * @author 杨添
 * @系统不断升级，获取新版本和技术支持地址：http://item.taobao.com/item.htm?spm=2013.1.20141001.2.YitFpw&id=40481412685&scm=1007.10115.4482.i42543888642&pvid=e2916971-1ade-45ae-98a8-754306378b06
 * @本淘宝店也有智能交通系统、OA系统、LBS系统、门户系统和JAVA企业前后台框架等产品：http://shop111863449.taobao.com/
 */
@Entity
@Table(name = "attachment")
@Cache(region = "all", usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonIgnoreProperties(value = { "maxResults", "firstResult", "topCount", "sortColumns", "cmd", "queryDynamicConditions", "sortedConditions", "dynamicProperties", "success", "message", "id", "forestryType",
		"forestrytypeId", "sortColumnsString" })
public class Attachment extends AttachmentParameter {

	private static final long serialVersionUID = 7296680169194828397L;
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id; // ID
	@Column(name = "file_name", length = 100)
	private String fileName; // 附件名
	@Column(name = "file_path", length = 1000)
	private String filePath; // 附件路径
	@ManyToOne
	@JoinColumn(name = "forestrytype_id")
	private ForestryType forestryType; // 外键关联树木种类信息主表
	@Column(name = "forestrytype_id", insertable = false, updatable = false)
	private Long forestrytypeId; // 拼凑SQL，不会持久化到数据库

	public Attachment() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public ForestryType getForestryType() {
		return forestryType;
	}

	public void setForestryType(ForestryType forestryType) {
		this.forestryType = forestryType;
	}

	public Long getForestrytypeId() {
		return forestrytypeId;
	}

	public void setForestrytypeId(Long forestrytypeId) {
		this.forestrytypeId = forestrytypeId;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Attachment other = (Attachment) obj;
		return Objects.equal(this.id, other.id) && Objects.equal(this.fileName, other.fileName) && Objects.equal(this.filePath, other.filePath) && Objects.equal(this.forestryType, other.forestryType);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.id, this.fileName, this.filePath, this.forestryType);
	}

}
