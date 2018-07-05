package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

//import com.sun.xml.internal.bind.v2.util.TypeCast;

import utilitaire.UtilitaireFichier;
import clinique.Clinique;
import clinique.Participant;

/**
 * Classe qui montre le cadre de gestion des 
 * participant de la clinique. Patient,docteur et 
 * infirmier
 * 
 * @author Akram Mousselmal
 * @version	Copyright 2017
 *
 */
public class CadreGestionParticipant extends JDialog {

	private static final long             serialVersionUID = 1L;

	// Position en X,Y de la fenêtre
	private final int POSITION_X = 500;
	private final int POSITION_Y = 500;

	// Dimension du cadre de Gestion
	private final int LARGEUR = 400;
	private final int HAUTEUR= 400;


	// Clinique à gérer
	private Clinique clinique;

	// La liste de participant affichée
	private List<Participant> listeParticipant;

	// L'interface de panneau de saisie 
	private PanneauSaisieParticipant panneauSaisie;

	// Liste des panneau de la fenêtre de gestion
	private JPanel panneauPrincipal;

	//Portion basse de la fenêtre
	private JPanel panneauBas;

	// Panneau contenant les boutons Ajouter/supprimer
	private JPanel panneauGestion;

	// Liste déroulante contenant les participants
	private JScrollPane listeDeroulante;

	// Table contenant la liste des participants
	private JTable listeTable;

	private BoutonSaisie boutonSaisie;
	
	private String category = "";


	/**
	 * Constructeur par copie d'attributs qui initialise
	 * mes attributs avec ceux reçus en paramètre et initialise.
	 * 
	 * Intialise la modalité de mon cadre et son
	 * comportement en plus de ses composantes.
	 * @param clinique
	 * @param panneauSaisie
	 * @param listeParticipant
	 * @param position
	 * @param dimCadre
	 */
	public CadreGestionParticipant(Clinique clinique, 
			InterfacePanSaisieParticipant panneauSaisie,
			List<Participant> listeParticipant, 
			Point position, 
			Dimension dimCadre, String category)
	{	

		// Constructeur de Jdialog
		super();

		// Initialise les attributs 
		this.clinique = clinique;
		this.panneauSaisie = (PanneauSaisieParticipant)panneauSaisie;
		this.listeParticipant = listeParticipant;
		this.category = category;

		// Set la position de la fenêtre
		this.setLocation(position);

		// Set les dimension de la fenêtre.
		this.setSize(dimCadre);

		// Comportement de la fenêtre quand on appuie sur X
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// Méthode  pour initialiser mes composantes
		initComposante();
	}


	/**
	 * Procédure qui initialise toute les composantes du cadre de gestion
	 * Button, panneau gestion, liste deroulante, position
	 * et dimension. Elle adapte les panneau selon si elle a
	 * une liste vide ou non
	 */
	void initComposante(){	


		//Crée une tableau de Donnée de la liste de participant
		listeTable =  UtilitaireSwing.obtenirListe_A_Afficher(

				listeParticipant.toArray()

				);

		//Crée une liste déroulante à l'aide de la listeTable
		listeDeroulante = new JScrollPane(listeTable);

		//Créer les panneau intermédiaire
		panneauBas = new JPanel();

		panneauGestion = new JPanel();


		// Créer panneau principal avec le frame "ContentPane"
		panneauPrincipal = (JPanel)super.getContentPane();


		// Crée mon ecouteur
		Ecouteur action = new Ecouteur();


		// Crée mes deux boutons Ajout/delete
		JButton boutonSupprimer = new JButton("Supprimer");

		JButton boutonAjouter = new JButton("Ajouter");


		// Appel procédure qui ajoute mes ActionListner aux boutons
		createAndAddListener(boutonSupprimer, boutonAjouter, action);

		// Méthode qui ajoute tout les panel dans le panel principal
		ajoutPanneau();

		// Ajout des boutons dans mon panneau
		panneauGestion.add(boutonAjouter);

		panneauGestion.add(boutonSupprimer);

		// Ajout du panneau quand le cadre est en mode Saisie
		ajoutPanneauSaisie();



		//Change le panneau selon si la liste est vide ou non.
		verifierListe();

		// Set les dimensions de la fenêtre(Frame)
		super.setBounds(new Rectangle(POSITION_X, POSITION_Y, 

				// Largeur et hauteur de la fenêtre
				LARGEUR, 

				HAUTEUR));

		// Cette méthode set la modalité de 
		// ma fenêtre(Empêche d'accèder aux fenêtre d'arrière plan).
		super.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);

		// Afficher la fenêtre
		super.setVisible(true);

	}



	/**
	 * Procédure qui ajout tout les panneau dans le 
	 * panneau principal et set les layouts
	 */
	private void ajoutPanneau(){


		// Ajout d'un panneau dans un autre et changement du Layout
		panneauBas.add(panneauGestion);
		panneauBas.setLayout(new CardLayout());

		// Ajout de la liste déroulante et du panneau intermédiaire
		panneauPrincipal.add(listeDeroulante);
		panneauPrincipal.add(panneauSaisie, BorderLayout.PAGE_START);
		panneauPrincipal.add(panneauBas, BorderLayout.PAGE_END);

	}


	/**
	 * Méthode qui instancie mon attributs boutonSaisie
	 * et ajout ce panel dans mon panel panneauBas;
	 */
	private void ajoutPanneauSaisie(){

		boutonSaisie = new BoutonSaisie();

		//Ecouteur à ajouter aux bouton de BoutonSaisie
		Ecouteur action = new Ecouteur();

		boutonSaisie.boutonOk.addActionListener(action);
		boutonSaisie.boutonAnnuler.addActionListener(action);

		panneauBas.add(boutonSaisie);



	}

	/**
	 * Vérifie si la liste de la clinique est vide.
	 * Si c'est le cas, elle rend invisible certains panneau
	 * et d'autre visible.
	 */
	private void verifierListe(){

		// Vérifie si la liste des participants est vide
		if(listeParticipant.isEmpty()) {

			// Rend visible panneau saisi et invisible liste deroulante
			panneauSaisie.setVisible(true);

			listeDeroulante.setVisible(false);

			// Affiche le dernier panneau ajouter dans le panel PanneauBas
			((CardLayout) panneauBas.getLayout()).last(panneauBas);

		}else {

			// rend visible la liste et invisisble le panneauSaisie
			listeDeroulante.setVisible(true);

			panneauSaisie.setVisible(false);

			// Affiche le premier panneau ajouter dans le panel PanneauBas
			((CardLayout) panneauBas.getLayout()).first(panneauBas);

		}


		// Méthode qui rafraichi le panneau et ses composantes
		UtilitaireSwing.rafraichirCadre(panneauBas);

	}



	/**
	 * Classe Ecouteur qui implément l'interface
	 * listener pour la gestions des évenement.
	 * Clique de bouton, etc. 
	 * @author akram
	 *
	 */
	private class Ecouteur implements ActionListener{



		//Constructeur par copie d'objet
		public Ecouteur() {


		}

		// Interface de ActionListener 
		public void actionPerformed(ActionEvent e){

			String stringEvent = e.getActionCommand();

			// Vérifie le bouton cliqer
			// et appel un sous-programme
			// en conséquence
			if(stringEvent == "Supprimer" ) {

				supprimerLesSelections();

			}else if(stringEvent == "Ajouter") {

				passerModeAjout();

			}else if(stringEvent == "Annuler") {

				annulerAjout();

			}else if(stringEvent == "Ok") {

				ajouterSiValide();
			}
		}


	}


	/**
	 * Procédure qui ajoute le participant à la liste
	 * et ça seulement le participant est valide.
	 */
	private void ajouterSiValide() {

		// Vérifie si il y a une erreur dans le panneau
		//  de saisie (Docteur,patient,etc.)
		if(!panneauSaisie.aviserDuneErreur()) {

			// Participant temporaire
			Participant partTmp = panneauSaisie.getParticipant();

			// Vérifie si le participant 
			// n'est pas déjà présent dans la liste
			if(listeParticipant.contains(partTmp)) {


				// affiche un message d'erreur
				panneauSaisie.appelMessageErreur("Le participant existe déjà !");


			}else {


				// Retourne la categorie de la liste
				String categoryParticipant = retourneCategorie();

				// Nom du fichier clinique.bin
				String nomFic = Clinique.NOM_FIC;

				// Ajoute selon la categorie (Docteur/infirmier,etc)
				addByCategory(categoryParticipant, partTmp);


				// Sauvegarde de la clinique
				UtilitaireFichier.sauvegarderClinique(clinique, nomFic);

				// Supprime la table et en crée une nouvelle
				listeDeroulante.remove(listeTable);
				listeTable =  UtilitaireSwing.obtenirListe_A_Afficher(

						listeParticipant.toArray()

						);

				// Ajoute la nouvelle table à la liste déroulante
				listeDeroulante.setViewportView(listeTable);

				// Vide les champ du panneau
				panneauSaisie.reset();
				// Méthode qui rafraichi le panneau et ses composantes
				UtilitaireSwing.rafraichirCadre(listeDeroulante);

				// Message qui confirme l'ajout
				panneauSaisie.appelMessageReussite("Le participant a été ajouté avec succès !");

			}

		}
	}

	/**
	 * Méthode qui reçoit la categorie du participant
	 * et le participant à ajouter dans la list de participant
	 * de la clinique
	 * @param categoryParticipant
	 * @param partTmp
	 */
	private void addByCategory(String categoryParticipant, Participant partTmp){

		// Vérifie quel est la categorie
		if(categoryParticipant == "docteur" ){

			// Crée une liste temporaire
			List<Participant> listeTmp = new
					ArrayList<Participant>(clinique.getDocteurs());

			//  supprimer directement de la liste de la clinique
			listeTmp.add(partTmp);


			// Ajoute cette liste temporaire à la clinique
			clinique.setDocteur(listeTmp);

			// Modifie la liste des participants(à jour)
			listeParticipant = clinique.getDocteurs();


		}else if( categoryParticipant == "infirmier"){

			// Même opération que plus haut, mais pour 
			// un infirmier
			List<Participant> listeTmp = new
					ArrayList<Participant>(clinique.getInfirmiers());

			//  supprimer directement de la liste de la clinique
			listeTmp.add(partTmp);


			clinique.setInfirmier(listeTmp);

			listeParticipant = clinique.getInfirmiers();

		}else{


			// Même opération que plus haut, mais pour 
			// un patient
			List<Participant> listeTmp = new 
					ArrayList<Participant>(clinique.getPatients());

			//  supprimer directement de la liste de la clinique
			listeTmp.add(partTmp);


			clinique.setPatient(listeTmp);

			listeParticipant = clinique.getPatients();

		}

	}


	/**
	 * Procédure qui change l'Affichage du panneau 
	 * princiapl quand on appuie sur le bouton annuler.
	 */
	private void annulerAjout() {

		// Cache le panneau de saisie et affiche la lsite
		// déroulante
		panneauSaisie.setVisible(false);
		listeDeroulante.setVisible(true);

		// Affiche le premier panneau ajouter dans le panel 
		// PanneauBas(Ajouter/supprimer)
		((CardLayout) panneauBas.getLayout()).first(panneauBas);

		// Méthode qui rafraichi le panneau et ses composantes
		UtilitaireSwing.rafraichirCadre(panneauBas);
	}


	/**
	 * Procédure qui change d'Affichage et
	 * passe en mode ajout
	 */
	private void passerModeAjout() {

		// Cache la liste déroulange
		listeDeroulante.setVisible(false);

		// Affiche le panneau de saisie
		panneauSaisie.setVisible(true);

		// Affiche le dernier panneau ajouter dans le panel PanneauBas
		// (PanneauBas(Ok/Annuler)
		((CardLayout) panneauBas.getLayout()).last(panneauBas);

		// Rafraichie le panneau principal ainsi que ses composantes
		UtilitaireSwing.rafraichirCadre(listeDeroulante);

	}


	/**
	 * Procèdure qui supprime les participants sélectionner
	 * dans une liste déroulante
	 */
	private void supprimerLesSelections() {

		// Obtient les indices de tout les participants sélectionner
		int[] indexArray = listeTable.getSelectedRows();

		// Méthode qui supprime les participants ayant l'index
		// présent dans le tableau donner en paramètre
		deletePartWithIndex(indexArray);


		// Message qui confirme l'ajout
		panneauSaisie.appelMessageReussite("La suppression a été "
				+ "réussie avec succès !");

	}


	/**
	 * Procédure qui reçoit un tableau de INT en paramètre
	 * et qui supprime les participants selon leur index
	 * @param indexArray
	 */
	private void deletePartWithIndex(int[] indexArray) {

		// Obtient le nombre de participants
		int nbrParticipants = indexArray.length;

		// nom de fichier de clinique.bin
		String nomFic = Clinique.NOM_FIC;

		// Boucle qui passe à travers chaque participants de la fin
		// pour éviter d'influencer la prochaine suppression
		for(int i = nbrParticipants-1; i>-1 ; i--) {

			// Obtenir la categorie à supprimée
			String categorieParticipant = retourneCategorie();

			// Supprime selon la categorie obtenue
			deleteByCategory(categorieParticipant, indexArray, i);

			// Méthode qui enlève les données du participants
			((DefaultTableModel) listeTable.getModel()).
			getDataVector().remove(indexArray[i]);

			// Vérifie si la liste est vide et 
			// change l'Affichage en conséquence
			verifierListe();



			UtilitaireFichier.sauvegarderClinique(clinique, nomFic);

			// Méthode qui rafraichi le panneau et ses composantes
			UtilitaireSwing.rafraichirCadre(listeDeroulante);



		}

	}


	/**
	 * Méthode qui reçoit la catégorie du participant à
	 * supprimé, la liste des indices de ces participants
	 * et le compteur i
	 * @param categorieParticipant
	 * @param indexArray
	 * @param i
	 */
	private void deleteByCategory(String categorieParticipant,
			int[] indexArray, int i){

		// Vérifie la catégorie du participant
		if(categorieParticipant  == "docteur"){


			// supprime le docteur 
			deleteDocteur(indexArray, i);


		}else if(categorieParticipant == "infirmier"){

			// supprime l'infirmier
			deleteInfirmier(indexArray, i);

		}else {

			// supprime le patient
			deletePatient(indexArray, i);

		}
	}

	/**
	 * Méthode qui reçoit le tableau avec
	 * les indices des participant à supprimé
	 * dans la liste et l'indice de ce même tableau
	 * i.
	 * @param indexArray
	 * @param i
	 */
	private void deleteDocteur(int[] indexArray, int i) {

		// crée une liste temporaire 
		// identique à celle de la clinique
		List<Participant> listeTmp = new 
				ArrayList<Participant>(clinique.getDocteurs());

		//  supprimer directement de la liste de la clinique
		listeTmp.remove(listeParticipant.get(indexArray[i]));

		// ajoute la nouvelle liste à la clinique
		clinique.setDocteur(listeTmp);

		// met à jour la liste de participant
		listeParticipant = clinique.getDocteurs();

	}


	/**
	 * Méthode qui reçoit le tableau avec
	 * les indices des participant à supprimé
	 * dans la liste et l'indice de ce même tableau
	 * i.
	 * @param indexArray
	 * @param i
	 */
	private void deleteInfirmier(int[] indexArray, int i) {


		// Effectue les mêmes opération que plus haut
		// pour un infirmier
		List<Participant> listeTmp = new 
				ArrayList<Participant>(clinique.getInfirmiers());

		//  supprimer directement de la liste de la clinique
		listeTmp.remove(listeParticipant.get(indexArray[i]));

		clinique.setInfirmier(listeTmp);

		listeParticipant = clinique.getInfirmiers();

	}

	/**
	 * Méthode qui reçoit le tableau avec
	 * les indices des participant à supprimé
	 * dans la liste et l'indice de ce même tableau
	 * i.
	 * @param indexArray
	 * @param i
	 */
	private void deletePatient(int[] indexArray, int i) {

		// Effectue les mêmes opération que plus haut
		// pour un patient
		List<Participant> listeTmp = new 
				ArrayList<Participant>(clinique.getPatients());

		//  supprimer directement de la liste de la clinique
		listeTmp.remove(listeParticipant.get(indexArray[i]));

		clinique.setPatient(listeTmp);

		listeParticipant = clinique.getPatients();

	}
	
	/**
	 * Retourne la catégorie de l'instance
	 * du cadre de gestion Participant.
	 * Docteur/infirmier ou partient.
	 * @return
	 */
	private String retourneCategorie(){

		return this.category;
		
	}


	/**
	 * Procédure privée qui ajoute mes listener à mes boutons
	 * @param boutonSupprimer
	 * @param boutonAjouter
	 * @param action
	 */
	private void createAndAddListener(JButton boutonSupprimer, 
			JButton boutonAjouter, Ecouteur action){

		// ajout des listnener dans chaque bouton
		boutonAjouter.addActionListener(action);

		boutonSupprimer.addActionListener(action);


	}


	/**
	 * Class qui hérite de la classe JPanel
	 * Contient un panneau et des boutons pour
	 * la saisie
	 * @author AkramMo
	 *
	 */
	private class BoutonSaisie extends JPanel{

		
		/**
		 * 
		 */
		private static final long serialVersionUID = -8763651601422772346L;
		
		// Attributs des boutons présents sur le panneau
		private JButton boutonAnnuler;
		private JButton boutonOk;

		/**
		 * Constructeur par défaut 
		 */
		public BoutonSaisie() {

			// Appel du constructeur de JPanel
			super();

			// Crée mes deux boutons Ok/Annule
			boutonAnnuler = new JButton("Annuler");

			boutonOk = new JButton("Ok");

			// Ajoutes ces deux boutons  au panneau
			this.add(boutonOk);
			this.add(boutonAnnuler);
		}


	}
}
