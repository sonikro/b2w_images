package br.com.sonikro.b2w_images.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("images")
public class Image {
	@Id
	private String url;
	
	private ImageSize size;
	
	private List<Image> sizes;

	public Image()
	{
		
	}
	
	public Image(String url)
	{
		setUrl(url);
	}
	
	public Image(String url, ImageSize size)
	{
		this(url);
		setSize(size);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	public String toString() {
		return getUrl();
	}
	
	public BufferedImage loadBufferedImage() throws MalformedURLException, IOException
	{
		return ImageIO.read(new URL(getUrl()));
	}
	
	public String getImageFilename()
	{
		return getUrl().substring(getUrl().lastIndexOf("/") + 1, getUrl().lastIndexOf("."));
	}
	
	public String getImageExtension()
	{
		return getUrl().substring(getUrl().lastIndexOf(".") + 1);
	}

	public List<Image> getSizes() {
		return sizes;
	}

	public void setSizes(List<Image> sizes) {
		this.sizes = sizes;
	}

	public ImageSize getSize() {
		return size;
	}

	public void setSize(ImageSize size) {
		this.size = size;
	}
	
	
	
	
	
	
}
