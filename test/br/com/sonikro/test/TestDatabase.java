package br.com.sonikro.test;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.sonikro.b2w_images.database.DatastoreManager;

public class TestDatabase {

	@Test
	public void validateDatabaseConnection() {
		assertNotNull(DatastoreManager.getMongoDatastore());
	}

}
