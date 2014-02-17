package com.framework.base;

import com.framework.layout.NavigationDrawerLayout;
import com.framework.manager.ActivityLayoutManager;

import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.container.MainScreen;

public abstract class BaseActivity extends MainScreen {
	private ActivityLayoutManager uiActivityLayoutManager; 
	private BaseLayout uiNavigationDrawerLayout;
	private BaseLayout uiMainLayout;
	
	protected static final String EMPTY = "";
	
	public BaseLayout getMainLayout() {
		return uiMainLayout;
	}
	
	public boolean isNavigationDrawerOpen() {
		return uiActivityLayoutManager.isOpen();
	}
	
	public BaseActivity() {
		// use the default NavigationDrawerLayout if none is provided
		uiNavigationDrawerLayout = initNavigationDrawerLayout();
		if (!(uiNavigationDrawerLayout instanceof BaseLayout)) {
			uiNavigationDrawerLayout = new NavigationDrawerLayout();
		}
		
		// an illegal argument exception is thrown if a MainLayout is not provided
		uiMainLayout = initMainLayout();
		if (!(uiMainLayout instanceof BaseLayout)) {
			throw new IllegalArgumentException("A MainLayout is always required");
		}
		
		uiActivityLayoutManager = new ActivityLayoutManager(
			uiNavigationDrawerLayout,
			uiMainLayout,
			showNavigationDrawer()
		);
		
		this.add(uiActivityLayoutManager);
		
		// focus the first field in the main layout
		uiMainLayout.focusFirst();
	}
	
	/**
	 * Toggle the navigation drawer layout
	 */
	public void toggleNavigationDrawer() {
		uiActivityLayoutManager.toggleNavigationDrawer();
		if (isNavigationDrawerOpen()) {
			uiNavigationDrawerLayout.focusFirst();
		} else {
			uiMainLayout.focusFirst();
		}
	}
	
	/**
	 * Run the async task on a new thread
	 * @param	asyncTask	The async task to run on the thread
	 */
	protected void runAsynTask(BaseAsyncTask asyncTask) {
		new Thread(asyncTask).start();
	}
	
	/**
	 * Override the keyDown method to toggle the navigation menu when 
	 * the native blackberry menu button is pressed
	 * @see net.rim.device.api.ui.Screen#keyDown(int keycode, int time)
	 */
	protected boolean keyDown(int keycode, int status) {
		switch (Keypad.key(keycode)) {
			case Keypad.KEY_MENU:
				if (showNavigationDrawer()) {
					toggleNavigationDrawer();
					return true;
				}
				
				break;
				
			case Keypad.KEY_ESCAPE:
				if (showNavigationDrawer() && isNavigationDrawerOpen()) {
					toggleNavigationDrawer();
					return true;
				}
				break;
		}
		
		return false;
	}
	
	protected boolean showNavigationDrawer() {
		return true;
	}
	
	protected abstract BaseLayout initNavigationDrawerLayout();
	
	protected abstract BaseLayout initMainLayout();
}
