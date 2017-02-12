package br.com.sonikro.b2w_images.api.imgurl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.jsonp.JsonProcessingFeature;

import br.com.sonikro.b2w_images.graphics.IImageUploader;
import br.com.sonikro.b2w_images.model.ImageList;

public class ImgurlAPI implements IImageUploader{
	//private static final String CLIENT_ID = "5b0102c6620b03a";
	private static final String CLIENT_ID = "a5a7c7f358bc446";
	private static final String TARGET_WS_URL = "https://api.imgur.com";
	private static final String TARGET_WS_RESOURCE = "/3/image";
	private Client client;
	
	public ImgurlAPI(Client client)
	{
		this.client = client;
	}
	
	@Override
	public String uploadImage(BufferedImage image, String extension, String filename) throws Exception
	{
	    ImgurlImagePost postBody = new ImgurlImagePost();
	    
	    postBody.setImage(encodeBase64Image(image, extension));
	    postBody.setName(filename);
	    postBody.setType("base64");
	    
	    WebTarget webTarget = client.register(JsonProcessingFeature.class).target(TARGET_WS_URL).path(TARGET_WS_RESOURCE);
	    
	    
		Response response = webTarget
		    .request(MediaType.APPLICATION_JSON_TYPE).
		    header(HttpHeaders.AUTHORIZATION, "Client-ID "+ CLIENT_ID).
		    post(Entity.json(postBody));
		
		if(response.getStatus() > 299)
		{
			ImgurlErrorResponse error = response.readEntity(ImgurlErrorResponse.class);
			throw new Exception("Error uploading image to IMGULR: "+error.data.error);
		}
		else
		{
			ImgurlImageResponse imgurlResponse = response.readEntity(ImgurlImageResponse.class);
			return imgurlResponse.getData().link;
		}
		
	}


	private String encodeBase64Image(BufferedImage image, String extension) throws IOException {
		ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
	    ImageIO.write(image, extension, byteArray);
	    byte[] byteImage = byteArray.toByteArray();
	    String dataImage = Base64.getEncoder().encodeToString(byteImage);
	    return dataImage;
	}
}
