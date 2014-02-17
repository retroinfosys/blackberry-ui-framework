package com.framework.transition;

/**
 * HACK:
 * A singleton that stores a flag to stop the onExposed method in TripList being 
 * executed when a new screen is being popped on top of it. This should be handled
 * gracefully in the future
 * @author samkirton
 */
public final class TripListExposedController {
	private static TripListExposedController instance;
	private boolean mBlockExecuteOnExposedBlock;
	private boolean mLoadAndPushTripSummary;
	private long mLoadAndPushTripSummaryTripPid;
	
	public boolean getBlockExecuteOnExposedBlock() {
		return mBlockExecuteOnExposedBlock;
	}
	
	public void setBlockExecuteOnExposedBlock(boolean newVal) {
		mBlockExecuteOnExposedBlock = newVal;
	}
	
	public boolean getLoadAndPushTripSummary() {
		return mLoadAndPushTripSummary;
	}
	
	public void setLoadAndPushTripSummary(boolean newVal) {
		mLoadAndPushTripSummary = newVal;
	}
	
	public long getLoadAndPushTripSummaryTripPid() {
		return mLoadAndPushTripSummaryTripPid;
	}
	
	public void setLoadAndPushTripSummaryTripPid(long newVal) {
		mLoadAndPushTripSummaryTripPid = newVal;
	}
	
	private TripListExposedController() {
		mBlockExecuteOnExposedBlock = false;
		mLoadAndPushTripSummary = false;
	}
	
	public static TripListExposedController getInstance() {
		if (!(instance instanceof TripListExposedController)) {
			instance = new TripListExposedController();
		}
		
		return instance;
	}
}
