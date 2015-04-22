package colellaparser;

import java.util.List;

public class Produttore {
	
	private String id;
	private String denominazione;
	private String sedeVia;
	private String sedeLat;
	private String sedeLon;
	private Boolean presentiAltreSedi;
	
	// ignoriamo prodotto, photo e multimedia
	
	private String descrizione;
	private String datiStorici;
	private String curiosita;
	
	private List<String> categoriaList;
	private List<String> telMobList;
	private List<String> telFissoList;
	private List<String> emailList;
	private List<String> noteList;
	
	private String titolare;
	private String webSite;
	private String facebook;
	private String twitter;
	
	public Produttore() {
		presentiAltreSedi = false;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public String getSedeVia() {
		return sedeVia;
	}

	public void setSedeVia(String sedeVia) {
		this.sedeVia = sedeVia;
	}

	public String getSedeLat() {
		return sedeLat;
	}

	public void setSedeLat(String sedeLat) {
		this.sedeLat = sedeLat;
	}

	public String getSedeLon() {
		return sedeLon;
	}

	public void setSedeLon(String sedeLon) {
		this.sedeLon = sedeLon;
	}

	public Boolean getPresentiAltreSedi() {
		return presentiAltreSedi;
	}

	public void setPresentiAltreSedi(Boolean presentiAltreSedi) {
		this.presentiAltreSedi = presentiAltreSedi;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getDatiStorici() {
		return datiStorici;
	}

	public void setDatiStorici(String datiStorici) {
		this.datiStorici = datiStorici;
	}

	public String getCuriosita() {
		return curiosita;
	}

	public void setCuriosita(String curiosita) {
		this.curiosita = curiosita;
	}

	public List<String> getCategoriaList() {
		return categoriaList;
	}

	public void setCategoriaList(List<String> categoriaList) {
		this.categoriaList = categoriaList;
	}

	public List<String> getTelMobList() {
		return telMobList;
	}

	public void setTelMobList(List<String> telMobList) {
		this.telMobList = telMobList;
	}

	public List<String> getTelFissoList() {
		return telFissoList;
	}

	public void setTelFissoList(List<String> telFissoList) {
		this.telFissoList = telFissoList;
	}

	public List<String> getEmailList() {
		return emailList;
	}

	public void setEmailList(List<String> emailList) {
		this.emailList = emailList;
	}

	public List<String> getNoteList() {
		return noteList;
	}

	public void setNoteList(List<String> noteList) {
		this.noteList = noteList;
	}

	public String getTitolare() {
		return titolare;
	}

	public void setTitolare(String titolare) {
		this.titolare = titolare;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}
	
}
