package br.com.sonikro.b2w_images.model;

public class ImageSize {
	private Integer width;
	private Integer heigth;
	private String sizeName;
	
	public ImageSize(Integer width, Integer heigth)
	{
		this.width = width;
		this.heigth = heigth;
	}
	
	public ImageSize(Integer width, Integer heigth, String sizeName)
	{
		this(width,heigth);
		setSizeName(sizeName);
	}
	
	public ImageSize()
	{
		
	}
	
	public Integer getWidth() {
		return width;
	}

	public Integer getHeigth() {
		return heigth;
	}
	public String getSizeName() {
		return sizeName;
	}
	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public void setHeigth(Integer heigth) {
		this.heigth = heigth;
	}
	
	

	
}
