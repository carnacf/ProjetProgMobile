package android.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


/**
 * Created by samuel on 03/05/17.
 */

public class EtudiantManager {
    private static final String TABLE_NAME = "Etudiant";
    public static final String KEY_ID_ETUDIANT = "id_etudiant";
    public static final String KEY_NOM_ETUIANT = "nom";
    public static final String KEY_PRENOM_ETUIANT = "prenom";
    public static final String KEY_MDP_ETUIANT = "prenom";
    public static final String KEY_DATE_NAISSANCE_ETUIANT = "date_naisance";
    public static final String KEY_EMAIL_ETUIANT = "email";
    public static final String KEY_NIVEAU_ETUIANT = "niveau_etud";
    public static final String KEY_SPECIALITE_ETUIANT = "specialite";
    public static final String CREATE_TABLE_ANIMAL = "CREATE TABLE "+TABLE_NAME+
            "("+
            " "+KEY_ID_ETUDIANT+" INTEGER primary key,"+
            " "+KEY_NOM_ETUIANT+" TEXT,"+
            " "+KEY_PRENOM_ETUIANT+" TEXT,"+
            " "+KEY_MDP_ETUIANT+" TEXT,"+
            " "+KEY_DATE_NAISSANCE_ETUIANT+" TEXT,"+
            " "+KEY_EMAIL_ETUIANT+" TEXT,"+
            " "+KEY_NIVEAU_ETUIANT+" TEXT,"+
            " "+KEY_SPECIALITE_ETUIANT+" TEXT"+
            ");";
    private MySQLite maBaseSQLite;
    private SQLiteDatabase db;

    // Constructeur
    public EtudiantManager(Context context)
    {
        maBaseSQLite = MySQLite.getInstance(context);
    }

    public void open()
    {
        //on ouvre la table en lecture/écriture
        db = maBaseSQLite.getWritableDatabase();
    }

    public void close()
    {
        //on ferme l'accès à la BDD
        db.close();
    }

    public long addEtudiant(Etudiant etudiant) {
        // Ajout d'un enregistrement dans la table

        ContentValues values = new ContentValues();
        values.put(KEY_NOM_ETUIANT, etudiant.getNom());
        values.put(KEY_PRENOM_ETUIANT, etudiant.getPrenom());
        values.put(KEY_MDP_ETUIANT,etudiant.getMdp());
        values.put(KEY_DATE_NAISSANCE_ETUIANT, etudiant.getDate_naisance());
        values.put(KEY_EMAIL_ETUIANT, etudiant.getEmail());
        values.put(KEY_NIVEAU_ETUIANT, etudiant.getNiveau_etud());
        values.put(KEY_SPECIALITE_ETUIANT, etudiant.getSpecialite());

        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        return db.insert(TABLE_NAME,null,values);
    }

    public int modEtudiant(Etudiant etudiant) {
        // modification d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la requête

        ContentValues values = new ContentValues();
        values.put(KEY_NOM_ETUIANT, etudiant.getNom());
        values.put(KEY_NOM_ETUIANT, etudiant.getNom());
        values.put(KEY_PRENOM_ETUIANT, etudiant.getPrenom());
        values.put(KEY_MDP_ETUIANT,etudiant.getMdp());
        values.put(KEY_DATE_NAISSANCE_ETUIANT, etudiant.getDate_naisance());
        values.put(KEY_EMAIL_ETUIANT, etudiant.getEmail());
        values.put(KEY_NIVEAU_ETUIANT, etudiant.getNiveau_etud());
        values.put(KEY_SPECIALITE_ETUIANT, etudiant.getSpecialite());

        String where = KEY_ID_ETUDIANT+" = ?";
        String[] whereArgs = {etudiant.getId_etudiant()+""};

        return db.update(TABLE_NAME, values, where, whereArgs);
    }

    public int supEtudiant(Etudiant etudiant) {
        // suppression d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon

        String where = KEY_ID_ETUDIANT+" = ?";
        String[] whereArgs = {etudiant.getId_etudiant()+""};

        return db.delete(TABLE_NAME, where, whereArgs);
    }

    public Etudiant getEtudiant(int id) {
        // Retourne l'etudiant dont l'id est passé en paramètre

        Etudiant etu=new Etudiant();

        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+KEY_ID_ETUDIANT+"="+id, null);
        if (c.moveToFirst()) {
            etu.setId_etudiant(c.getInt(c.getColumnIndex(KEY_ID_ETUDIANT)));
            etu.setNom(c.getString(c.getColumnIndex(KEY_NOM_ETUIANT)));
            etu.setPrenom(c.getString(c.getColumnIndex(KEY_PRENOM_ETUIANT)));
            etu.setMdp(c.getString(c.getColumnIndex(KEY_MDP_ETUIANT)));
            etu.setDate_naisance(c.getString(c.getColumnIndex(KEY_DATE_NAISSANCE_ETUIANT)));
            etu.setEmail(c.getString(c.getColumnIndex(KEY_EMAIL_ETUIANT)));
            etu.setNiveau_etud(c.getString(c.getColumnIndex(KEY_NIVEAU_ETUIANT)));
            etu.setSpecialite(c.getString(c.getColumnIndex(KEY_SPECIALITE_ETUIANT)));
            c.close();
        } else {
            return null;
        }
        return etu;
    }

    public Cursor getEtudiants() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
    }

} // class AnimalManager

