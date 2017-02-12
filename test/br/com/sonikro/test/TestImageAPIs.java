package br.com.sonikro.test;

import static org.junit.Assert.*;


import org.junit.Test;

import br.com.sonikro.b2w_images.api.imgurl.ImgurlAPI;
import br.com.sonikro.b2w_images.graphics.IImageUploader;
import br.com.sonikro.b2w_images.graphics.ImageUploaderFactory;
import br.com.sonikro.b2w_images.graphics.ImageUploaderType;
import br.com.sonikro.b2w_images.model.Image;


import javax.ws.rs.client.ClientBuilder;
public class TestImageAPIs {
	public static final String SAMPLE_IMAGE = "http://54.152.221.29/images/b737_4.jpg";
	@Test
	public void TestImgulrImageUpload() throws Exception{
		assertNotNull(uploadTestImageUsing(ImageUploaderType.IMGURL));
	}
	
	@Test
	public void TestCloudinaryImageUpload() throws Exception{
		assertNotNull(uploadTestImageUsing(ImageUploaderType.CLOUDINARY));
	}
	
	public String uploadTestImageUsing(ImageUploaderType type) throws Exception
	{
		IImageUploader uploader = ImageUploaderFactory.factory(type, ClientBuilder.newClient());
		Image image = new Image(SAMPLE_IMAGE);
		return uploader.uploadImage(image.loadBufferedImage(), image.getImageExtension(), image.getImageFilename());
	}

}
