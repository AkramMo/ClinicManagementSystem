/**
 * Classe pour les patients.
 * 
 * Dans le cadre du cours inf111, travail pratique numéro 3
 * (voir énoncé INF111 A17 tp3 partie 1 fourni).
 * 
 * @author Pierre Bélisle
 * @version Copyright A2017
 */
package clinique;



public class Patient extends Participant {

    private static final long serialVersionUID = 1L;

    private String            nas;


    /**
     * Crée un patient avec l'identification et le numéro d'assurance social
     * fournis à l'appel.
     * 
     * @param identificationParticipant
     * @param nas
     */
    public Patient(Identification identification, String nas) {

        super(identification);
        this.nas = nas;

    }


    /**
     * @return Le numéro d'assurance social.
     */
    public String getNas() {
        return nas;
    }

    
    /**
     * @param nas
     *        Le numéro d'assurance social à utiliser.
     */
    public void setNas(String nas) {
        this.nas = nas;
    }


    @Override
    public boolean equals(Object obj) {

        // La partie identification laissée au parent.
        return super.equals(obj) && ((Patient) obj).nas.equals(nas);
    }


    @Override
    public String toString() {

        // La partie identification laissée au parent.
        return super.toString() + "   " + "NAS : " + nas;
    }


    @Override
    public String getCategorieParticipant() {
        return "patient";
    }

}
