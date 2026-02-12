package model;

public class Pflanze {

    private int id;
    private String name;
    private int ertrag;
    private int dauer;
    private double groesse;

    public Pflanze() {}

    public Pflanze(String name, int ertrag, int dauer, double groesse) {
        this.name = name;
        this.ertrag = ertrag;
        this.dauer = dauer;
        this.groesse = groesse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getErtrag() {
        return ertrag;
    }

    public void setErtrag(int ertrag) {
        this.ertrag = ertrag;
    }

    public int getDauer() {
        return dauer;
    }

    public void setDauer(int dauer) {
        this.dauer = dauer;
    }

    public double getGroesse() {
        return groesse;
    }

    public void setGroesse(double groesse) {
        this.groesse = groesse;
    }
}