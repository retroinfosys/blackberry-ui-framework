package com.framework.layout;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.component.ButtonField;

import com.framework.base.BaseLayout;

public class TestLayout extends BaseLayout {
	private ButtonField uiTestButtonField;
	private ButtonField uiTest2ButtonField;
	
	public TestLayout() {
		uiTestButtonField = new ButtonField("Test");
		uiTest2ButtonField = new ButtonField("Test2");
		
		this.add(uiTestButtonField);
		this.add(uiTest2ButtonField);
	}
	
	protected int getMeasuredWidth() {
		return Display.getWidth();
	}

	protected int getMeasuredHeight() {
		return Display.getHeight();
	}
}
