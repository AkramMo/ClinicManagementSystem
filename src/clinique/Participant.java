/**
 * Classe pour regrouper le code commun au participant d'une clinique médicale.
 * 
 * Dans le cadre du cours inf111, travail pratique numéro 3
 * 
 * @author Pierre Bélisle
 * @version Copyright A2017
 */
package clinique;



import java.io.Serializable;



public class Participant implements Serializable {

    private static final long serialVersionUID = 1L;

    // Dans ce projet, le seul code commun est celui de l'identification.
    private Identification    identification;


    /**
     * Constructeur qui nécessite une identification.
     * 
     * Le constructeur par défaut n'est pas implémenté.
     * 
     * @param identification
     *        Doit être non null (aucune validation).
     */
    public Participant(Identification identification) {

        this.identification = identification;
    }


    /**
     * @return L'identification du participant.
     */
    public Identification getIdentification() {
        return identification;
    }


    /**
     * @param identification
     *        L' identification à utiliser.
     */
    public void setIdentification(Identification identification) {
        this.identification = identification;
    }


    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Participant other = (Participant) obj;
        if (identification == null) {
            if (identification.getNom() != null)
                return false;
        }
        else if (!identification.getNom().equals(other.identification.getNom()))
            return false;
        if (identification.getPrenom() == null) {
            if (identification.getPrenom() != null)
                return false;
        }
        else if (!identification.getPrenom().equals(other.identification.getPrenom()))
            return false;
        return true;
    }


    @Override
    public String toString() {
        return "Nom : " + identification.getNom() + "    " + "Prénom : " + identification.getPrenom();
    }


    /**
     * Retourne une chaîne pour identifier la catégorier
     * de participant (pratique pour les tittre de cadre par exemple).
     * 
     * @return La catégorie de participant en String.
     */
    public String getCategorieParticipant() {
        return "participant";
    }

}
