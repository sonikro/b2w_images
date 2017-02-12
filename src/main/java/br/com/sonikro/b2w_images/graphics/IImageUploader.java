package br.com.sonikro.b2w_images.graphics;

import java.awt.image.BufferedImage;

public interface IImageUploader {
	
	public String uploadImage(BufferedImage image, String extension, String filename) throws Exception;
}
