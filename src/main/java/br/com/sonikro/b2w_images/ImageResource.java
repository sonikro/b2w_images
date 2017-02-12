package br.com.sonikro.b2w_images;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.sonikro.b2w_images.dao.ImageDAO;
import br.com.sonikro.b2w_images.database.DatastoreManager;
import br.com.sonikro.b2w_images.model.Image;


@Path("/images")
public class ImageResource {

    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Image> getIt() throws MalformedURLException {
    	ImageDAO dao = new ImageDAO(DatastoreManager.getMongoDatastore());
    	return dao.getList();
    }
}
