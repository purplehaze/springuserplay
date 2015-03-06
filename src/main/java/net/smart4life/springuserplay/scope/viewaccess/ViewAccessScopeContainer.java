package net.smart4life.springuserplay.scope.viewaccess;

import java.io.Serializable;
import java.util.*;

/**
 * Created by roman on 06.03.2015.
 */
public class ViewAccessScopeContainer implements Serializable {
    public static final String NAME = ViewAccessScopeContainer.class.getName();

    private List<Map<String, Object>> containerList = new ArrayList<>();

    public ViewAccessScopeContainer(){
        containerList.add(new HashMap<String, Object>());
    }

    public void onRequestStart(){
        containerList.add(new HashMap<String, Object>());
    }

    public void onRequestEnd(){
        containerList.remove(0);
    }

    public Object get(String name){
        Object bean = null;
        Map<String, Object> currentMap = containerList.get(1);
        bean = currentMap.get(name);
        if(bean == null){
            Map<String, Object> prevMap = containerList.get(0);
            bean = prevMap.get(name);
            if(bean != null){
                currentMap.put(name, bean);
            }
        }

        return bean;
    }

    public void put(String name, Object bean){
        containerList.get(1).put(name, bean);
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
}
