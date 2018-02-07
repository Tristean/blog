package com.blog.util;

import java.util.List;

public class Page<T> {
	List<T>pageData;
	public List<T> getPageData() {
		return pageData;
	}
	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}
}
