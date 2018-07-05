package gui;



import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import clinique.Identification;
import clinique.Participant;

/**
 *Classe qui gère le panneau de saisi
 *spécifique aux Participant. Ce panneau de saisi
 * qui contient 2 formulaires pour le nom et le prénom
 * Elle implémente l'interface InterfacePanSaisieParticipant
 * Et qui hérifie d'un JPanel
 * @author Akram Mousselmal
 * @version Copyright 2017
 *
 */
public class PanneauSaisieParticipant extends JPanel 
implements InterfacePanSaisieParticipant {

	private static final long serialVersionUID = 1L;

	// Deux panneau contenant les 2 formulaires pour nom et prenom
	private JPanel panneauNom;
	private JPanel panneauPrenom;

	// Les deux champs de formulaire
	private JTextField textNom;
	private JTextField textPrenom;




	/**
	 * Constructeur par défaut du panneau. Appel
	 * le constructeur de la classe mère JPanel et set 
	 * les composantes et le layout
	 */
	public PanneauSaisieParticipant() {

		// Constructeur par défaut de la classe mère JPanel
		super();

		// Crée une instance BoxLayout
		BoxLayout boxlayout = new BoxLayout(this, BoxLayout.PAGE_AXIS);


		// Ajout d'un layout au panneau de Saisi Participant
		this.setLayout(boxlayout);

		// Procédure qui initialise les composantes des participants
		initComposanteParticipant();

	}

	/**
	 * Procédure qui initialiste les composantes des participants
	 * Ajuste et ajoute les panneau de nom et prenom au panneau
	 * de participants
	 */
	private void initComposanteParticipant() {

		// Crée un text pour identifier les formulaires Nom/Prenom
		JLabel nom = new JLabel("Nom :");
		JLabel prenom = new JLabel("Prénom :");

		// Procédure qui ajoute des champs de formulaire à mes panneaux
		createTextFieldAndPanel();

		// Ajout le tout au panneau respectif Nom et Prenom
		panneauNom.add(nom);
		panneauPrenom.add(prenom);
		panneauNom.add(textNom);
		panneauPrenom.add(textPrenom);

		// Ajoute les panneaux au panneau hériter par la classe mère.
		this.add(panneauNom);
		this.add(panneauPrenom);


	}

	/**
	 * Procèdure qui crée mes champs et instancie
	 * mes panneauxs
	 */
	private void createTextFieldAndPanel(){

		// Instancie les champs et les panneau
		textNom = new JTextField(20);
		textPrenom = new JTextField(20);   	
		panneauNom = new  JPanel();
		panneauPrenom = new JPanel();

	}

	/**
	 * Fonction qui retourne une nouvelle instance
	 * d'identification avec celle reçut par les textField
	 * @return
	 */
	public Identification getIdentification() {

		return new Identification(textNom.getText(), textPrenom.getText());

	}

	/**
	 * Fonctions qui retourne une nouvelle instance de la classe
	 * Participant avec une nouvelle Identification
	 */
	@Override
	public Participant getParticipant() {

		// Crée une nouvelle instance de Participant avec la méthode
		// get Identification et la retourne
		return new Participant(this.getIdentification());

	}

	/**
	 * Procédure qui avise d'une erreur de saisie
	 * et affiche un message d'erreur si c'est le cas et
	 * retourne true. False si aucune erreur
	 */
	@Override
	public boolean aviserDuneErreur() {

		// Valeur boolean retour
		boolean valeurRetour;

		// Vérifie si un des champs est vides
		if(textNom.getText().isEmpty() || textPrenom.getText().isEmpty()) {



			appelMessageErreur("L'un des champs nom ou prénom"
					+ " est invalide !");

			valeurRetour = true;

		}else {

			valeurRetour = false;

		}

		// Retourne true ou false
		return valeurRetour;
	}

	/**
	 * Procédure qui vide les champs de formulaires
	 * Nom et prenom
	 */
	@Override
	public void reset() {

		textNom.setText("");
		textPrenom.setText("");
	}


	/**
	 * Méthode qui reçoit un string en
	 * paramètre et qui l'Affiche
	 * en tant que message d'erreur
	 * @param message
	 */
	public void appelMessageErreur(String message){


		// Pris sur https://openclassrooms.com/
		JOptionPane.showMessageDialog(null, message,
				"Message d'erreur", JOptionPane.ERROR_MESSAGE);

	}

	/**
	 * Méthode qui reçoit un string en
	 * paramètre et qui l'Affiche
	 * en tant que message de réussite
	 * @param message
	 */
	public void appelMessageReussite(String message){

		// Pris sur https://openclassrooms.com/
		JOptionPane.showMessageDialog(null, message,
				"Message d'erreur", JOptionPane.INFORMATION_MESSAGE);

	}
}
