package libreria;

public class Ordine {
    private Libro libro;
    private Editore editore;
    private int quantita;
    private int progrDiLibro;
    private boolean consegnato;
    public void setConsegnato(boolean consegnato) {
        this.consegnato = consegnato;
    }

    static private int progressivo;

    
    
    public Ordine(Libro libro, Editore editore, int quantita) {
	super();
	this.libro = libro;
	this.editore = editore;
	this.quantita = quantita;
	progrDiLibro = progressivo;
	progressivo++;
	
    }

    public Editore getEditore(){
        return editore;
    }
    
    public Libro getLibro(){
        return libro;
    }
    
    public int getQuantita(){
        return quantita;
    }

    public boolean isConsegnato(){
        return false;
    }

    public int getNumero(){
        return progrDiLibro;
    }
}
