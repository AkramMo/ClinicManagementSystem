/**
 * Interface qui annonce les méthodes utilisées par le cadre de gestion.
 * Cette interface est destinée à des classes de la famille des javax.swing.JPanel.
 * Le panneau doit afficher les informations de saisie d'un des participants
 * à la clinique.
 *          
 * Elle est utilisée par la classe CadreGestion qui en demande une instance 
 * dans son constructeur.
 * 
 *   ex: public CadreGestion(Clinique clinique, 
 *                                           InterfacePanneauGestion panneauSaisie,...)

 * reset() : Permet au cadre de réinitialiser les champs de saisie à vide.
 * 
 * aviserSiErreur() : Cette méthode est appelée pour laisser au panneau
 *                              la validation des attributs vides. La fonction retourne s'il y
 *                              a eu avis ou non.   
 *  
 * getParticipant() : Retourne le Participant avec les infos saisies dans le panneau.
 *  
 * Dans le cadre du cours inf111, travail pratique numéro 3
 * 
 * Vous n'avez pas à modifier ce code mais vous devez l'utiliser.
 * 
 * @author Pierre Bélisle
 * @version Copyright A2017
 */
package gui;

import clinique.Participant;

public interface InterfacePanSaisieParticipant {


    /**
     * Return un participant créé avec les infos du panneau.
     * 
     * @return Le participant que saisie dans le panneau.
     */
    public Participant getParticipant();


    /**
     * Le panneau doit aviser s'il y a des composants laissés vides
     * ou non sélectionnés.
     * 
     * @return Si l'utilisateur a reçu un avis d'erreur ou non.
     */
    public boolean aviserDuneErreur();


    /**
     * Remet le panneau de saisie dans son état initial.
     */
    public void reset();


}
