package br.com.sonikro.b2w_images.graphics;

import javax.ws.rs.client.Client;

import br.com.sonikro.b2w_images.api.cloudinary.CloudinaryAPI;
import br.com.sonikro.b2w_images.api.imgurl.ImgurlAPI;

public class ImageUploaderFactory {
	public static IImageUploader factory(ImageUploaderType type, Client client) throws Exception
	{
		switch(type)
		{
			case CLOUDINARY:
				return new CloudinaryAPI();
			case IMGURL:
				return new ImgurlAPI(client);
			default:
				throw new Exception("Invalid ImageUploaderType "+type);
		}
	
	}
}
