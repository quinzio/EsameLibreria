package libreria;

import java.util.ArrayList;
import java.util.List;

public class Libro {
    private String titolo;
    private String autore;
    private int anno;
    private double prezzo;
    private String nomeEditore;
    private int quantita;
    private Libreria libreria;
    private List<Vendita> vendite = new ArrayList<>();
    private int sogliamin;
    private int riordino;


    public int getRiordino() {
        return riordino;
    }

    public Libreria getLibreria() {
        return libreria;
    }

    public void setLibreria(Libreria libreria) {
        this.libreria = libreria;
    }

    public Libro(String titolo, String autore, int anno, double prezzo, String nomeEditore) {
	super();
	this.titolo = titolo;
	this.autore = autore;
	this.anno = anno;
	this.prezzo = prezzo;
	this.nomeEditore = nomeEditore;
    }

    public String getTitolo() {
	return titolo;
    }

    public String getAutore() {
	return autore;
    }

    public int getAnno() {
	return anno;
    }

    public double getPrezzo() {
	return prezzo;
    }

    // public Editore getEditore(){
    public String getEditore() {
	return nomeEditore;
    }

    public void setQuantita(int q) {
	this.quantita = quantita;
    }

    public int getQuantita() {
	return quantita;
    }

    public void registraVendita(int settimana, int mese) {
	Vendita	vend = new Vendita(mese, settimana, this);
	libreria.vendite.add(vend);
	vendite.add(vend);
	quantita--;
	if (quantita == sogliamin -1) {
	    Editore edi = libreria.editoriDaNome.get(nomeEditore);
	    Ordine ordNew = new Ordine(this, edi, mese);
	    libreria.ordini.add(ordNew);
	}
    }

    public void setParametri(int soglia, int quantitaRiordino) {
	sogliamin = soglia;
	riordino = quantitaRiordino;
    }
}
