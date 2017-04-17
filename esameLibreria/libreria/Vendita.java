package libreria;

public class Vendita {
    private int month;
    private int week;
    private Libro libro;
    public Vendita(int month, int week, Libro libro) {
	super();
	this.month = month;
	this.week = week;
	this.libro = libro;
    }
    public int getMonth() {
        return month;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public int getWeek() {
        return week;
    }
    public void setWeek(int week) {
        this.week = week;
    }
    public Libro getLibro() {
        return libro;
    }
    public void setLibro(Libro libro) {
        this.libro = libro;
    }
    

}
