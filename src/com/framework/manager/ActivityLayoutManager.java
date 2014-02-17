package com.framework.manager;


import com.framework.base.BaseLayout;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.container.AbsoluteFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;

/**
 * @author	samkirton@me.com
 */
public final class ActivityLayoutManager extends AbsoluteFieldManager {
	private Manager mNavigationDrawerLayout;
	private Manager mActivityContainerLayout;
	private int mNavigationDrawerWidth;
	private boolean mShowNavigationDrawer;
	private boolean mCollapsed = true;
	private int mWidth;
	private int mHeight;
	
	public boolean isOpen() {
		return !mCollapsed;
	}
	
	public ActivityLayoutManager(BaseLayout navigationDrawerLayout, BaseLayout activityLayout, boolean showNavigationDrawer) {
		mNavigationDrawerLayout = navigationDrawerLayout;
		mNavigationDrawerWidth = mNavigationDrawerLayout.getPreferredWidth();
		mShowNavigationDrawer = showNavigationDrawer;
		mWidth = Display.getWidth();
		mHeight = activityLayout.getPreferredHeight();
		
		if (showNavigationDrawer) {
			add(mNavigationDrawerLayout, 0, 0);
		}
		
		mActivityContainerLayout = new VerticalFieldManager(NO_VERTICAL_SCROLL);
		mActivityContainerLayout.add(activityLayout);
		
		add(mActivityContainerLayout, 0, 0);
		this.setBackground(BackgroundFactory.createSolidBackground(0x000000));
	}
	
	/**
	 * Open or close the navigation drawer
	 */
	public void toggleNavigationDrawer() {
		if (mCollapsed) {
			this.setPosChild(mActivityContainerLayout, mNavigationDrawerWidth, 0);
			mCollapsed = false;
			invalidate();
		} else {
			this.setPosChild(mActivityContainerLayout, 0, 0);
			mCollapsed = true;
			invalidate();
		}
	}
	
	public void sublayout(int width, int height) {
		super.sublayout(mWidth, mHeight);
		setExtent(mWidth,mHeight);
	}
	
	protected int nextFocus(int direction, int axis) {
		int nextFocusIndex = getFieldWithFocusIndex();
		if (mShowNavigationDrawer && nextFocusIndex == 0 && direction == /*DOWN*/1) {
			return nextFocusIndex;
		} else {
			return super.nextFocus(direction, axis);
		}
	}
}