package net.smart4life.springuserplay.scope.viewaccess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.lifecycle.ClientWindow;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by roman on 12.03.2015.
 */
@Component
@Scope("session")
public class VasWindowsContainer implements Serializable {
    public static final Logger log = LoggerFactory.getLogger(VasWindowsContainer.class);
    private Integer maxWindows;

    private Map<String, VasContainer> windowContainerMap = new ConcurrentHashMap<>();

    @Autowired
    private Environment env;

    @PostConstruct
    private void init(){
        String maxWindowsStr = env.getProperty(ViewAccessScope.MAX_WINDOWS_PARAM);
        try{
            maxWindows = Integer.valueOf(maxWindowsStr);
        }catch (Exception e){}

        if(maxWindows == null){
            maxWindows = 16;
        }

        log.info("ViewAccessScopeWindowsContainer initialized with maxWindows={}", maxWindows);
    }

    public VasContainer getVasContainer(){
        String wid = FacesContext.getCurrentInstance().getExternalContext().getClientWindow().getId();
        VasContainer container = windowContainerMap.get(wid);

        if(container == null){
            if(windowContainerMap.size() >= maxWindows){
                synchronized (windowContainerMap) {
                    TreeMap<Long, String> accessKeyMap = new TreeMap<>();
                    for(VasContainer vasContainer : windowContainerMap.values()){
                        accessKeyMap.put(vasContainer.getLastAccess(), vasContainer.getWindowId());
                    }

                    while (windowContainerMap.size() > maxWindows - 1){
                        long lastAccessToRemove = accessKeyMap.firstEntry().getKey();
                        String widToRemove = accessKeyMap.firstEntry().getValue();
                        VasContainer vasContainer = windowContainerMap.get(widToRemove);
                        vasContainer.destroy();
                        windowContainerMap.remove(widToRemove);
                        accessKeyMap.remove(lastAccessToRemove);
                    }
                }
            }

            container = new VasContainer(wid);
            log.debug("new VasContainer({}) created", wid);
            windowContainerMap.put(wid, container);
        }

        return container;
    }
}
