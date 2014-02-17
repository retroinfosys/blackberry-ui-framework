package com.framework.activity;

import com.framework.base.BaseActivity;
import com.framework.base.BaseLayout;
import com.framework.layout.TestLayout;

/**
 * @author samkirton
 */
public class TestActivity extends BaseActivity {

	protected BaseLayout initNavigationDrawerLayout() {
		return null;
	}

	protected BaseLayout initMainLayout() {
		return new TestLayout();
	}
}
