package anmadvancedservices;

import anmbaseservices.ArrayOfComune;
import anmbaseservices.ArrayOfLinea;
import anmbaseservices.ArrayOfPalina;
import anmbaseservices.ArrayOfPercorso;
import anmbaseservices.ArrayOfPosizioneVeicolo;
import anmbaseservices.ArrayOfPrevisione;
import anmbaseservices.ServiceInfoAnmLineeClasse;

public class AnmWebServices {

	public static ArrayOfComune caricaElencoComuni(){
		ServiceInfoAnmLineeClasse service = new ServiceInfoAnmLineeClasse();
		return service.getServiceInfoAnmLineeClasseSoap().caricaElencoComuni();
	}
	
	public static ArrayOfLinea caricaElencoLinee(){
		ServiceInfoAnmLineeClasse service = new ServiceInfoAnmLineeClasse();
		return service.getServiceInfoAnmLineeClasseSoap().caricaElencoLinee();
	}
	
	public static ArrayOfPalina caricaElencoPaline(){
		ServiceInfoAnmLineeClasse service = new ServiceInfoAnmLineeClasse();
		return service.getServiceInfoAnmLineeClasseSoap().caricaElencoPaline();
	}
	
	public static ArrayOfPercorso caricaFermateStrada(String strada,String citta){
		ServiceInfoAnmLineeClasse service = new ServiceInfoAnmLineeClasse();
		return service.getServiceInfoAnmLineeClasseSoap().caricaFermateStrada(strada, citta);
	}
	
	public static ArrayOfPercorso caricaPercorsoLinea(String linea){
		ServiceInfoAnmLineeClasse service = new ServiceInfoAnmLineeClasse();
		return service.getServiceInfoAnmLineeClasseSoap().caricaPercorsoLinea(linea);
	}
	
	public static ArrayOfPosizioneVeicolo caricaPosizioneVeicolo(String linea){
		ServiceInfoAnmLineeClasse service = new ServiceInfoAnmLineeClasse();
		return service.getServiceInfoAnmLineeClasseSoap().caricaPosizioneVeicolo(linea);
	}
	
	public static ArrayOfPrevisione caricaPrevisioni(String palina){
		ServiceInfoAnmLineeClasse service = new ServiceInfoAnmLineeClasse();
		return service.getServiceInfoAnmLineeClasseSoap().caricaPrevisioni(palina);
	}
	
	public static ArrayOfLinea caricaTransiti(String palina){
		ServiceInfoAnmLineeClasse service = new ServiceInfoAnmLineeClasse();
		return service.getServiceInfoAnmLineeClasseSoap().caricaTransiti(palina);
	}
	
	public static ArrayOfLinea rilevaAnomalieLinea(String linea){
		ServiceInfoAnmLineeClasse service = new ServiceInfoAnmLineeClasse();
		return service.getServiceInfoAnmLineeClasseSoap().rilevaAnomalieLinea(linea);
	}
	
	public static ArrayOfPercorso rilevaEsistenzaFermata(String fermata){
		ServiceInfoAnmLineeClasse service = new ServiceInfoAnmLineeClasse();
		return service.getServiceInfoAnmLineeClasseSoap().rilevaEsistenzaFermata(fermata);
	}
	
}

