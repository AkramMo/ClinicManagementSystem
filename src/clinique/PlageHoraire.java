/**
 * 
 * @author Mathieu Nayrolles
 * @adapation Pierre Bélisle (ajout de certains commentaires et de constantes).
 * @version Copyright A2017
 */
package clinique;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;



public class PlageHoraire implements Serializable {

    private static final long serialVersionUID = 1L;

    /*
     * Certaines constantes de la classe Clinique du tp2
     * ont été déplacées ici.
     */
    public final static int   HEURE_OUVERTURE  = 8;
    public final static int   HEURE_FERMETURE  = 20;
    public final static int   DUREE_RDV        = 30;

    // La date et l'heure de la plage horaire.
    private Calendar          date;

    // Tous les RV de cette plage.
    private List<RendezVous>  rdvs             = new ArrayList<>();


    /**
     * Crée une plage horaire dans rendez-vous à la date reçue.
     * 
     * @param date
     */
    public PlageHoraire(Calendar date) {

        super();

        this.date = date;

        rdvs = new ArrayList<>();
    }


    public Calendar getDate() {
        return date;
    }


    public void ajouterRdv(RendezVous rdv) {
        this.rdvs.add(rdv);
    }


    public void supprimerRdv(RendezVous rdv) {
        this.rdvs.remove(rdv);
    }


    public boolean rendezVousExiste(RendezVous rdv) {
        return rdvs.contains(rdv);
    }


    /**
     * @return Le nombre de rendez-vous dans cette plage horaire.
     */
    public int getNbRendezVous() {


        // return rdvs.size();
        return 1;
    }


    /**
     * @return Tous les rendezVous d'une plage horaire.
     */
    public List<RendezVous> getRendezVous() {

        return rdvs;
    }


    /**
     * @author Pierre Bélisle
     */
    @Override
    public boolean equals(Object obj) {

        // C'est la même plage horaire si c'est la même date et la même heure.
        PlageHoraire ph = (PlageHoraire) obj;

        int heure = ph.date.get(Calendar.HOUR_OF_DAY);
        int minute = ph.date.get(Calendar.MINUTE);
        int jour = ph.date.get(Calendar.DAY_OF_MONTH);
        int mois = ph.date.get(Calendar.MONTH);
        int annee = ph.date.get(Calendar.YEAR);


        // Aide au déboggage.
        int hDate = date.get(Calendar.HOUR_OF_DAY);
        int minDate = date.get(Calendar.MINUTE);
        int jDate = date.get(Calendar.DAY_OF_MONTH);
        int moisDate = date.get(Calendar.MONTH);
        int aDate = date.get(Calendar.YEAR);

        return heure == hDate && minute == minDate && jour == jDate && mois == moisDate && annee == aDate;
    }


    @Override
    public String toString() {

        String str = new String();

        for (RendezVous rendezVous : rdvs) {
            str += rendezVous + "\n";
        }

        return str;
    }

}
