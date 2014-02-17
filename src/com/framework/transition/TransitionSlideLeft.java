package com.framework.transition;

import net.rim.device.api.ui.TransitionContext;

/**
 * @author samkirton
 */
public final class TransitionSlideLeft {
	private static int inSpeed = 200;
	private static int outSpeed = 200;
	
	public static TransitionContext in() {
		TransitionContext transitionContextIn;
		transitionContextIn = new TransitionContext(TransitionContext.TRANSITION_SLIDE);
		transitionContextIn.setIntAttribute(TransitionContext.ATTR_DURATION, inSpeed);
		return transitionContextIn;
	}
	
	public static TransitionContext out() {
		TransitionContext transitionContextOut;
		transitionContextOut = new TransitionContext(TransitionContext.TRANSITION_SLIDE);
		transitionContextOut.setIntAttribute(TransitionContext.ATTR_DURATION, outSpeed);                    
		transitionContextOut.setIntAttribute(TransitionContext.ATTR_DIRECTION, TransitionContext.DIRECTION_RIGHT); 
		return transitionContextOut;
	}
}
