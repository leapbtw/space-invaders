
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
			sound.sound("src\\audio\\soundtrack.wav");
			try {
				Thread.sleep(195000); //appena finisce ricomincia
			} catch (InterruptedException ex) {}
		}
	}
}
