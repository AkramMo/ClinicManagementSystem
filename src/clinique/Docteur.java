/**
 * Classe pour les docteurs. 
 * 
 * Ls département peuvent être : 	Clinique.CHIRURGIE, 
 * Clinique.UROLOGIE ou Clinique.URGENCE
 * 
 * Dans le cadre du cours inf111, travail pratique numéro 3
 * (voir énoncé INF111 A17 tp3 partie 1 fourni).
 * 
 * @author Pierre Bélisle
 * @version Copyright A2017
 */
package clinique;



public class Docteur extends Participant {

    private static final long serialVersionUID = 1L;

    private int               numDepartement;


    /**
     * Crée un docteur avec l'identification et le numéro de
     * département fournis à l'appel.
     * 
     * @param identification
     *        Doit être non null (aucune validation).
     * @param numDepartement
     *        Le numéro qui peut être Clinique.UROLOGIE...
     * 
     */
    public Docteur(Identification identification, int numDepartement) {

        super(identification);
        this.numDepartement = numDepartement;

    }


    /**
     * @return Le numéro de departement.
     */
    public int getNumDepartement() {
        return numDepartement;
    }


    /**
     * @param numDepartement
     *        Le numéro de departement à utiliser.
     */
    public void setNumDepartement(int numDepartement) {
        this.numDepartement = numDepartement;
    }


    @Override
    public boolean equals(Object obj) {

        // La partie identification laissée au parent.
        return super.equals(obj) && ((Docteur) obj).numDepartement == numDepartement;
    }


    @Override
    public String toString() {

        // La partie identification laissée au parent.
        return super.toString() + "   " + "Departement   :   " + Clinique.getNumDepartement2String(numDepartement);
    }


    @Override
    public String getCategorieParticipant() {
        return "docteur";
    }
}
