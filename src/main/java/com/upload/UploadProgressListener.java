package com.upload;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;
import org.springframework.stereotype.Component;

import groovy.util.logging.Commons;

/**
 * 上传监听器 Description:
 * 
 * @author:高鹤
 * @date:2017年9月22日
 */
@Component
public class UploadProgressListener implements ProgressListener {
	private HttpSession session;

	public void setSession(HttpSession session) {
		this.session = session;
		ProgressEntity status = new ProgressEntity();
		session.setAttribute("status", status);
	}

	/*
	 * pBytesRead 到目前为止读取文件的比特数 pContentLength 文件总大小 pItems 目前正在读取第几个文件
	 */
	@Override
	public void update(long pBytesRead, long pContentLength, int pItems) {
		System.out.println(pBytesRead+"----"+pContentLength+"----"+pItems);
		ProgressEntity status = (ProgressEntity) session.getAttribute("status");
		status.setpBytesRead(pBytesRead);
		status.setpContentLength(pContentLength);
		status.setpItems(pItems);
		System.out.println(status);
	}
}
