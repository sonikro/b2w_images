package br.com.sonikro.test;

import java.awt.image.BufferedImage;

import java.util.ArrayList;

import java.util.List;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;


import br.com.sonikro.b2w_images.api.ExternalImageAPI;
import br.com.sonikro.b2w_images.dao.ImageDAO;
import br.com.sonikro.b2w_images.database.DatastoreManager;
import br.com.sonikro.b2w_images.graphics.IImageUploader;
import br.com.sonikro.b2w_images.graphics.ImageResizer;
import br.com.sonikro.b2w_images.graphics.ImageUploaderFactory;
import br.com.sonikro.b2w_images.graphics.ImageUploaderType;
import br.com.sonikro.b2w_images.model.Image;
import br.com.sonikro.b2w_images.model.ImageSize;

public class mainTest {
	public static void main(String[] args) throws Exception {
		
		//Inject dependency to ImageDAO
		ImageDAO dao = new ImageDAO(DatastoreManager.getMongoDatastore());
		dao.clear(); //Clear current images
		
		//Prepare ExternaImageService and Inject Dependency
		Client client = ClientBuilder.newClient();
		ExternalImageAPI imageService = new ExternalImageAPI(client);
		
		//Download all images from ImageService (Original 10 images)
		List<Image> imageList = imageService.getImageList();
		
		//Prepare list of Sizes
		ArrayList<ImageSize> imageSizeList = new ArrayList<ImageSize>();
		imageSizeList.add(new ImageSize(320, 240, "Small"));
		imageSizeList.add(new ImageSize(384, 288, "Medium"));
		imageSizeList.add(new ImageSize(640, 480, "Large"));
		
		//Prepare ImageUploader Api for uploading the resized images
		IImageUploader imageUploader = ImageUploaderFactory.factory(ImageUploaderType.CLOUDINARY, client);
		//For each image, resize it according to the specified sizes
		for (Image image: imageList) {
			ImageResizer resizer = new ImageResizer(image.loadBufferedImage());
			ArrayList<Image> resizedImages = new ArrayList<Image>();
			
			for (ImageSize imageSize : imageSizeList) {
				BufferedImage resizedImage = resizer.resize(imageSize);
				
				String resizedImageName =  image.getImageFilename() + "_" + imageSize.getSizeName()+
						"." + image.getImageExtension();
				
				
				String resizedImageUrl = imageUploader.uploadImage(resizedImage, image.getImageExtension(),
						resizedImageName);
				

				resizedImages.add(new Image(resizedImageUrl, imageSize));
				
			}
			
			image.setSizes(resizedImages);
			
			dao.persist(image);
			
		}

		
		client.close();
		
	}
}
