package org.owasp.jvmxray.api;

/**
 * This class is a wrapper for executing operations with the context of the
 * NullSecurityManager.  It's purpose is to prevent stack overflows that may
 * occur when code executing within the context of the NullSecurityManager 
 * results in additional calls to the security manager.  
 * 
 * @author Milton Smith
 *
 */
public class SafeExecute {

	private static volatile boolean bSupressRecursion = false;
	
	public SafeExecute() {
	}
	
	public synchronized void execute(NullSecurityManager sm) {
		
		try {
			if( bSupressRecursion ) return;
			bSupressRecursion = true;
			work();
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			bSupressRecursion = false;
		}
	}
	
	public void work() {
	}

}