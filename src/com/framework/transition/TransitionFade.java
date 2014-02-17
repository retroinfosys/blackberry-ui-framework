package com.framework.transition;

import net.rim.device.api.ui.TransitionContext;

/**
 * @author samkirton
 */
public final class TransitionFade {
	private static int defaultInSpeed = 200;
	private static int defaultOutSpeed = 200;
	
	public static TransitionContext in() {
		return in(defaultInSpeed);
	}
	
	public static TransitionContext in(int inSpeed) {
		TransitionContext transitionContextIn;
		transitionContextIn = new TransitionContext(TransitionContext.TRANSITION_FADE);
		transitionContextIn.setIntAttribute(TransitionContext.ATTR_DURATION, inSpeed); 
		return transitionContextIn;
	}
	
	public static TransitionContext out() {
		return out(defaultOutSpeed);
	}
	
	public static TransitionContext out(int outSpeed) {
		TransitionContext transitionContextOut;
		transitionContextOut = new TransitionContext(TransitionContext.TRANSITION_FADE);
		transitionContextOut.setIntAttribute(TransitionContext.ATTR_DURATION, outSpeed); 
		transitionContextOut.setIntAttribute(TransitionContext.ATTR_KIND, TransitionContext.KIND_OUT);
		return transitionContextOut;
	}
}
