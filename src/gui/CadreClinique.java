package gui;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import clinique.Clinique;
import clinique.Participant;
import utilitaire.UtilitaireFichier;

/**
 * Classe qui montre le cadre principal de l'application de 
 * clinique médicale.
 * 
 * Dans le cadre du cours inf111, travail pratique numéro 3
 * (voir énoncé INF111 A17 tp3 partie 2 fourni).
 * 
 * @author Pierre Bélisle
 * @version Copyright A2017
 */
public class CadreClinique extends JFrame implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3449731348049242609L;
	
	/**
	 * CONSTANTES 
	 */
	// La taille du cadre a été choisie arbitrairement.
	public static final int LARGEUR = 1000;
	public static final int HAUTEUR = 400;

	// Pour fixer la taille, il faut une instance de la classe Dimension.
	public static final Dimension TAILLE_CADRE = 
			new Dimension(LARGEUR,HAUTEUR);

	// Pour centrer le cadre, on obtient la taille de l'écran par le Toolkit.
	public static final Dimension TAILLE_ECRAN = 
			Toolkit.getDefaultToolkit().getScreenSize();	

	// La taille du cadre pour la gestion des participants.
	public static final int LARGEUR_CADRE_GESTION = 500;
	public static final int HAUTEUR_CADRE_GESTION = 400;

	// Le centre à partir de la taille du cadre.
	public final int CENTRE_X = 
			(TAILLE_ECRAN.width - LARGEUR_CADRE_GESTION) / 2;

	public final int CENTRE_Y = 
			(TAILLE_ECRAN.height -HAUTEUR_CADRE_GESTION) / 2;

	// On calcule la position au centre.
	private final Point POINT = new Point(CENTRE_X, CENTRE_Y);

	private final Dimension DIMENSION_CADRE = 
			new Dimension(LARGEUR_CADRE_GESTION, 
					HAUTEUR_CADRE_GESTION);

	// La seule clinique de rendez-vous de l'application 
	// sera instanciée au constructeur.
	private Clinique clinique;



	/**
	 *	On tente d'obtenir le fichier clinique par défaut (Clinique.NOM_FIC).
	 *  S'il n'existe pas, on instancie une clinique neuve.   
	 *  
	 *  Pour l'instant, il n'y a qu'une seule clinique dans tout le projet. 
	 */
	public CadreClinique(){

		try{

			File fic = new File(Clinique.NOM_FIC);

			clinique = 
					UtilitaireFichier.obtenirClinique(
							new FileInputStream (fic));
		}
		catch(Exception e){
			clinique = new Clinique();
		}
	}

	/**
	 * Initialise les composants, et la configuration
	 * de base du cadre au monent décidé par Thread.start().
	 */
	public void run(){

		// On obtient le panneau du JFrame.
		JPanel panneau = (JPanel) getContentPane();

		// Dimension plein écran du  CadreClinique (this).
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		// On ferme sur X.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// On ajoute le panneau pour les date.
		PanneauSelecteurDate panneauSelecteurDate = 
				new PanneauSelecteurDate("Rendez-vous clinique", clinique);

		// Le menu que les étudiants doivent écrire.
		MonMenuBar menuBar = new MonMenuBar(this);
		setJMenuBar(menuBar);

		// La grille qui présente le calendrier (dateChooser).
		panneau.add(panneauSelecteurDate);

		//On montre la fenêtre
		setVisible(true);		
	}

	/**
	 * Permet de démarrer la fenêtre de dialogue pour la
	 * gestion des docteurs.
	 */
	public void gererDocteur() {

		// Méthode offerte par Pierre Belisle pour prendre transférer un documents .txt dans une liste.
		List<Participant> listeDocteurs = clinique.getDocteurs();

		if(listeDocteurs.size() == 0){

			listeDocteurs = UtilitaireFichier.getParticipantsFichierTexte("noms.txt", "docteur");

			clinique.setDocteur(listeDocteurs);

		}

		// Les paramètres sont sur plusieurs lignes. 
		new CadreGestionParticipant(clinique,

				// Le panneau qui saisit les infos d'un docteur.
				new PanneauSaisieDocteur(clinique.getDepartements()),

				// La liste des docteurs.
				listeDocteurs,

				// La position et la dimension.
				POINT,
				DIMENSION_CADRE, "docteur");


	}

	/**
	 * Permet de démarrer la fenêtre de dialogue pour la
	 * gestion des informiers.
	 */
	public void gererInfirmier() {


		// Méthode offerte par Pierre Belisle pour prendre transférer un documents .txt dans une liste.
		List<Participant> listeInfirmier = clinique.getInfirmiers();

		if(listeInfirmier.size() == 0){

			listeInfirmier = UtilitaireFichier.getParticipantsFichierTexte("nomsInfirmier.txt", "infirmier");

			clinique.setInfirmier(listeInfirmier);

		}

		// Les paramètres sont sur plusieurs lignes. 
		new CadreGestionParticipant(clinique,

				// Le panneau qui saisit les infos d'un infirmier.
				new PanneauSaisieInfirmier(),

				// La liste des infirmiers.
				listeInfirmier,

				// La position et la dimension.
				POINT,
				DIMENSION_CADRE, "infirmier");


	}

	/**
	 * Permet de démarrer la fenêtre de dialogue pour la
	 * gestion des patients.
	 */
	public void gererPatient() {


		// Méthode offerte par Pierre Belisle pour prendre transférer un documents .txt dans une liste.
		List<Participant> listePatient = clinique.getPatients();

		if(listePatient.size() == 0){

			listePatient= UtilitaireFichier.getParticipantsFichierTexte("nomsPatient.txt", "patient");

			clinique.setPatient(listePatient);

		}
		// Les paramètres sont sur plusieurs lignes. 
		new CadreGestionParticipant(clinique,

				// Le panneau qui saisit les infos d'un patient.
				new PanneauSaisiePatient(), 

				// La liste de patients.
				listePatient,

				// La position et la dimension.
				POINT,
				DIMENSION_CADRE, "patient");

	}
}