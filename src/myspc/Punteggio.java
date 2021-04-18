package myspc;

public class Punteggio {
    	private long tempoIniziale;
	private long tempoFinale;
        private long tempoTrascorso;
	
	public void tStart(){
		tempoIniziale = System.currentTimeMillis();
	}
	
	public void tStop(){
		tempoFinale = System.currentTimeMillis();
		
	}

	public long tTrascorso (){
                tempoTrascorso = tempoFinale - tempoIniziale;
		return tempoTrascorso;
		
	}
	
	public long inizio (){
		return tempoIniziale;
		
	}
	
	public long fine (){
		return tempoFinale;	
	}
        
}
