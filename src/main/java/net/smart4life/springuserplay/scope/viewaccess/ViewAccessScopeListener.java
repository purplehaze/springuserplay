package net.smart4life.springuserplay.scope.viewaccess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.jsf.FacesContextUtils;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import java.util.Map;

/**
 * Created by roman on 28.02.2015.
 */
public class ViewAccessScopeListener implements PhaseListener {
    private static final Logger log = LoggerFactory.getLogger(ViewAccessScopeListener.class);

    @Override
    public void afterPhase(PhaseEvent event) {
        if(PhaseId.RESTORE_VIEW.equals(event.getPhaseId())){
//            log.debug("viewAccessBeans {}", getContainer().getBeanNamesInContainer());
            boolean isPartialRequest = FacesContext.getCurrentInstance().getPartialViewContext().isPartialRequest();
            if(!isPartialRequest) {
                VasContainer container = getContainer();
                container.moveViewContainer();
//                log.debug("viewAccessBeans after move {}", getContainer().getBeanNamesInContainer());
            }
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {
//        if(PhaseId.RESTORE_VIEW.equals(event.getPhaseId())){
//            ViewAccessScopeContainer container = getContainer();
//            container.onRequestStart();
//        }
    }

    private VasContainer getContainer(){
        VasWindowsContainer vasWindowsContainer = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance()).getBean(VasWindowsContainer.class);
        VasContainer container = vasWindowsContainer.getVasContainer();

        return container;
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
}
