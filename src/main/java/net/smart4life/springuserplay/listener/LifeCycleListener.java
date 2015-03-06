package net.smart4life.springuserplay.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.context.FacesContext;
import javax.faces.context.PartialViewContext;
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
        log.debug("--- after{}", msg);
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        String msg = formatRequestInfo(event);
        log.debug("--- before{}", msg);
    }

    private String formatRequestInfo(PhaseEvent event){
        FacesContext ctx = FacesContext.getCurrentInstance();
        String viewId = null;
        Boolean isPostback = null;
        Boolean isPartialRequest = null;
        Boolean isRenderAll = null;
        Boolean isResetValues = null;
        if(ctx == null){
            viewId = "context is null";
        } else {
            if(ctx.getViewRoot() == null){
                viewId = "viewRoot is null";
            } else {
                viewId = ctx.getViewRoot().getViewId();
            }

            isPostback = ctx.isPostback();
            PartialViewContext pwc = ctx.getPartialViewContext();
            isPartialRequest = pwc.isPartialRequest();
            isRenderAll = pwc.isRenderAll();
            isResetValues = pwc.isResetValues();
        }
        return String.format("Phase(phaseId=%s, viewId=%s, isPostback=%s, isPartialRequest=%s, isRenderAll=%s, isResetValues=%s",
                event.getPhaseId(),
                viewId,
                isPostback,
                isPartialRequest,
                isRenderAll,
                isResetValues);
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
}
