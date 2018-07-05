package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Classe qui cr�e une barre de menu 
 * dans le coin sup�rieur gauche de mon interface
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

	// Menu d�roulant de ma classe
	private JMenu monMenu;


	/**
	 * Constructeur par copie d'attributs et qui
	 * re�oit un cadreClinique en param�tre
	 * @param cadreClinique
	 */
	public MonMenuBar(CadreClinique cadreClinique) {

		// Appel du constructeur m�re(JMenuBar())
		super();

		this.cadreClinique = cadreClinique;

		// Appel de la m�thode initComposante qui
		// cr�e ma bar de menu
		initComposante();
	}

	/**
	 * Proc�dure qui initialise toute les composantes de
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
	 * Fonctions qui ajoute un listener re�us en param�tre
	 * dans tout les JMenuItem re�us ne param�tre
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



		// Ajout des listener � chacun de mes menuItem
		menu_1.addActionListener(action);

		menu_2.addActionListener(action);

		menu_3.addActionListener(action);

		menu_4.addActionListener(action);

	}

	/**
	 * Fonctions qui ajout tout les menus re�ut en param�tre dans
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
	 * Classe Ecouteur qui impl�ment l'interface
	 * listener pour la gestions des �venement.
	 * Clique de bouton, etc. 
	 * @author akram
	 *
	 */
	private class Ecouteur implements ActionListener{

		// Frame actif o� a lieu les actions
		private CadreClinique JFrameActif;

		// Constructeur par copie d'attribut
		private Ecouteur(CadreClinique JFrameActif) {

			this.JFrameActif = JFrameActif;

		}

		/**
		 Proc�dure qui re�oit un event en param�tre
		 * et qui agit en cons�quence
		 */
		public void actionPerformed(ActionEvent e) {


			 String stringEvent = e.getActionCommand();

			/**
			 * Selon le menu choisi, un sous-programme 
			 * est appel� pour g�n�rer de nouvelles actions
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
