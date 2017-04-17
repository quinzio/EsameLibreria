package libreria;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;


public class Libreria {

    Map<String, Editore> editoriDaNome = new TreeMap<>();
    List<Libro> libriSet = new ArrayList<>();
    List<Vendita> vendite = new ArrayList<>();
    List<Ordine> ordini = new ArrayList<>();

    public Editore creaEditore(String nome, int tempoConsegna, String email) {
	Editore edi = new Editore(nome, tempoConsegna, email);
	editoriDaNome.put(nome, edi);
	return edi;
    }

    public Editore getEditore(String nome) {
	return editoriDaNome.get(nome);
    }

    @SuppressWarnings("rawtypes")
    public Collection getEditori() {
	return editoriDaNome.values();
    }

    public Libro creaLibro(String titolo, String autore, int anno, double prezzo, String nomeEditore)
	    throws EditoreInesistente {

	Libro lib = new Libro(titolo, autore, anno, prezzo, nomeEditore);
	libriSet.add(lib);
	lib.setLibreria(this);
	return lib;
    }

    public Libro getLibro(String autore, String titolo) {
	List<Libro> scelti = new ArrayList<>();
	if (autore == null && titolo == null)
	    return null;

	if ((titolo != null) && (autore != null)) {
	    scelti = libriSet.stream().filter(l -> l.getAutore().equals(autore))
		    .filter(l -> l.getTitolo().equals(titolo)).collect(Collectors.toList());
	    return scelti.get(0);
	}

	if (titolo != null) {
	    scelti = libriSet.stream().filter(l -> l.getTitolo().equals(titolo)).collect(Collectors.toList());
	    return scelti.get(0);
	}
	if (autore != null) {
	    scelti = libriSet.stream().filter(l -> l.getAutore().equals(titolo)).collect(Collectors.toList());
	    return scelti.get(0);
	}

	return null;

    }

    @SuppressWarnings("rawtypes")
    public Collection getClassificaSettimana(final int settimana) {
	List<Libro> list1 = vendite.stream().filter(v -> v.getWeek() == settimana)
		.collect(Collectors.groupingBy(Vendita::getLibro, HashMap::new, Collectors.counting())).entrySet()
		.stream().sorted((en1, en2) -> (int) (en1.getValue() - en2.getValue())).map(en -> en.getKey())
		.collect(Collectors.toList());
	return list1;
    }

    @SuppressWarnings("rawtypes")
    public Collection getClassificaMese(final int mese) {

	List<Libro> list1 = vendite.stream().filter(v -> v.getMonth() == mese)
		.collect(Collectors.groupingBy(Vendita::getLibro, HashMap::new, Collectors.counting())).entrySet()
		.stream().sorted((en1, en2) -> (int) (en1.getValue() - en2.getValue())).map(en -> en.getKey())
		.collect(Collectors.toList());
	return list1;
    }

    @SuppressWarnings("rawtypes")
    public Collection getOrdini() {
	return ordini;
    }

    public void ordineRicevuto(int numOrdine) {
	Ordine ord = ordini.stream().filter(ords -> ords.getNumero() == numOrdine).collect(Collectors.toList()).get(0);
	ord.setConsegnato(true);
	Libro l = ord.getLibro();
	l.setQuantita(l.getQuantita() + l.getRiordino());
    }

    public void leggi(String file) {
	List<String> lines = null;
	try {
	    Path p = Paths.get(file);
	    lines = Files.readAllLines(p);
	} catch (IOException e1) {
	    System.out.println("File error");
	} catch (InvalidPathException e1) {
	    System.out.println("Path not found");
	}
	Scanner scan;
	String type;
	// Editore
	Editore ed;
	String nome;
	int tempoConsegna;
	String email;
	// LIbro
	Libro li;
	String ltitolo;
	String lautore;
	int lanno;
	int lprezzo;
	String leditore;
	int lquantita;

	for (String s : lines) {
	    scan = new Scanner(s);
	    type = scan.next();
	    if (type.equals("E")) {
		try {
		    nome = scan.next();
		    tempoConsegna = scan.nextInt();
		    email = scan.next();
		    ed = new Editore(nome, tempoConsegna, email);
		    editoriDaNome.put(nome, ed);
		} catch (Exception e) {
		}
	    }
	    if (type.equals("L")) {

		try {
		    ltitolo = scan.next();
		    lautore = scan.next();
		    lanno = scan.nextInt();
		    lprezzo = scan.nextInt();
		    leditore = scan.next();
		    lquantita = scan.nextInt();
		    li = new Libro(ltitolo, lautore, lanno, lprezzo, leditore);
		    li.setQuantita(lquantita);
		} catch (Exception e) {
		}
	    }

	}

    }
}
