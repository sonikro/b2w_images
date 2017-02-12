package br.com.sonikro.b2w_images.dao;

import java.util.Iterator;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import br.com.sonikro.b2w_images.model.Image;

public class ImageDAO {
	private Datastore datastore;
	
	public ImageDAO(Datastore datastore)
	{
		this.datastore = datastore;
	}
	
	public void persistList(List<Image> images)
	{
		Iterator<Image> iterator = images.iterator();
		while(iterator.hasNext())
		{
			Image image = iterator.next();
			persist(image);
		}
	}
	
	public void persist(Image image)
	{
		datastore.save(image);
	}
	
	public List<Image> getList()
	{
		Query<Image> query = datastore.createQuery(Image.class);
		return query.asList();
	}

	public void clear() {
		Query<Image> query = datastore.createQuery(Image.class);
		datastore.delete(query);
	}
}
