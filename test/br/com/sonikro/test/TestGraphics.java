package br.com.sonikro.test;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Test;

import br.com.sonikro.b2w_images.graphics.ImageResizer;
import br.com.sonikro.b2w_images.model.Image;
import br.com.sonikro.b2w_images.model.ImageSize;

public class TestGraphics {
	private static final int RESIZE_HEIGTH = 240;
	private static final int RESIZE_WIDTH = 320;
	@Test
	public void testImageResizer() throws MalformedURLException, IOException {
		Image image = new Image(TestImageAPIs.SAMPLE_IMAGE);
		ImageResizer resizer = new ImageResizer(image.loadBufferedImage());
		
		BufferedImage resizedImage = resizer.resize(new ImageSize(RESIZE_WIDTH, RESIZE_HEIGTH));
		
		assertEquals(resizedImage.getWidth(), RESIZE_WIDTH );
		assertEquals(resizedImage.getHeight(), RESIZE_HEIGTH);
	}

}
