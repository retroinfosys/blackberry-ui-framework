package com.framework;

/**   
 * The entry point of the application                                                                                                                                                                                              
 * @author samkirton
 */
public final class EntryPoint {	

	public static void main(String[] args) {
		// UI application
		HostApplication hostApplication = HostApplication.getInstance();
		hostApplication.enterEventDispatcher();
	}
}
