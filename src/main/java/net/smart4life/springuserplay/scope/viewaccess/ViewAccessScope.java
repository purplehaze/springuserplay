package net.smart4life.springuserplay.scope.viewaccess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.web.jsf.FacesContextUtils;

import javax.faces.context.FacesContext;
import java.util.Map;


public class ViewAccessScope implements Scope {
    private static final Logger log = LoggerFactory.getLogger(ViewAccessScope.class);

    public static final String NAME = "viewAccess";
    public static final String MAX_WINDOWS_PARAM = "viewAccessScope.max.windows";

    @Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
        log.debug("get() viewAccessScoped bean={}", name);

		VasContainer container = getContainer();
		Object bean = container.get(name);

		if (bean == null) {
            log.debug("!!! create by factory viewAccessScoped bean={}", name);
            bean = objectFactory.getObject();
			container.put(name, bean);
		}

		return bean;
	}

    private VasContainer getContainer(){
        VasWindowsContainer vasWindowsContainer = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance()).getBean(VasWindowsContainer.class);
        VasContainer container = vasWindowsContainer.getVasContainer();

        return container;
    }

    @Override
	public void registerDestructionCallback(String name, Runnable callback) {
		// Unsupported feature
	}

    @Override
	public Object remove(String name) {
        log.debug("!!! remove viewAccessScope bean={}", name);
		VasContainer container = getContainer();
		return container.remove(name);
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