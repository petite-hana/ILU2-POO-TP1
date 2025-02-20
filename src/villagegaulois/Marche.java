package villagegaulois;

import personnages.Gaulois;

import java.util.Arrays;

public class Marche {
	private final Etal[] etals;
	private int nbEtalVide;

	public Marche(int nbEtal) {
		this.etals = new Etal[nbEtal];
		this.nbEtalVide = nbEtal;

		for (int i = 0; i < nbEtal; i++) {
			this.etals[i] = new Etal();
		}
	}

	public void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
		Etal etal = this.etals[indiceEtal];
		etal.occuperEtal(vendeur, produit, nbProduit);
		this.nbEtalVide--;
	}

	public int trouverEtalLibre() {
		int i = 0;
		while (i < this.etals.length && this.etals[i].isEtalOccupe()) {
			i++;
		}
		if (i >= this.etals.length) {
			return -1;
		}
		return i;
	}

	public Etal[] trouverEtals(String produit) {
		return Arrays.stream(this.etals)
			.filter(etal -> etal.contientProduit(produit))
			.toArray(Etal[]::new);
	}

	public Etal trouverVendeur(Gaulois gaulois) {
		return Arrays.stream(this.etals)
			.filter(etat -> etat.getVendeur() == gaulois)
			.findFirst()
			.orElse(null);
	}

	public String afficherMarche() {
		StringBuilder sb = new StringBuilder();
		for (Etal etal : this.etals) {
			if (etal.isEtalOccupe()) {
				sb.append(etal.afficherEtal());
			}
		}

		if (this.nbEtalVide != 0) {
			sb.append("Il reste ")
				.append(nbEtalVide)
				.append(" étals non utilisés dans le marché.\n");
		}

		return sb.toString();
	}
}
