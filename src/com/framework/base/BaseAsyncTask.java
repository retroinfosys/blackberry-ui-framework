package com.framework.base;

import net.rim.device.api.ui.UiApplication;

import com.framework.callback.AsyncTaskCallback;

/**
 * An abstract class that handles running asynchronous threading, all AsyncTask
 * classes must extend from BaseAsyncTask to access the threading implementation.
 * All callbacks from the thread are returned via the UI thread, this approach has been
 * taken because generally the result of a thread is always returned to the UI.
 * 
 * TODO make returning to the UI thread optional, background task threads may not 
 * wish to run their result in the UI thread
 * @author samkirton
 */
public abstract class BaseAsyncTask implements Runnable {
	private volatile boolean mShouldExecute;
	private AsyncTaskCallback mAsyncTaskCallback;
	private final BaseAsyncTask reference;
	
	public void setAsyncTaskCallback(AsyncTaskCallback asyncTaskCallback) {
		mAsyncTaskCallback = asyncTaskCallback;
	}
	
	/**
	 * Store a reference of this class so that it can be return by the UI thread
	 */
	public BaseAsyncTask() {
		reference = this;
	}
	
	/**
	 * Cancel the run method, due to the nature of Blackberry threading,
	 * this method is not guaranteed to cancel the thread immediately
	 */
	public void cancel() {
		mShouldExecute = false;
	}
	
	/**
	 * @see java.lang.Runnable.run()
	 */
	public void run() {
		mShouldExecute = true;
		
		while (mShouldExecute) {
			execute();
			mShouldExecute = false;
			break;
		}
		
		UiApplication.getUiApplication().invokeLater(new Runnable() {
			public void run() {
				mAsyncTaskCallback.asyncResponse(reference,getResponse());
			}
		});
	}
	
	/**
	 * @return	the response of the AsyncTask
	 */
	protected abstract BaseResponse getResponse();
	
	/**
	 * Run the async task
	 */
	protected abstract void execute();
}
