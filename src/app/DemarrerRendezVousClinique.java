package app;

import gui.CadreClinique;

/**
 * @author Francis Bourdeau
 * @version Copyright A2017
 *
 */
public class DemarrerRendezVousClinique {

    public static void main(String[] args) {

    	Thread t = new Thread(new CadreClinique());
    	t.start();
    }
}
