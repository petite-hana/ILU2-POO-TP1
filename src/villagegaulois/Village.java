package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private Marche marche;
	private int nbVillageois = 0;

	public Village(String nom, int nbVillageoisMaximum, int nbEtal) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		this.marche = new Marche(nbEtal);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		int indiceEtalVide = this.marche.trouverEtalLibre();
		this.marche.utiliserEtal(indiceEtalVide, vendeur, produit, nbProduit);

		StringBuilder sb = new StringBuilder();
		sb.append("Le vendeur ")
			.append(vendeur.getNom())
			.append(" Propose des ")
			.append(produit)
			.append(" au marché.");

		return sb.toString();
	}

	public String rechercherVendeursProduit(String produit) {
		Etal[] etals = this.marche.trouverEtals(produit);
		StringBuilder sb = new StringBuilder();

		if (etals.length == 0) {
			sb.append("Il n'y a pas de vendeur qui propose des ")
					.append(produit)
					.append(" au marché");
		} else if (etals.length == 1) {
			sb.append("Seul le vendeur ")
					.append(etals[0].getVendeur().getNom())
					.append(" propose des ")
					.append(produit)
					.append(" au marché.");

			return sb.toString();
		} else {
			sb.append("Les venders qui proposent des fleurs sont : \n");
			for (Etal etal : etals) {
				sb.append(" - ").append(etal.getVendeur().getNom()).append('\n');
			}
		}

		return sb.toString();
	}

	public Etal rechercherEtal(Gaulois vendeur) {
		return this.marche.trouverVendeur(vendeur);
	}
	public String partirVendeur(Gaulois vendeur) {
		return this.rechercherEtal(vendeur).libererEtal();
	}
	public String afficherMarche() {
		return new StringBuilder()
			.append("Le marché du village \"")
			.append(this.nom)
			.append("\" possède plusieurs étals : \n")
			.append(this.marche.afficherMarche())
			.toString();
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
}