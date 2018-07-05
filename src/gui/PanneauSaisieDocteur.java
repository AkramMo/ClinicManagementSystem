package gui;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import clinique.Docteur;

/**
 *Classe qui g�re le panneau de saisi
 *sp�cifique aux docteurs. Ce panneau de saisi
 *contient une liste d�roulante contenant tout les d�partements des
 *docteurs
 * 
 * @author Akram Mousselmal
 * @version Copyright 2017
 *
 */
public class PanneauSaisieDocteur extends PanneauSaisieParticipant {

	// Listed�roulante qui contient une liste de type JList
	private JScrollPane listeDeroulante;

	// Liste qui contient mes d�partements
	private JList<String> liste;

	//Tableau de chaine de caract�re qui contient tout les departemnents
	private String[] tableauDepartement;


	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur par copie d'attributs qui re�oit un tableau
	 * de String contenant les 3 d�partements
	 * @param tableauDepartement
	 */
	public PanneauSaisieDocteur(String[] tableauDepartement) {

		// Constructeur de la classe m�re(PanneauSaisiParticipant)
		super();

		// Prend la r�f�rence du tableau re�ut en param�tre
		this.tableauDepartement = tableauDepartement;

		// Proc�dure qui initialise les composantes de mes listes et panneau
		initComposanteDocteur();
	}

	/**
	 * Proc�dure qui cr�e ma liste d�roulante et remplie ma liste
	 * JList. Ajout cette liste au panneau sp�cifique aux docteur
	 * H�rite de la classe PanneauSaisiParticipant
	 */
	private void initComposanteDocteur() {

		// Instancie un model de liste
		DefaultListModel<String> listModel = new DefaultListModel<String>();

		// Remplir la liste String des noms de d�partement 
		// du tableau(attributs)
		remplirListModel(listModel);


		// Cr�e ma liste JListe avec le listModel remplie pr�c�demment
		liste = new JList<String>(listModel);


		// Set un Mode de selection unique et non pas multiple
		liste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Instancie ma liste d�roulante
		listeDeroulante = new JScrollPane(liste);

		// Ajoute cette liste d�roulante � la classe m�re
		super.add(listeDeroulante);

	}

	/**
	 * Proc�dure qui re�oit une listModel de type DefaultListModel
	 * et la remplie avec le tableau de d�partement en attribut de 
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
	 * Fonction qui retourne le num�roe de d�partement
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
	 * Proc�dure qui clear la liste et les panneaux de saisie
	 */
	public void reset() {

		// Utilise la proc�dure reset de la classe m�re
		super.reset();

		liste.clearSelection();

	}

	/**
	 * Proc�dure qui retourne true si il y a une erreur
	 * de saisie ou d'identifiant invalide
	 */
	public boolean aviserDuneErreur(){

		// valeur retour
		boolean valeurRetour;

		// Numero de departement
		int departement = liste.getSelectedIndex();
		
		// Condition pour v�rifie si il y a erreur dans le PSaisiParticipant
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
