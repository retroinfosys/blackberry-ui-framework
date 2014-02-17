package com.framework.layout;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.decor.BackgroundFactory;

import com.framework.base.BaseLayout;

/**
 * @author samkirton
 */
public class NavigationDrawerLayout extends BaseLayout {
	private ButtonField uiNavigationButtonField1;
	private ButtonField uiNavigationButtonField2;
	private ButtonField uiNavigationButtonField3;
	
	public NavigationDrawerLayout() {
		this.setBackground(BackgroundFactory.createSolidBackground(0x333333));
		uiNavigationButtonField1 = new ButtonField("Navigation item 1");
		uiNavigationButtonField2 = new ButtonField("Navigation item 2");
		uiNavigationButtonField3 = new ButtonField("Navigation item 3");
		
		this.add(uiNavigationButtonField1);
		this.add(uiNavigationButtonField2);
		this.add(uiNavigationButtonField3);
	}

	protected int getMeasuredWidth() {
		return Display.getWidth() * 60 / 100;
	}

	protected int getMeasuredHeight() {
		return Display.getHeight();
	}

	public void focusChanged(Field field, int eventType) {
		// TODO Auto-generated method stub
		
	}
}
