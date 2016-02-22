package devoxx.microframeworks.services;

// FIXME clean data: private field, use List<T> when useful, good type
public class Wine {

    String id;
    String produit;
    String millesime;
    String appellation;
    String couleur;
    String region;
    String pays;
    String bio;
    String cepageDominant;
    String type;
    String alcool;
    String contenance;

    String image; // FIXME IL: remove prefix 'http://localhost:8090' in image (json)
    String conservation;
    String aBoireAPartirDe;
    String garderJusquA;

    String auNez;
    String enBouche;
    String aOeil;

    String service;
    String temperatureDeService;
    String accordsRecommandes;
    String accordsMetsVin;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public String getMillesime() {
        return millesime;
    }

    public void setMillesime(String millesime) {
        this.millesime = millesime;
    }

    public String getAppellation() {
        return appellation;
    }

    public void setAppellation(String appellation) {
        this.appellation = appellation;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getCepageDominant() {
        return cepageDominant;
    }

    public void setCepageDominant(String cepageDominant) {
        this.cepageDominant = cepageDominant;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAlcool() {
        return alcool;
    }

    public void setAlcool(String alcool) {
        this.alcool = alcool;
    }

    public String getContenance() {
        return contenance;
    }

    public void setContenance(String contenance) {
        this.contenance = contenance;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getConservation() {
        return conservation;
    }

    public void setConservation(String conservation) {
        this.conservation = conservation;
    }

    public String getaBoireAPartirDe() {
        return aBoireAPartirDe;
    }

    public void setaBoireAPartirDe(String aBoireAPartirDe) {
        this.aBoireAPartirDe = aBoireAPartirDe;
    }

    public String getGarderJusquA() {
        return garderJusquA;
    }

    public void setGarderJusquA(String garderJusquA) {
        this.garderJusquA = garderJusquA;
    }

    public String getAuNez() {
        return auNez;
    }

    public void setAuNez(String auNez) {
        this.auNez = auNez;
    }

    public String getEnBouche() {
        return enBouche;
    }

    public void setEnBouche(String enBouche) {
        this.enBouche = enBouche;
    }

    public String getaOeil() {
        return aOeil;
    }

    public void setaOeil(String aOeil) {
        this.aOeil = aOeil;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getTemperatureDeService() {
        return temperatureDeService;
    }

    public void setTemperatureDeService(String temperatureDeService) {
        this.temperatureDeService = temperatureDeService;
    }

    public String getAccordsRecommandes() {
        return accordsRecommandes;
    }

    public void setAccordsRecommandes(String accordsRecommandes) {
        this.accordsRecommandes = accordsRecommandes;
    }

    public String getAccordsMetsVin() {
        return accordsMetsVin;
    }

    public void setAccordsMetsVin(String accordsMetsVin) {
        this.accordsMetsVin = accordsMetsVin;
    }
}
