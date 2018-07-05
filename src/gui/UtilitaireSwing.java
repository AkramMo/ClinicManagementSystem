
package gui;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 * Classe utilitaire pour regrouper des instructions sur les
 * composants Swing qui risquent de se répéter.
 * 
 * @author Pierre Bélisle
 * @version Copyright A2017
 */
public class UtilitaireSwing {

    /**
     * Permet de regrouper le code qui dimensionne un <b>composant<\b>
     * de Swing.
     * 
     * @param composant
     *        Le composant Swing à dimensionner
     * @param dimension
     *        La nouvelle dimension du composant.
     * 
     */
    public static void setDimension(JComponent component, Dimension dimension) {

        // Dans nos applications, il est préférable de dimensionner
        // les trois de façon identique.
        component.setMinimumSize(dimension);
        component.setMaximumSize(dimension);
        component.setPreferredSize(dimension);

    }


    /**
     * Permet de regrouper le code qui dimensionne <b>un conteneur</b>
     * de Swing.
     * 
     * @param composant
     *        Le composant Swing à dimensionner
     * @param dimension
     *        La nouvelle dimension du composant.
     * 
     */
    public static void setDimension(Container component, Dimension dimension) {

        // Dans nos applications, il est préférable de dimensionner
        // les trois de façon identique.
        component.setMinimumSize(dimension);
        component.setMaximumSize(dimension);
        component.setPreferredSize(dimension);

    }


    /**
     * Permet d'obtenir un JTable de Swing remplit à partir d'un
     * tableau statique d'objets.
     * 
     * La méthode toString() doit être implémenter par les sous-classes
     * de la classe Object.
     * 
     * 
     * @param liste
     *        Un tableau qui contient les données à présenter.
     */
    public static JTable obtenirListe_A_Afficher(Object[] liste) {

        /*
         * Inspiré du démo sur JTable
         * 
         * Ce code permet de disposer des données dans une table à partir d'une
         * liste. Les éléments de la liste doivent implémenter toString().
         * 
         * (https://docs.oracle.com/javase/tutorial/displayCode.html?code=
         * https://docs.oracle.com/javase/tutorial/uiswing/examples/components/
         * ListDemoProject/src/components/JTableDemo.java)
         */
        String[] columns = { " " };
        Object[][] data = new Object[liste.length][1];

        // On obtient le modèle de la table.
        DefaultTableModel tableModel = new DefaultTableModel();
        JTable listeSwing = new JTable(tableModel);

        // On ajoute les données et l'entête au modèle de la table.
        tableModel.setDataVector(data, columns);

        listeSwing.setSelectionMode(
        		ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // Ajoute liste[i].toString() à la table.
        for (int i = 0; i < liste.length; i++) {

            tableModel.setValueAt(liste[i], i, 0);

        }

        return listeSwing;
    }


    /*
     * Rafraîchit le composant.
     */
    public static void rafraichirCadre(JComponent component) {

        component.validate();
        component.repaint();
    }
}