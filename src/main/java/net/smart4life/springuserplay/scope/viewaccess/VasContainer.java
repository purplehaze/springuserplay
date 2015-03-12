package net.smart4life.springuserplay.scope.viewaccess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.*;

/**
 * Created by roman on 06.03.2015.
 */
public class VasContainer implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(VasContainer.class);

    public static final String NAME = VasContainer.class.getName();

    private List<Map<String, Object>> containerList = new ArrayList<>();
    private final String windowId;
    private long lastAccess = 0;

    public VasContainer(String windowId){
        containerList.add(new HashMap<String, Object>());
        this.windowId = windowId;
    }

    public void moveViewContainer(){
//        logger.debug("!!!!! start moveViewContainer() size={}", containerList.size());
        containerList.add(new HashMap<String, Object>());
        while (containerList.size() > 2){
            Map<String, Object> map = containerList.get(0);
            map.clear();
            containerList.remove(0);
        }
//        logger.debug("!!!!! end moveViewContainer() size={}", containerList.size());
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

        lastAccess = System.currentTimeMillis();

        return bean;
    }

    public void put(String name, Object bean){
        containerList.get(containerList.size() - 1).put(name, bean);

        lastAccess = System.currentTimeMillis();
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

    public void destroy(){
        for(Map<String, Object> map : containerList){
            map.clear();
        }
        containerList.clear();
        containerList = null;
    }

    public long getLastAccess() {
        return lastAccess;
    }

    public String getWindowId() {
        return windowId;
    }
}
