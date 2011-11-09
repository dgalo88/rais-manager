/*
 * Created on 13/04/2007
 */
package com.rais.manager;

import java.util.HashMap;
import java.util.Map;

import nextapp.echo.app.ImageReference;
import nextapp.echo.app.ResourceImageReference;

/**
 * @author Demián Gutierrez
 */

public class ImageReferenceCache {

	private static ImageReferenceCache instance = new ImageReferenceCache();

	private Map<String, ImageReference> imageReferenceMap = new HashMap<String, ImageReference>();

	// ----------------------------------------

	public static ImageReferenceCache getInstance() {
		if (instance == null) {
			instance = new ImageReferenceCache();
		}

		return instance;
	}

	// ----------------------------------------

	public ImageReference getImageReference(String name) {
		String key = ImageReference.class.getName() + ":" + name;

		ImageReference ret = imageReferenceMap.get(key);

		if (ret == null) {
			ret = new ResourceImageReference(name);
			imageReferenceMap.put(key, ret);
		}

		return ret;
	}
}
