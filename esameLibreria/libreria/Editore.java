package libreria;

public class Editore {
    String nome;
    int tempoConsegna;
    String email;

    
    public Editore(String nome, int tempoConsegna, String email) {
	super();
	this.nome = nome;
	this.tempoConsegna = tempoConsegna;
	this.email = email;
    }

    public String getNome(){
        return nome;
    }
    
    public int getTempoConsegna(){
        return tempoConsegna;
    }
    
    public String getEmail(){
        return email;
    }
 
}
