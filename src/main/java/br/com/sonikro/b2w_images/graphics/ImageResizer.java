package br.com.sonikro.b2w_images.graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import br.com.sonikro.b2w_images.model.ImageSize;

public class ImageResizer {
	private BufferedImage originalImage;
	
	public ImageResizer(BufferedImage image)
	{
		this.originalImage = image;
	}
	
	public BufferedImage resize(ImageSize size)
	{
		
        BufferedImage resizedImage = 
        		new BufferedImage(size.getWidth(),size.getHeigth(), originalImage.getType());
        
        Graphics2D g2d = resizedImage.createGraphics();

        g2d.drawImage(originalImage, 0, 0, size.getWidth(), size.getHeigth(), null);
        g2d.dispose();
 
        return resizedImage;
	}
	
	public List<BufferedImage> resize(List<ImageSize> sizes)
	{
		ArrayList<BufferedImage> resizedImages = new ArrayList<BufferedImage>();
		
		for (ImageSize size : sizes) {
			BufferedImage resizedImage = resize(size);
			resizedImages.add(resizedImage);
		}
		
		return resizedImages;
		
	}
}
