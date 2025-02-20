package histoire;

import exceptions.VillageSansChefException;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ScenarioCasDegrade {
	public static void main(String[] args) {
		try { cas1(); }
		catch (IllegalStateException e) {
			System.out.println("cas1... OK !");
		}
		try { cas2(); }
		catch (IllegalArgumentException e) {
			System.out.println("cas2... OK !");
		}
		try { cas3(); }
		catch (IllegalArgumentException e) {
			System.out.println("cas3... OK !");
		}
		try { cas4(); }
		catch (IllegalStateException e) {
			System.out.println("cas4... OK !");
		}
		try { cas5(); }
		catch (VillageSansChefException e) {
			System.out.println("cas5... OK !");
		}
	}

	private static void cas1() {
		Etal etal = new Etal();
		etal.libererEtal();
	}

	private static void cas2() {
		Etal etal = new Etal();
		etal.acheterProduit(5, null);
	}
	private static void cas3() {
		Etal etal = new Etal();
		etal.acheterProduit(0, new Gaulois("gaulois 1", 1));
	}
	private static void cas4() {
		Etal etal = new Etal();
		etal.acheterProduit(5, new Gaulois("gaulois 1", 1));
	}
	private static void cas5() {
		Village village = new Village("village 1", 1, 0);
		village.afficherVillageois();
	}
}
