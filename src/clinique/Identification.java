/**
 * Classe pour identifier les différents participants
 * à la clinique.
 * 
 * Dans le cadre du cours inf111, travail pratique numéro 3
 * 
 * @author Pierre Bélisle
 * @version Copyright A2017
 */
package clinique;



import java.io.Serializable;



public class Identification implements Serializable {

    private static final long serialVersionUID = 1L;

    private String            nom;
    private String            prenom;


    /**
     * Constructeur d'une identification avec le nom et le prénom.
     * 
     * Les paramèetres doivent être non null, mais aucune validation n'est
     * effectuée.
     * 
     * @param nom
     * @param prenom
     */
    public Identification(String nom, String prenom) {

        this.nom = nom;
        this.prenom = prenom;

    }


    /**
     * @return Le nom
     */
    public String getNom() {
        return nom;
    }


    /**
     * @param nom
     *        Le nom à modifier.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }


    /**
     * @return Le prénom à modifier
     */
    public String getPrenom() {
        return prenom;
    }


    /**
     * @param prenom
     *        Le prénom à utiliser.
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Identification other = (Identification) obj;
        if (nom == null) {
            if (other.nom != null)
                return false;
        }
        else if (!nom.equals(other.nom))
            return false;
        if (prenom == null) {
            if (other.prenom != null)
                return false;
        }
        else if (!prenom.equals(other.prenom))
            return false;
        return true;
    }
}
