package net.smart4life.springuserplay.scope.viewaccess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.faces.lifecycle.ClientWindow;
import java.util.Map;

/**
 * Created by roman on 28.02.2015.
 */
public class ViewAccessScopeListener implements PhaseListener {
    private static final Logger log = LoggerFactory.getLogger(ViewAccessScopeListener.class);

    @Override
    public void afterPhase(PhaseEvent event) {
        if(PhaseId.RENDER_RESPONSE.equals(event.getPhaseId())){
            ViewAccessScopeContainer container = getContainer();
            container.onRequestEnd();
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        if(PhaseId.RESTORE_VIEW.equals(event.getPhaseId())){
            ViewAccessScopeContainer container = getContainer();
            container.onRequestStart();
        }
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
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
}
