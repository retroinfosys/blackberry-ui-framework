package com.framework;

import com.framework.activity.TestActivity;
import com.framework.transition.TransitionFade;
import com.framework.transition.TransitionSlideLeft;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.UiEngineInstance;
import net.rim.device.api.ui.container.MainScreen;

/**
 * An application wrapper for the UiApplication object, it contains
 * screen transitions and methods for pushing and popping activites
 * @author samkirton
 */
public final class HostApplication extends UiApplication {
	private static HostApplication instance;
	private static boolean inTransition = false;
	
	public static HostApplication getInstance() {
		if (!(instance instanceof HostApplication)) {
			instance = new HostApplication();
		}
		
		return instance;
	}
	
	private HostApplication() {
		Ui.getUiEngineInstance().setAcceptableDirections(Display.DIRECTION_NORTH);
		pushScreen(new TestActivity());
	}
	
	/**
	 * Pushes the provided screen to the application screen stack using the default
	 * left transition
	 * @param	screen	The screen to push onto the stack
	 * @param	includePopTransition	Should a transition be used when it is popped from the stack
	 */
	public void pushActivity(final MainScreen screen, final boolean includePopTransition) {
		if (!inTransition) {
			new Thread() {
				public void run() {  
					inTransition = true;
					
					UiEngineInstance engine = Ui.getUiEngineInstance();             
					engine.setTransition(null, screen, UiEngineInstance.TRIGGER_PUSH, TransitionSlideLeft.in());
					
					if (includePopTransition) {
						engine.setTransition(screen, null, UiEngineInstance.TRIGGER_POP, TransitionSlideLeft.out());
					}
					
					UiApplication.getUiApplication().invokeLater(new Runnable() {
						public void run() {
							inTransition = false;
							pushScreen(screen);    
						}      
					});
				}
			}.start();
		}
	}
	
	/**
	 * Push a modal screen to the screen stack
	 * @param	screen	The modal screen to push
	 */
	public void pushModalActivity(final MainScreen screen) {
		if (!inTransition) {
			new Thread() {
				public void run() {
					inTransition = true;
					
					UiEngineInstance engine = Ui.getUiEngineInstance();
					engine.setTransition(null, screen, UiEngineInstance.TRIGGER_PUSH, TransitionFade.in());
					
					UiApplication.getUiApplication().invokeLater(new Runnable() {
						public void run() {
							inTransition = false;
							pushScreen(screen);
						}      
					});
				}
			}.start();
		}
	}
	
	/**
	 * An override of pushScreen that by defaults includes a pop transition
	 * @param	screen	The screen to push onto the stack
	 */
	public void pushActivity(final MainScreen screen) {
		pushActivity(screen,true);
	}
	
	/**
	 * Pops n screens from the UI stack
	 * @param	popAmount	amount of screens to pop from the stack
	 */
	public void popActivity(int popAmount) {
		for (int i = 0; i < popAmount; i++) {
			Screen activeScreen = getActiveScreen();  
			try {
				popScreen(activeScreen);   
			} catch (IllegalArgumentException e) {
				// HACK explanation:
				// It is possible that a thread finishes when a cancel button is pressed,
				// if this occurs the screen will no longer be on the stack and an IllegalArgumentException
				// is thrown. This locks up the application, so all IllegalArgumentExceptions must be caught
				// and ignored.
			}
		}
	}
	
	/**
	 * Pop a single screen from the UI stack
	 */
	public void popSingleActivity() {
		this.popActivity(1);
	}
	
	/**
	 * Pop all but n screens from the stack
	 * @param	retainingScreens	amount of screens to be retained on the stack
	 */
	public void popAndRetainScreens(int retainingScreens) {
		int screenCount = getScreenCount();
		
		int popAmount = screenCount-retainingScreens;
		if (popAmount > 0) 
			this.popActivity(screenCount-retainingScreens);
	}
	
	/**
	 * Pop all but the last screen in the stack
	 */
	public void popAllOverlayActivities() {
		int screenCount = getScreenCount();
		this.popActivity(screenCount-1);
	}
}
