package com.forestry.model.sys;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.forestry.model.sys.param.ForestryTypeParameter;
import com.google.common.base.Objects;

/**
 * @author 杨添
 * @系统不断升级，获取新版本和技术支持地址：http://item.taobao.com/item.htm?spm=2013.1.20141001.2.YitFpw&id=40481412685&scm=1007.10115.4482.i42543888642&pvid=e2916971-1ade-45ae-98a8-754306378b06
 * @本淘宝店也有智能交通系统、OA系统、LBS系统、门户系统和JAVA企业前后台框架等产品：http://shop111863449.taobao.com/
 */
@Entity
@Table(name = "forestry_type")
@Cache(region = "all", usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonIgnoreProperties(value = { "maxResults", "firstResult", "topCount", "sortColumns", "cmd", "queryDynamicConditions", "sortedConditions", "dynamicProperties", "success", "message", "sortColumnsString", "forestrys",
		"attachments" })
public class ForestryType extends ForestryTypeParameter {

	private static final long serialVersionUID = -2584402708895690089L;
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id; // ID
	@Column(name = "name", length = 200, nullable = false)
	private String name; // 名称
	@Column(name = "description", length = 2000, nullable = true)
	private String description; // 描述
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "forestryType", fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<Forestry> forestrys; // 树木
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "forestryType", fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<Attachment> attachments; // 附件

	public ForestryType() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Forestry> getForestrys() {
		return forestrys;
	}

	public void setForestrys(Set<Forestry> forestrys) {
		this.forestrys = forestrys;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ForestryType other = (ForestryType) obj;
		return Objects.equal(this.id, other.id) && Objects.equal(this.name, other.name) && Objects.equal(this.description, other.description);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.id, this.name, this.description);
	}

}
