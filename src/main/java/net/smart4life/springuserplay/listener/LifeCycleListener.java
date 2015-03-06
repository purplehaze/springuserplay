package net.smart4life.springuserplay.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.faces.lifecycle.ClientWindow;

/**
 * Created by roman on 28.02.2015.
 */
public class LifeCycleListener implements PhaseListener {
    private static final Logger log = LoggerFactory.getLogger(LifeCycleListener.class);

    @Override
    public void afterPhase(PhaseEvent event) {
        String msg = formatRequestInfo(event);
//        log.debug("!!!!!!!!! after{}", msg);
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        String msg = formatRequestInfo(event);
//        log.debug("!!!!!!!!! before{}", msg);
    }

    private String formatRequestInfo(PhaseEvent event){
        FacesContext ctx = FacesContext.getCurrentInstance();
        String viewId = null;
        String wId = null;
        boolean wEnabled = false;
        if(ctx == null){
            viewId = "context is null";
        } else {
            if(ctx.getViewRoot() == null){
                viewId = "viewRoot is null";
            } else {
                viewId = ctx.getViewRoot().getViewId();
            }
            ClientWindow clientWindow = ctx.getExternalContext().getClientWindow();
            if(clientWindow != null)
            {
                wId = clientWindow.getId();
                wEnabled = clientWindow.isClientWindowRenderModeEnabled(ctx);
            } else {
                wId = "clientWindowIsNull";
            }
        }
        return String.format("Phase(phaseId=%s, viewId=%s, wId=%s, wEnabled=%s", event.getPhaseId(), viewId, wId, wEnabled);
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
}
