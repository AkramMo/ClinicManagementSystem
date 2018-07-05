package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Classe qui crée une barre de menu 
 * dans le coin supérieur gauche de mon interface
 * 
 * @author Akram Mousselmal
 * @version Copyright 2017
 *
 */
public class MonMenuBar extends JMenuBar{


	/**
	 * 
	 */
	private static final long serialVersionUID = 3267277845253073438L;

	// Jframe de ma clinique
	private CadreClinique cadreClinique;

	// Menu déroulant de ma classe
	private JMenu monMenu;


	/**
	 * Constructeur par copie d'attributs et qui
	 * reçoit un cadreClinique en paramètre
	 * @param cadreClinique
	 */
	public MonMenuBar(CadreClinique cadreClinique) {

		// Appel du constructeur mère(JMenuBar())
		super();

		this.cadreClinique = cadreClinique;

		// Appel de la méthode initComposante qui
		// crée ma bar de menu
		initComposante();
	}

	/**
	 * Procédure qui initialise toute les composantes de
	 * ma barre de menu en plus des listener et 
	 * de leurs disposition
	 */
	private void initComposante() {

		// Construire mon menu Gestion
		monMenu = new JMenu("Gestion");

		// Ajout monMenu mon cadreClinique
		super.add(monMenu);

		// Variable de Type Ecouteur pour mes boutons
		Ecouteur action = new Ecouteur(this.cadreClinique);

		// Instancie tout les Menu Item 
		JMenuItem docteurMenu = new JMenuItem("Docteur");

		JMenuItem infirmierMenu = new JMenuItem("Infirmier");

		JMenuItem patientMenu = new JMenuItem("Patient");

		JMenuItem quitterMenu = new JMenuItem("Quitter");

		// Fonctions qui ajoute tout mes listener aux menuItems.
		ajoutListener(action, docteurMenu, 
				infirmierMenu, patientMenu, quitterMenu);

		// Fonctions qui ajoute tout les menuItem au MenuPrincipal
		ajoutMenuItem(docteurMenu, infirmierMenu, patientMenu, quitterMenu);




	}

	/**
	 * Fonctions qui ajoute un listener reçus en paramètre
	 * dans tout les JMenuItem reçus ne paramètre
	 * @param action
	 * @param menu_1
	 * @param menu_2
	 * @param menu_3
	 * @param menu_4
	 */
	private void ajoutListener(ActionListener action, JMenuItem menu_1,
			JMenuItem menu_2, 

			JMenuItem menu_3 , 

			JMenuItem menu_4){



		// Ajout des listener à chacun de mes menuItem
		menu_1.addActionListener(action);

		menu_2.addActionListener(action);

		menu_3.addActionListener(action);

		menu_4.addActionListener(action);

	}

	/**
	 * Fonctions qui ajout tout les menus reçut en paramètre dans
	 * le menuPrincipal de la classe. 
	 * @param menu_1
	 * @param menu_2
	 * @param menu_3
	 * @param menu_4
	 */
	private void ajoutMenuItem(JMenuItem menu_1, JMenuItem menu_2, 

			JMenuItem menu_3 , 

			JMenuItem menu_4){

		// Ajoute tout mes Item de menu au menu Principale
		monMenu.add(menu_1);
		monMenu.add(menu_2);
		monMenu.add(menu_3);
		monMenu.add(menu_4);

	}

	/**
	 * Classe Ecouteur qui implément l'interface
	 * listener pour la gestions des évenement.
	 * Clique de bouton, etc. 
	 * @author akram
	 *
	 */
	private class Ecouteur implements ActionListener{

		// Frame actif où a lieu les actions
		private CadreClinique JFrameActif;

		// Constructeur par copie d'attribut
		private Ecouteur(CadreClinique JFrameActif) {

			this.JFrameActif = JFrameActif;

		}

		/**
		 Procédure qui reçoit un event en paramètre
		 * et qui agit en conséquence
		 */
		public void actionPerformed(ActionEvent e) {


			 String stringEvent = e.getActionCommand();

			/**
			 * Selon le menu choisi, un sous-programme 
			 * est appelé pour générer de nouvelles actions
			 * Sauf pour Quitter, qui ferme l'application
			 */
			if(stringEvent == "Docteur") {

				JFrameActif.gererDocteur();

			}else if(stringEvent == "Infirmier") {

				JFrameActif.gererInfirmier();

			}else if(stringEvent == "Patient") {

				JFrameActif.gererPatient();	

			}else if(stringEvent == "Quitter") {

				// Quitte l'application 
				System.exit(0);
			}
		}

	}


}
