package org.netvogue.server.webmvc.domain;

import java.util.List;

public class UploadedFileResponse {
	private boolean status = false;
    private String  error = null;
    private List<UploadedFile>  filesuploaded;
	
    public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public List<UploadedFile> getFilesuploaded() {
		return filesuploaded;
	}
	public void setFilesuploaded(List<UploadedFile> filesuploaded) {
		this.filesuploaded = filesuploaded;
	}
    
}
