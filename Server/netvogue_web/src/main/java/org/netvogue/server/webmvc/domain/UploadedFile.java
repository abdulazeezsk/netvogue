package org.netvogue.server.webmvc.domain;

import java.io.Serializable;

public class UploadedFile implements Serializable{
	private static final long serialVersionUID = -38331060124340967L;
	private String name;
	private Integer size;
	private String url;
	private String thumbnail_url;
	private String uniqueid;

	public UploadedFile() {
		super();
	}

	public UploadedFile(String name, Integer size, String url, String uniqueid) {
		super();
		this.name = name;
		this.size = size;
		this.url = url;
		this.uniqueid = uniqueid;
	}

	public UploadedFile(String name, Integer size, String url,
			String thumbnail_url, String delete_url, String delete_type) {
		super();
		this.name = name;
		this.size = size;
		this.url = url;
		this.thumbnail_url = thumbnail_url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getThumbnail_url() {
		return thumbnail_url;
	}

	public void setThumbnail_url(String thumbnail_url) {
		this.thumbnail_url = thumbnail_url;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
