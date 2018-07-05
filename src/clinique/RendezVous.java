/**
 * @author Mathieu Nayrolles
 */

package clinique;



import java.io.Serializable;


public class RendezVous implements Serializable {

    private static final long serialVersionUID = 1L;

    private Docteur           docteur;
    private Patient           patient;
    private Infirmier         infirmier;


    public RendezVous(Docteur docteur, Patient patient, Infirmier infirmier) {

        this.docteur = docteur;
        this.patient = patient;
        this.infirmier = infirmier;
    }


    public Docteur getDocteur() {

        return docteur;
    }


    public void setDocteur(Docteur docteur) {

        this.docteur = docteur;
    }


    public Patient getPatient() {

        return patient;
    }


    public void setPatient(Patient patient) {

        this.patient = patient;
    }


    public Infirmier getInfirmier() {

        return infirmier;
    }


    public void setInfirmier(Infirmier infirmier) {

        this.infirmier = infirmier;
    }


    @Override
    public boolean equals(Object obj) {

        if (obj == null) {

            return false;
        }

        // Évite pls appels au même acesseur.
        RendezVous rdv = (RendezVous) obj;

        return rdv.docteur.equals(docteur) && rdv.patient.equals(patient) && rdv.infirmier.equals(infirmier);
    }


    @Override
    public String toString() {
        return "docteur : " + docteur + "\n" + ", patient : " + patient + "\n" + ", infirmier : " + infirmier + "\n";
    }
}
