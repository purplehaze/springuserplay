package net.smart4life.springuserplay.scope.viewaccess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import javax.faces.context.FacesContext;
import javax.faces.lifecycle.ClientWindow;
import java.util.Map;


public class ViewAccessScope implements Scope {
    private static final Logger log = LoggerFactory.getLogger(ViewAccessScope.class);

    public static final String NAME = "viewAccess";

    @Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
        log.debug("get() viewAccessScoped bean={}", name);

		ViewAccessScopeContainer container = getContainer();
		Object bean = container.get(name);

		if (bean == null) {
            log.debug("!!! create by factory viewAccessScoped bean={}", name);
            bean = objectFactory.getObject();
			container.put(name, bean);
		}

		return bean;
	}

    private ViewAccessScopeContainer getContainer(){
        Map<String, Object> sessMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        ViewAccessScopeContainer container = (ViewAccessScopeContainer) sessMap.get(ViewAccessScopeContainer.NAME);
        if(container == null){
            container = new ViewAccessScopeContainer();
            sessMap.put(ViewAccessScopeContainer.NAME, container);
        }

        return container;
    }

    @Override
	public void registerDestructionCallback(String name, Runnable callback) {
		// Unsupported feature
	}

    @Override
	public Object remove(String name) {
        log.debug("!!! remove viewAccessScope bean={}", name);
		ViewAccessScopeContainer container = getContainer();
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