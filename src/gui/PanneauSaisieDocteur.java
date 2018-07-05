package gui;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import clinique.Docteur;

/**
 *Classe qui gère le panneau de saisi
 *spécifique aux docteurs. Ce panneau de saisi
 *contient une liste déroulante contenant tout les départements des
 *docteurs
 * 
 * @author Akram Mousselmal
 * @version Copyright 2017
 *
 */
public class PanneauSaisieDocteur extends PanneauSaisieParticipant {

	// Listedéroulante qui contient une liste de type JList
	private JScrollPane listeDeroulante;

	// Liste qui contient mes départements
	private JList<String> liste;

	//Tableau de chaine de caractère qui contient tout les departemnents
	private String[] tableauDepartement;


	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur par copie d'attributs qui reçoit un tableau
	 * de String contenant les 3 départements
	 * @param tableauDepartement
	 */
	public PanneauSaisieDocteur(String[] tableauDepartement) {

		// Constructeur de la classe mère(PanneauSaisiParticipant)
		super();

		// Prend la référence du tableau reçut en paramètre
		this.tableauDepartement = tableauDepartement;

		// Procédure qui initialise les composantes de mes listes et panneau
		initComposanteDocteur();
	}

	/**
	 * Procédure qui crée ma liste déroulante et remplie ma liste
	 * JList. Ajout cette liste au panneau spécifique aux docteur
	 * Hérite de la classe PanneauSaisiParticipant
	 */
	private void initComposanteDocteur() {

		// Instancie un model de liste
		DefaultListModel<String> listModel = new DefaultListModel<String>();

		// Remplir la liste String des noms de département 
		// du tableau(attributs)
		remplirListModel(listModel);


		// Crée ma liste JListe avec le listModel remplie précédemment
		liste = new JList<String>(listModel);


		// Set un Mode de selection unique et non pas multiple
		liste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Instancie ma liste déroulante
		listeDeroulante = new JScrollPane(liste);

		// Ajoute cette liste déroulante à la classe mère
		super.add(listeDeroulante);

	}

	/**
	 * Procédure qui reçoit une listModel de type DefaultListModel
	 * et la remplie avec le tableau de département en attribut de 
	 * classe
	 * @param listModel
	 */
	private void remplirListModel(DefaultListModel<String> listModel){

		// Remplir la list avec les trois premiers string du tableau 
		// de string
		listModel.addElement(this.tableauDepartement[0]);

		listModel.addElement(this.tableauDepartement[1]);

		listModel.addElement(this.tableauDepartement[2]);

	}

	/**
	 * Fonction qui retourne le numéroe de département
	 * @return
	 */
	public int getNumDepartement() {

		return liste.getSelectedIndex();

	}

	/**
	 * Accesseur qui retourne un nouveau docteur 
	 */
	public Docteur getParticipant() {

		return new Docteur(super.getIdentification(), 
				liste.getSelectedIndex());

	}

	/**
	 * Procèdure qui clear la liste et les panneaux de saisie
	 */
	public void reset() {

		// Utilise la procédure reset de la classe mère
		super.reset();

		liste.clearSelection();

	}

	/**
	 * Procédure qui retourne true si il y a une erreur
	 * de saisie ou d'identifiant invalide
	 */
	public boolean aviserDuneErreur(){

		// valeur retour
		boolean valeurRetour;

		// Numero de departement
		int departement = liste.getSelectedIndex();
		
		// Condition pour vérifie si il y a erreur dans le PSaisiParticipant
		if(super.aviserDuneErreur()) {


			// Affiche un message d'erreur
			valeurRetour = true;



			// Verifie si departement est valide
		}else if(!(departement == 0 || departement == 1 || 
				departement == 2 )) {


			// Affiche message d'erreur si mauvais departement choisie
			valeurRetour = true;

			// message d'erreur 
			appelMessageErreur("Aucun departement choisi "
					+ "n'est valide !");



		}else {

			valeurRetour = false;
		}

		// Retourn true ou false 
		return valeurRetour;

	}

}
