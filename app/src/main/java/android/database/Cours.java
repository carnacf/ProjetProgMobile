package android.database;

/**
 * Created by samuel on 03/05/17.
 */

public class Cours {
    private int id_cours;
    private String nom;
    private String format;
    private String niveau_etud;
    private String specialie;

    public int getId_cours() {
        return id_cours;
    }

    public void setId_cours(int id_cours) {
        this.id_cours = id_cours;
    }

    public String getNom() {
        return nom;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNiveau_etud() {
        return niveau_etud;
    }

    public void setNiveau_etud(String niveau_etud) {
        this.niveau_etud = niveau_etud;
    }

    public String getSpecialie() {
        return specialie;
    }

    public void setSpecialie(String specialie) {
        this.specialie = specialie;
    }
}