package net.smart4life.springuserplay.scope.viewaccess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.*;

/**
 * Created by roman on 06.03.2015.
 */
public class ViewAccessScopeContainer implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(ViewAccessScopeContainer.class);

    public static final String NAME = ViewAccessScopeContainer.class.getName();

    private List<Map<String, Object>> containerList = new ArrayList<>();

    public ViewAccessScopeContainer(){
        containerList.add(new HashMap<String, Object>());
    }

    public void moveViewContainer(){
        logger.debug("!!!!! start moveViewContainer() size={}", containerList.size());
        containerList.add(new HashMap<String, Object>());
        while (containerList.size() > 2){
            Map<String, Object> map = containerList.get(0);
            map.clear();
            containerList.remove(0);
        }
        logger.debug("!!!!! end moveViewContainer() size={}", containerList.size());
    }

    public Object get(String name){
        Object bean = null;
        final int containerSize = containerList.size();
        Map<String, Object> currentMap = containerList.get(containerSize - 1);
        bean = currentMap.get(name);
        if(bean == null && containerList.size() > 1){
            Map<String, Object> prevMap = containerList.get(containerSize - 2);
            bean = prevMap.get(name);
            if(bean != null){
                currentMap.put(name, bean);
            }
        }

        return bean;
    }

    public void put(String name, Object bean){
        containerList.get(containerList.size() - 1).put(name, bean);
    }

    public Object remove(String name){
        Object bean = null;
        for(Map<String, Object> map : containerList){
            Object removed = map.remove(name);
            if(removed != null){
                bean = removed;
            }
        }
        return bean;
    }

    public Set<String> getBeanNamesInContainer(){
        return containerList.get(containerList.size() - 1).keySet();
    }
}
