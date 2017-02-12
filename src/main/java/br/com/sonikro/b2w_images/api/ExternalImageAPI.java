package br.com.sonikro.b2w_images.api;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.jsonp.JsonProcessingFeature;

import br.com.sonikro.b2w_images.model.Image;
import br.com.sonikro.b2w_images.model.ImageList;

public class ExternalImageAPI {
	private final static String TARGET_WS_URL = "http://54.152.221.29/";
	private final static String TARGET_WS_RESOURCE = "images.json";
	
	private Client client;
	
	public ExternalImageAPI(Client client)
	{
		this.client = client;
	}
	
	public List<Image> getImageList()
	{
		WebTarget webTarget = client.register(JsonProcessingFeature.class).target(TARGET_WS_URL);
		ImageList imageList = webTarget.path(TARGET_WS_RESOURCE)
		    .request(MediaType.APPLICATION_JSON_TYPE).get(ImageList.class);
		return imageList.getImages();
	}
}
