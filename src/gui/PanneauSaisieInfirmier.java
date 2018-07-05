package gui;

import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import clinique.Infirmier;

/**
 *Classe qui gère le panneau de saisi
 *spécifique aux infirmières. Ce panneau de saisi
 * qui contient une checkbox pour dire si l'infirmier est disponible
 * Hérite de la classe PanneauSaisiParticipant
 * @author Akram Mousselmal
 * @version Copyright 2017
 *
 */
public class PanneauSaisieInfirmier extends PanneauSaisieParticipant {

	private static final long serialVersionUID = 1L;

	// Check box pour vérifie si infirmier disponible ou pas
	private JRadioButton disponible;

	private JRadioButton indisponible;

	// BoutonGroup regroupant mes checkBox
	private ButtonGroup group;


	/**
	 * Constructeur par défaut qui initialise mes composantes
	 * et appel constructeur de classe mère
	 */
	public PanneauSaisieInfirmier() {

		// Constructeur de classe mère
		super();

		// Procédure qui initialise toute mes composantes
		initComposante();

	}
	/**
	 * Procédure qui initialise tout mes composantes
	 * et qui set mes checkBox et leurs positions
	 */
	private void initComposante() {

		// Procédure qui crée mon groupButton
		setCheckBox();

		// Ajoute mes components dans la panneau de saisi spécifique aux
		// infirmier
		this.add(disponible);

		this.add(indisponible);


	}

	/**
	 * Procédure qui ajoute et crée tout mes case à cocher
	 * Disponible et indisponible
	 */
	private void setCheckBox(){

		// Crée une case Disponible et set son actionCommand
		disponible  = new JRadioButton("disponible");
		disponible.setMnemonic(KeyEvent.VK_C);
		disponible.setActionCommand("disponible");

		// Crée une case Indisponible et set son ActionCommand
		indisponible = new JRadioButton("indisponible");
		indisponible.setMnemonic(KeyEvent.VK_D);
		indisponible.setActionCommand("indisponible");

		// Instancie mon ButtonGroup et lui ajout mes cases à cocher
		group = new ButtonGroup();
		group.add(disponible);
		group.add(indisponible);


	}

	/**
	 * Fonction qui retourne la disponiblité de l'infirmier
	 * (Boolean)
	 * @return
	 */
	public boolean getDisponibiliter() {

		// Retourne true ou false
		return (group.getSelection() == disponible.getModel());

	}

	/**
	 * Fonctions qui retourne un nouveau infirmier avec
	 * l'identification rentrer par l'utilisateur et ses disponibilité
	 */
	public Infirmier getParticipant() {

		// Retourne nouvelle instance d'infirmier
		return new Infirmier(super.getIdentification(), 
				this.getDisponibiliter());


	}

	/**
	 * Procédure qui reset le panneau de la classe mère
	 */
	public void reset() {

		super.reset();


	}

	/**
	 * Procédure qui avise de tout erreur lors de l'ajout 
	 * d'un infirmier. Elle retourne true si il y a une erreur
	 * sinon false
	 */
	public boolean aviserDuneErreur(){

		// Valeur boolean retourner
		boolean valeurRetour;


		// Vérifie si il y a une erreur dans les noms ou prenoms.
		if(super.aviserDuneErreur()) {
			

			valeurRetour = true;

		}else if(!disponible.isSelected() && !indisponible.isSelected()) {

			valeurRetour = true;

			appelMessageErreur("Sélectionner la disponibilité !");

		}else {

			valeurRetour = false;
		}

		// valeur retourner
		return valeurRetour;

	}

}