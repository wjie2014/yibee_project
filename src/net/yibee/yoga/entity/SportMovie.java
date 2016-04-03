package net.yibee.yoga.entity;

/*
 * 基本功能：健身视频列表实体类
 * 创建：王杰
 */
public class SportMovie {
	private String id;// 资讯id
	private String name;// 名字
	private String updateTime;// 更新时间
	private String thumb;// 缩略图地址
	private String typeId;// 分类id
	private String typeName;// 分类名称
	private String times;// 阅读数
	private String movie_url;
	private String pageCount;

	public String getPageCount() {
		return pageCount;
	}

	public String getMovie_url() {
		return movie_url;
	}

	public void setMovie_url(String movie_url) {
		this.movie_url = movie_url;
	}

	public void setPageCount(String pageCount) {
		this.pageCount = pageCount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

}
