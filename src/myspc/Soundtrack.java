
package myspc;

/**
 *
 * @author leapbtw
 */

public class Soundtrack extends Thread {
	SoundPlayer sound = new SoundPlayer();
	
	@Override 
	public void run() {
		while (true) {
			sound.sound("src\\myspc\\audio\\soundtrack.wav");
			try {
				Thread.sleep(195300); //appena finisce ricomincia
			} catch (InterruptedException ex) {}
		}
	}
}
