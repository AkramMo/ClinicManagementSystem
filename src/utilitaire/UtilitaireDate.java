/**
 * Sert essentiellement au débogage.  Il offre de montrer
 * la date et l'heure d'un objet de la classe Calendar car la méthode toString n'est 
 * pas implémentée pour cette classe abstraite java.util.Calendar.
 * 
 * @author Pierre Bélisle
 * @version Copyright A2017
 * 
 */
package utilitaire;

import java.util.Calendar;

public class UtilitaireDate {
	
	/**
	 * Retourne un string correspondant à la date et l'heure de
	 * la référence reçue.
	 * 
	 *  (à utiliser sous l'onglet  "Expressions" du déboggueur : 
	 *  					ecrire: UtilitaireDate.calendarToString(date))
	 * 
	 * @param date La date à considérer
	 * @return La version String de cet objet de la classe Calendar.
	 */
	public static String calendarToString(Calendar date){
		
		// Format utile au tp.
		return date.get(Calendar.DAY_OF_MONTH) + "/" + 
				date.get(Calendar.MONTH) + "/" +
				date.get(Calendar.YEAR) + "/" + "      " + 
				date.get(Calendar.HOUR_OF_DAY) + ":"  +
				date.get(Calendar.MINUTE);
	}

}
