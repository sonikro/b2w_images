package br.com.sonikro.b2w_images.api.imgurl;

import javax.json.JsonObject;

public class ImgurlImageResponse {
	

	private ImgurlImage data;
	private String success;
	private String status;
	
	
	public ImgurlImage getData() {
		return data;
	}
	public void setData(ImgurlImage data) {
		this.data = data;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
