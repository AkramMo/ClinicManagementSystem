package gui;

import javax.swing.JLabel;
import javax.swing.JTextField;

import clinique.Patient;

/**
 * Classe qui gère le panneau de saisie des patient.
 * Elle hérite de la classe Panneau de SaisiParticipant
 * @author Akram Mousselmal
 * @version Copyright 2017
 *
 */
public class PanneauSaisiePatient extends PanneauSaisieParticipant {

	// TextField pour le numero d'assurance social
	private JTextField textNas;
	private JLabel nas;

	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur par défaut qui appel
	 * le constructeur par défaut de la classe mère et crée mon panneau
	 * en initialisant ses composantes
	 */
	public PanneauSaisiePatient() {

		// appel du constructeur de la classe mère
		super();

		// Procèdure qui intialise les composantes de mon panneau
		initComposante();

	}

	/**
	 * Procédure qui initialise les composantes de mon panneau
	 * de saisie de participant
	 */
	private void initComposante() {

		// Instancie le texte permettant d'identifier mon champs
		nas = new JLabel("NAS :");

		// Instancie mon textField d'assurance social
		textNas = new JTextField(9);

		// Ajoute les composantes précédente dans le panneau de saisi
		// patient spécifique.
		this.add(nas);
		this.add(textNas);



	}

	/**
	 * Fonction qui retourne le numeroe d'Assurance social.
	 * Retourne un int
	 * @return
	 */
	public int getNasPatient() {

		// Retourne la conversion de string vers int du numero d'Assurance
		// social
		return Integer.parseInt(textNas.getText());

	}

	/** 
	 * Retourne une nouvelle instance de patient avec son identifiant
	 * et son numero d'assurance social
	 */
	public Patient getParticipant() {

		return new Patient(super.getIdentification(), textNas.getText());


	}

	/**
	 * Vide toute les champs de formulaire. NAS et nom/prenom
	 */
	public void reset() {

		// Appel la procédure reset de la classe mère
		super.reset();

		// Vide le champ de NAS
		textNas.setText("");

	}

	/** 
	 * Fonction qui retourne true si il y a une erreur et affiche un 
	 * message d'erreur. Retourne false sinon.
	 */
	public boolean aviserDuneErreur(){

		// Valeur retour boolean
		boolean valeurRetour;
		

		// Verifie si il y a une erreur dans le panneau de saisi Participant
		if(super.aviserDuneErreur()) {

			// Affiche un message d'erreur
			valeurRetour = true;

			// Verifie si le NAS est assez long
		}else if(textNas.getText().length() != 9 ) {

			// affiche message d'erreur
			appelMessageErreur("Le numéro d'assurance social est"
					+ " trop court !");

			// Affiche message d'erreur
			valeurRetour = true;


			// Vérifie si le NAS est numeric et ne contient que des
			// caratère valide
		}else if(!isNumeric(textNas.getText())){


			// affiche message d'erreur !
			appelMessageErreur(" Le numéro d'Assurance social"
					+ " contient des caractères invalide !");

			valeurRetour = true;

		}else {

			valeurRetour = false;

		}

		// retourne true ou false
		return valeurRetour;

	}

	/**
	 * Fonction qui retourne true si le string est numérique
	 * sinon retourne false
	 * @param text
	 * @return
	 */
	private boolean isNumeric(String text){

		// Valeur retour true ou false
		boolean valeurRetour;

		// Variable char temporaire pour chaque caractère
		char temp;

		// Compteur pour la chaine de caracètere
		int i = 0;

		// Boucle qui arrête vérifie chaque caractère de ma chaine
		// String.
		do {

			// Prend le caractère à la position (i) du String
			temp = text.charAt(i);

			// Vérifie si le caractère est un Digit(Chiffre)
			if(Character.isDigit(temp)) {

				valeurRetour = true;

			}else {

				valeurRetour = false;
			}

			// incrément le compteur
			i++;

			// Arrête si on traverse toute le String ou qu'on trouve
			// un caractère invalide(lettre)
		}while( i<text.length() && valeurRetour != false);

		// Retourne true si numérique sinon False
		return valeurRetour;

	}



}
