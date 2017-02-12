package br.com.sonikro.b2w_images.api.cloudinary;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;

import javax.imageio.ImageIO;

import br.com.sonikro.b2w_images.graphics.IImageUploader;
import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;

public class CloudinaryAPI implements IImageUploader{
	private Cloudinary cloudinary;
	
	public CloudinaryAPI() {
		cloudinary = new Cloudinary(ObjectUtils.asMap(
				  "cloud_name", "dfv1itbth",
				  "api_key", "729948517522752",
				  "api_secret", "A1i01f9ovUWmXt1zq0ZBJA_bjEo"));
	}
	
	@Override
	public String uploadImage(BufferedImage image, String extension, String filename) throws Exception {
		File imageFile = new File(filename);
		ImageIO.write(image, extension, imageFile);
		Map uploadResult = cloudinary.uploader().upload(imageFile, ObjectUtils.emptyMap());
		imageFile.delete(); //Remove temp file from server
		return (String) uploadResult.get("url");
	}

}
