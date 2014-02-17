package com.framework.base;

import java.util.Vector;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.ScrollChangeListener;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;

/**
 * @author samkirton
 */
public abstract class BaseLayout extends VerticalFieldManager implements ScrollChangeListener  {
	private int mWidth;
	private int mHeight;
	private Vector mFieldsAdded;
	
	/**
	 * Constructor
	 */
	public BaseLayout() {
		super(VERTICAL_SCROLL|USE_ALL_WIDTH);
		this.setBackground(BackgroundFactory.createSolidBackground(0xEEEEEE));
		mWidth = getMeasuredWidth();
		mHeight = getMeasuredHeight();
		this.setScrollListener(this);
	}
	
	/**
	 * Inserts a field above or below the current VerticalFieldManager
	 * @param	field	The field to insert
	 * @param	above	Insert above the VerticalFieldManager?
	 * @param	index	The index to insert the field at
	 */
	public void insertField(Field field, boolean above, int index) {
		if (!mFieldsAdded.contains(field)) {
			if (above) {
				if (index == -1) {
					this.insert(field, 0);
				} else {
					this.insert(field, index);
				}
			} else {
				this.add(field);
			}
			
			mFieldsAdded.addElement(field);
		}
	}
	
	/**
	 * An override of insertField that 
	 * @param	field	The field to insert
	 * @param	above	Insert above the VerticalFieldManager?
	 */
	public void insertField(Field field, boolean above) {
		insertField(field,above,-1);
	}
	
	/**
	 * Removes the field from the VerticalFieldManager if it exists
	 * @param	field	The field to remove
	 */
	public void removeField(Field field) {
		if (mFieldsAdded.contains(field)) {
			mFieldsAdded.removeElement(field);
			this.delete(field);
		}
	}
	
	/**
	 * Does the dynamic field exist
	 * @param	field	The field contained in the dynamic field
	 * @return	Does the dynamic field exist?
	 */
	public boolean fieldExists(Field field) {
		if (mFieldsAdded.contains(field)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Focus the first field in the layout
	 */
	public void focusFirst() {
		if (getFieldCount() > 0) {
			for (int i = 0; i < getFieldCount(); i++) {
				if (getField(i).isFocusable()) {
					getField(i).setFocus();
					break;
				}
			}
		}
	}
	
	public void setPreferredHeight(int height) {
		mHeight = height;
	}
	
	public int getPreferredWidth() {
		return mWidth;
	}
	
	public int getPreferredHeight() {
		return mHeight;
	}
	
	public void sublayout(int width, int height) {
		super.sublayout(mWidth, mHeight);
		setExtent(mWidth,mHeight);
	}
	
	public void scrollChanged(Manager manager, int newHorizontalScroll, int newVerticalScroll) {
		// force the verticalFieldManager to invalidate when a scroll occurs, this is to ensure
		// that fields do not stick to the top of the screen
		this.updateLayout();
	}
	
	/**
	 * @return	Layout width measurement provided by each layout
	 */
	protected abstract int getMeasuredWidth();
	
	/**
	 * @return	Layout height measurement provided by each layout
	 */
	protected abstract int getMeasuredHeight();
}
