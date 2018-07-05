package gui;

import java.awt.Color; 
import java.awt.Dimension;

import java.util.Calendar;

//Les composants
import javax.swing.JPanel;

import clinique.Clinique;

import javax.swing.BoxLayout;
import javax.swing.JButton; 
import javax.swing.JComboBox;

//Les layouts
import java.awt.BorderLayout; 
import java.awt.GridLayout;

//Les événements
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;

/**
 * Panneau qui permet de sélectionner une date et de gérer les 
 * rendez-vous de la clinique.
 * 
 * Pour cette partie, le panneau montre un calendrier mais
 * n'implémente pas les événements.  Cela se fera dans la partie 2 du travail.  
 * 
 * Ce code est fourni aux étudiants mais n'a pas à être consulté pour la partie 1.
 * 
 * Dans le cadre du cours inf111, travail pratique numéro 3
 * (voir énoncé INF111 A17 tp3 partie 1 fourni).
 * 
 * @author Pierre Bélisle
 * @version Copyright A2017
 *
 */
public class PanneauSelecteurDate extends JPanel{
	

	
	/*
	 * Stratégie : Un panneau privé pour sélectionner le mois et l'année dans des
	 *                   composants le permettant (voir panneauComboMoisAnnee).
	 * 
	 *                   Un panneau privé de boutons ajusté selon le nombre de jour 
	 *                   du mois et l'année.  Lorsqu'un bouton est cliqué, on le met
	 *                   rouge et on remet blanc l'ancien jour sélectionné.  Pour cela,
	 *                   on utilise un attribut global (boutonActif)
	 *                   
	 *                   On utilise la classe Calendar qui existe déjà et qui nous
	 *                   permet d'obtenir facilement le jour associé à une date.
	 *                   Cela pour afficher les boutons aux bons endroits dans la
	 *                   grille.
	 */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6310410965175209498L;
	//Dimension de la fenêtre
	private final int LARGEUR = 380;
	private final int HAUTEUR = 380;
	
	//Permet le choix du mois et de l'année 
	PanneauComboMoisAnnee panneauComboMoisAnnee;

	//Panneau montrant les jour du mois
	PanneauJour panneauJour;		
	
	//obtenir la date du jour et maintenir le choix de l'utilisateur
	Calendar cTravail;
	
	//Sert a remettre l'ancien jour en blanc lorsqu'un nouveau jour est choisi
	JButton boutonActif;
	
	private Clinique clinique;
	
	/**
	 * Constructeur qui permet de sélectionner une date et de l'obtenir dans un 
	 * objet de la classe Calendar.
	 * 
	 * @param Le titre de la fenêtre de dialogue
	 */
	public PanneauSelecteurDate(String titre, Clinique clinique){

		
		/*
		 *  On laisse tout dans le constructeur plutôt que créer une méthode 
		 *  initComposants car un DateChooser est initialisée une seule fois 
		 *  par utilisation.
		 */

		this.clinique = clinique;
	
		//Sert à obtenir la date de l'ordi
		cTravail = Calendar.getInstance();
		
		//Création du panneau permettant de sélectionner le mois et l'année
		panneauComboMoisAnnee = new PanneauComboMoisAnnee();
				
		//Création du panneau permettant de sélectionner LE JOUR
		panneauJour = new PanneauJour();
						
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		//ajout des composants dans le panneau de contenu de la fenêtre
		add(panneauComboMoisAnnee, BorderLayout.PAGE_START);
		add(panneauJour);
		
		//Ajuste la dimension du panneau.
		UtilitaireSwing.setDimension(this, 
				new Dimension(LARGEUR,HAUTEUR));
		
	}
	
	/**
	 * Retourne 1 si l'année est bissextile et 0 sinon.
	 * 
	 * @param annee l'année actuelle
	 * @return 1 si l'année est bissextile et 0 sinon
	 */
	private int estBissextile(int annee){
		
		//L'anéée est bissextile au 400 ans ou si 
		//c'est un multiple de 4 mais pas de 100 (Almanah du peuple)
		if((annee%400 == 0) || (annee % 4 == 0 && annee % 100 != 0))
			return 1;
		
		return 0;
	}
	
	/*
	 * Retourne la date sélectionnée en String
	 * @return  La date choisie en String
	 */
	public Calendar getCalendar(){
				
		return cTravail;
	}

	
	/**
	 * 
	 * CLASSE INTERNE ET PRIVÉE
	 * 
	 * Permet de sélection le mois et l'année visa des JComboBox
	 * 
	 * @author pbelisle
	 *
	 */
	private class PanneauComboMoisAnnee extends JPanel{
		
		
		/**
		 *  Stratégie : On met les noms de mois dans un tableau pour la 
		 *                    présentation dans un JComboBox.
		 *                    
		 *                    Les années aussi sont dans un JComboBox de String qu'on
		 *                    ajoute item par item dans une boucle.
		 *                    
		 *                    Tout est dans le constructeur car initialisé une seule fois.
		 *                      
		 */
		
		//évite un warning mais est inutilisé
		private static final long serialVersionUID = 1L;

		//Le tableau avec les noms de mois
		private final String[] TAB_NOM_MOIS = new String[] 
				{"Janvier", "Février", "Mars", "Avril", "Mai",
	            "Juin", "Juillet", "Août", "Septembre", "Octobre",
	            "Novembre", "Décembre"};
		
		//Valeur arbitrairement choisie.
		private final int MIN_ANNEE = 1963;
		private final int MAX_ANNEE = 2100;
		
		//Un JComboBox pour chaque
		private JComboBox<String> comboMois;
		private JComboBox<String> comboAnnee;
		 
		
		/**
		 * Constructeur
		 *   
		 */
		public PanneauComboMoisAnnee(){
			
			//Création du combo qui contient les mois et sélection du mois
			//actuel
			comboMois = new JComboBox<String>(TAB_NOM_MOIS);
			comboMois.setSelectedIndex(cTravail.get(Calendar.MONTH));
			
			//L'écouteur ne retient que la valeur sélectionnée
			comboMois.addActionListener(new MonEcouteurComboMois());
						
			//Création de la liste des années
			comboAnnee = new JComboBox<String>();
			
			//Ajout des années une par une.  
			//Pour changer l'intervalle, il faut changer les constantes
			for(int i = MIN_ANNEE; i <= MAX_ANNEE; i++)
			   comboAnnee.addItem(new String(String.valueOf(i)));
			
			//sélectionne l'année en cours
			comboAnnee.setSelectedItem(
					String.valueOf(cTravail.get(Calendar.YEAR)));		
			
			//L'écouteur ne retient que la valeur sélectionnée
			comboAnnee.addActionListener(new MonEcouteurComboAnnee());
			
			add(comboMois);
			add(comboAnnee);
			
			UtilitaireSwing.setDimension(this, new Dimension(162, 45));
		}
		
		
		private class MonEcouteurComboMois implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				
				cTravail.set(Calendar.MONTH, comboMois.getSelectedIndex());
				
				panneauJour.initComposants();
			}
			
		}
				
		private class MonEcouteurComboAnnee implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				
				cTravail.set(Calendar.YEAR,
						Integer.valueOf((String)comboAnnee.getSelectedItem()));
				
				panneauJour.initComposants();
			}
			
		}
		
	}
	
	/**
	 * CLASSE INTERNE ET PRIVÉE
	 * 
	 * 
	 * Panneau qui contiendra les jours possibles selon le mois reçu
	 * @author pbelisle
	 *
	 */
	private class PanneauJour extends JPanel{
		

        /**
		 * Stratégie : On utilise un tableau de String pour les noms de jour et le 
		 *                   nombre de jours maximum permis par mois.
		 *                   
		 *                   Pour le cas février, on utilise la méthode locale privée 
		 *                   estBissextile pour déterminer si on ajoute 1 au nombre 
		 *                   de jour ou non.  
		 *                   
		 *                   Le panneau des jours se remet à jour aussitôt que 
		 *                   le mois ou l'année change et que le panneau est redessiné.
		 *                   Cela parce qu'on a mis le code dans paintComponent.
		 *                   
		 *                   
		 */
		//évite un warning mais est inutilisé
		private static final long serialVersionUID = 1L;

		//Même stratégie que pour les mois du panneauComboMoisAnnee
		private final String[] TAB_JOUR_SEMAINE = new String[] 
				{"D","L","Ma","Me","J","V","S"};
        
		//Nombre de jours permis par mois
		private final int[] TAB_NB_JOUR_MAX_PAR_JOUR = new int[] 
				{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		
		//Allure du calendrier (GridLayout)
		private int NB_LIGNE = 7; 
		private int NB_COLONNE = 7; 
				
		
		public PanneauJour(){
			
			//On veut une grille pour mettre les jours
			setLayout(new GridLayout(NB_LIGNE,NB_COLONNE));		
			
			initComposants();
		}
		
		
		/**
		 * Évite de devoir appeler initComposants() directement dans les 
		 * contrôleurs du panneau pour la séection du mois et de l'année.
		 */
		public void initComposants(){
			
			//évite pls appels aux accesseurs
			int mois = cTravail.get(Calendar.MONTH);
			int annee = cTravail.get(Calendar.YEAR); 			
			/*
			 * Stratégie : On recrée le panneau de boutons à chaque réaffichage.
			 *                   On le vide d'abord et on utilise des méthodes privées
			 *                   pour le remplir à nouveau.
			 *        
			 */
			//Conseillé			
			removeAll();
			
			//Pour ajouter 1  jour en février des années bissextiles
			int bissex = 0;
			
			//On appelle la fonction seulement en février
			if(mois== Calendar.FEBRUARY)
				bissex = estBissextile(annee);
			
			//On créer et ajoute les boutons
			ajouterBouton(TAB_NB_JOUR_MAX_PAR_JOUR[mois] + bissex);
			
			validate();
			
		
		}
		
		/*
		 * Ajouter les boutons d'entête contenant les jours de la semaine
		 */
		private void ajouterBoutonEntete(){
			
			/*
			 * Stratégie : On crée un bouton avec chaque valeur du tableau
			 *                   qui contient les noms de jour et on les ajoute au panneau.
			 */
			JButton b;
			
			for(int i = 0; i < TAB_JOUR_SEMAINE.length; i++){
				
				b = new JButton(TAB_JOUR_SEMAINE[i]);
				
				b.setBackground(Color.LIGHT_GRAY);
				
				add(b);
			}		
		}
		
		
		/*
		 * Ajoute nb boutons vides de couleur de fond blanc dans le panneau
		 */
		private void ajouterBoutonVide(int nb){
			
			JButton b;
			
			for(int i = 0; i <nb;i++){
				b = new JButton();
				
				b.setBackground(Color.WHITE);
				
			    add(b);
			}			    
		}
		
		/*
		 * Ajoute les boutons de 1 au nombre de jour max pour le mois fourni.
		 * 
		 * 
		 */		
		private void ajouterBoutonJour(int nbJour, int jourActuel){
			
			/*
			 * Stratégie : Dans une boucle pour toutes les valeurs possibles de
			 *                  jour ( 1 à nbJour) on crée un bouton avec la valeur.
			 *                  
			 *                   Lorsque le jour  représente le jourActuel reçu, il est mis 
			 *                   rouge, retient pour traitement futur dans boutonActif.
			 */
			MonEcouteurBouton ecouteBouton = new MonEcouteurBouton();
			
			//Bouton à créer et à mettre dans le panneau
			JButton b;
			
			//Les jours
			for(int i = 1; i <= nbJour;i++){
				
				b = new JButton(String.valueOf(i));
				
				//On retient le jour qui est mis rouge pour pouvoir
				//le remettre blanc lors du clique d'un autre bouton
				//(voir MonEcouteurBouton).
				
				if(i == jourActuel){
					
				   boutonActif = b;
				   b.setBackground(Color.RED);
				}
				else					
				   b.setBackground(Color.WHITE);
				
				b.addActionListener(ecouteBouton);
				
				add(b);			
			}						
		}
				
		/**
		 * Procédure qui utilise les sous programmes d'ajout 
		 * de boutons pour créer le calendrier
		 *  
		 * @param nbJour  Le nombre de jout du mois actuel
		 */
		private void ajouterBouton(int nbJour){
			
			/*
			 * Stratégie : On se crée une copie de l'objet Calendar pour le mettre
			 *                   au premier jour du mois et obtenir le jour de la semaine
			 *                   que le mois commence.  On crée les boutons à partir 
			 *                   de cette valeur.
			 *                   
			 *                   La création des boutons est réalisée par les méthodes 
			 *                   privées locales prévues.
			 */
			Calendar cTmp = Calendar.getInstance(); 
				
			//L'Entête avec les jours de la semaine
			ajouterBoutonEntete();
			
			//Pour trouver le premier jour de la semaine, on set le calendrier
			//au premier jour du mois et on récupère quel jour de la semaine c'est.
			cTmp.set(cTravail.get(Calendar.YEAR), cTravail.get(Calendar.MONTH), 1);
			
			int premierJour = cTmp.get(Calendar.DAY_OF_WEEK);
			
			//Ajouter les boutons vides avant le premier du mois
			ajouterBoutonVide(premierJour-1);
			
			//Ajoute les jours
			ajouterBoutonJour(nbJour, cTravail.get(Calendar.DAY_OF_MONTH));
			
			//Ajoute des boutons à la fin pour garder la taille de la grille
			ajouterBoutonVide(NB_LIGNE * NB_COLONNE -
					(nbJour+TAB_JOUR_SEMAINE.length+premierJour-1));
		}
		
		/**
		 * À faire pour chaque bouton, on change la couleur selon
		 * le bouton cliqué et on remet l'ancien à blanc.
		 * 
		 * On appelle aussi la procédure qui affiche les plages horaires.
		 * 
		 * @author pbelisle
		 */
		private class MonEcouteurBouton implements ActionListener{
			
			public void actionPerformed(ActionEvent arg0) {
				
				JButton b = (JButton) arg0.getSource();
				
				//Si on touche à un bouton, on veut retenir le  jour actuel
				//et changer la couleur de celui qui est choisi en 
				//changeant celui qui était choisi avant. À moins
				//que ne soit un double clique.
				cTravail.set(Calendar.DAY_OF_MONTH, Integer.valueOf(b.getText()));
								
				b.setBackground(Color.RED);
				
				//Évite de remettre blanc lors d'un double clique.  En d'autres mots,
				//on ne permet pas de n'avoir aucun jour de sélectionnée.
				if(boutonActif != b)
				    boutonActif.setBackground(Color.WHITE);
				
				boutonActif = b;
				
				/*
				 * Stratégie : On démarrera la JDialog qui montre une plage horaire et 
				 * permet a gestion de tous les rendez-vous.  Partie 2 tp3.
				 */

			}			
		}				
	}	
}