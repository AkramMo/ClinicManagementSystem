package gui;

import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import clinique.Infirmier;

/**
 *Classe qui g�re le panneau de saisi
 *sp�cifique aux infirmi�res. Ce panneau de saisi
 * qui contient une checkbox pour dire si l'infirmier est disponible
 * H�rite de la classe PanneauSaisiParticipant
 * @author Akram Mousselmal
 * @version Copyright 2017
 *
 */
public class PanneauSaisieInfirmier extends PanneauSaisieParticipant {

	private static final long serialVersionUID = 1L;

	// Check box pour v�rifie si infirmier disponible ou pas
	private JRadioButton disponible;

	private JRadioButton indisponible;

	// BoutonGroup regroupant mes checkBox
	private ButtonGroup group;


	/**
	 * Constructeur par d�faut qui initialise mes composantes
	 * et appel constructeur de classe m�re
	 */
	public PanneauSaisieInfirmier() {

		// Constructeur de classe m�re
		super();

		// Proc�dure qui initialise toute mes composantes
		initComposante();

	}
	/**
	 * Proc�dure qui initialise tout mes composantes
	 * et qui set mes checkBox et leurs positions
	 */
	private void initComposante() {

		// Proc�dure qui cr�e mon groupButton
		setCheckBox();

		// Ajoute mes components dans la panneau de saisi sp�cifique aux
		// infirmier
		this.add(disponible);

		this.add(indisponible);


	}

	/**
	 * Proc�dure qui ajoute et cr�e tout mes case � cocher
	 * Disponible et indisponible
	 */
	private void setCheckBox(){

		// Cr�e une case Disponible et set son actionCommand
		disponible  = new JRadioButton("disponible");
		disponible.setMnemonic(KeyEvent.VK_C);
		disponible.setActionCommand("disponible");

		// Cr�e une case Indisponible et set son ActionCommand
		indisponible = new JRadioButton("indisponible");
		indisponible.setMnemonic(KeyEvent.VK_D);
		indisponible.setActionCommand("indisponible");

		// Instancie mon ButtonGroup et lui ajout mes cases � cocher
		group = new ButtonGroup();
		group.add(disponible);
		group.add(indisponible);


	}

	/**
	 * Fonction qui retourne la disponiblit� de l'infirmier
	 * (Boolean)
	 * @return
	 */
	public boolean getDisponibiliter() {

		// Retourne true ou false
		return (group.getSelection() == disponible.getModel());

	}

	/**
	 * Fonctions qui retourne un nouveau infirmier avec
	 * l'identification rentrer par l'utilisateur et ses disponibilit�
	 */
	public Infirmier getParticipant() {

		// Retourne nouvelle instance d'infirmier
		return new Infirmier(super.getIdentification(), 
				this.getDisponibiliter());


	}

	/**
	 * Proc�dure qui reset le panneau de la classe m�re
	 */
	public void reset() {

		super.reset();


	}

	/**
	 * Proc�dure qui avise de tout erreur lors de l'ajout 
	 * d'un infirmier. Elle retourne true si il y a une erreur
	 * sinon false
	 */
	public boolean aviserDuneErreur(){

		// Valeur boolean retourner
		boolean valeurRetour;


		// V�rifie si il y a une erreur dans les noms ou prenoms.
		if(super.aviserDuneErreur()) {
			

			valeurRetour = true;

		}else if(!disponible.isSelected() && !indisponible.isSelected()) {

			valeurRetour = true;

			appelMessageErreur("S�lectionner la disponibilit� !");

		}else {

			valeurRetour = false;
		}

		// valeur retourner
		return valeurRetour;

	}

}