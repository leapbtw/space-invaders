
package myspc;

/**
 *
 * @author leapbtw
 */

public class StartGame extends Thread {
	
	@Override 
	public void run() {
		while (SpcInv.start == false) {
			System.out.print("");
		}
	}
}
