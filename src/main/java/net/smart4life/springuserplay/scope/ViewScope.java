/**
 * Copyright (c) 2000-2014 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
package net.smart4life.springuserplay.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import javax.faces.context.FacesContext;
import java.util.Map;


/**
 * This class serves as a custom Spring scope that decorates the map of view-scoped managed-beans behind the JSF2 {@link
 * javax.faces.bean.ViewScoped} annotation.
 *
 * @author  Neil Griffin
 */
public class ViewScope implements Scope {

    @Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Map<String, Object> viewMap = facesContext.getViewRoot().getViewMap();
		Object viewScopedBean = viewMap.get(name);

		if (viewScopedBean == null) {
			viewScopedBean = objectFactory.getObject();
			viewMap.put(name, viewScopedBean);
		}

		return viewScopedBean;
	}

    @Override
	public void registerDestructionCallback(String name, Runnable callback) {
		// Unsupported feature
	}

    @Override
	public Object remove(String name) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Map<String, Object> viewMap = facesContext.getViewRoot().getViewMap();

		return viewMap.remove(name);
	}

    @Override
	public Object resolveContextualObject(String key) {
		// Unsupported feature
		return null;
	}

    @Override
	public String getConversationId() {
		// Unsupported feature
		return null;
	}

}