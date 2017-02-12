package br.com.sonikro.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;


import org.junit.Test;

import br.com.sonikro.b2w_images.api.ExternalImageAPI;
import br.com.sonikro.b2w_images.model.Image;

public class TestImageDownloadAPI {
	private final static int EXPECTED_NUMBER_OF_IMAGES = 10;
	@Test
	public void Test_WS_Consume_Image_Quantity() {
		Client client = ClientBuilder.newClient();
		ExternalImageAPI service = new ExternalImageAPI(client);
		List<Image> imageList = service.getImageList();
		assertTrue(imageList.size() == EXPECTED_NUMBER_OF_IMAGES);
	}

}
