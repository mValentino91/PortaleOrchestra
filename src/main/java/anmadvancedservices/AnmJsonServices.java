package anmadvancedservices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import anmadvancedservices.exceptions.NoInfoPalinaException;
import anmadvancedservices.exceptions.NoLineRouteException;
import anmadvancedservices.exceptions.NoLineServiceAvailableException;
import anmadvancedservices.exceptions.NoTransitingBusException;
import anmadvancedservices.exceptions.UnspecifiedLineCodeException;
import anmadvancedservices.exceptions.UnspecifiedStopCodeException;
import anmadvancedservices.exceptions.WrongStopCodeException;
import anmadvancedservices.model.Fermata;
import anmadvancedservices.model.Transito;
import anmbaseservices.ArrayOfLinea;
import anmbaseservices.ArrayOfPercorso;
import anmbaseservices.ArrayOfPrevisione;
import anmbaseservices.Linea;
import anmbaseservices.Percorso;
import anmbaseservices.Previsione;


/**
 * Fornisce i servizi principali per utilizzare le API di ANM restituendo i risultati in formato JSON 
 * @author barnap
 *
 */
public class AnmJsonServices {

	protected static String retrieveBasicInfoPalina(String codicePalina) throws UnspecifiedStopCodeException, WrongStopCodeException {
		if(codicePalina==null || "".equals(codicePalina))
			throw new UnspecifiedStopCodeException();
		
		ArrayOfPercorso arrayOfPercorso = AnmWebServices.rilevaEsistenzaFermata(codicePalina);

		if(arrayOfPercorso==null || arrayOfPercorso.getPercorso()==null || arrayOfPercorso.getPercorso().size()<=0)
			throw new WrongStopCodeException("Numero fermata errato.");

		Percorso percorso = arrayOfPercorso.getPercorso().get(0);
		if(percorso.getId()==null)
			throw new WrongStopCodeException(percorso.getStato());
		
		String codice = percorso.getId().trim();
		String nome = percorso.getNome();
		nome = nome.replaceAll("\"", "'");
		String lat = percorso.getLat();
		String lon = percorso.getLon();
		
		String jsonPalina = "{";
		jsonPalina += "\"codice\":\"" + codice + "\"";
		jsonPalina += ",\"nome\":\"" + nome + "\"";
		jsonPalina += ",\"location\":[\"" + lat + "\",\"" + lon + "\"]";
		jsonPalina += "}";
		
		return jsonPalina;
	}
	
	protected static String retrieveTransitiPalina(String codicePalina) throws UnspecifiedStopCodeException, NoTransitingBusException {
		if(codicePalina==null || "".equals(codicePalina))
			throw new UnspecifiedStopCodeException();
		
		ArrayOfLinea arrayOfLinea = AnmWebServices.caricaTransiti(codicePalina);
		
		if(arrayOfLinea==null || arrayOfLinea.getLinea()==null || arrayOfLinea.getLinea().size()<=0)
			throw new NoTransitingBusException("Nessuna linea in transito sulla palina per oggi..");

		if(arrayOfLinea.getLinea().size()==1){
			Linea l = arrayOfLinea.getLinea().get(0);
			if(l.getLinea()==null)
				throw new NoTransitingBusException(l.getStato());;
		}
		
		List<Linea> transiti = arrayOfLinea.getLinea();
		
		String jsonTransiti = "";
		
		for(Linea linea : transiti){
			jsonTransiti+="\""+linea.getLinea().trim()+"\",";
		}
		

		jsonTransiti="["+jsonTransiti.substring(0, jsonTransiti.length()-1)+"]";
		
		return jsonTransiti;
	}
	
	protected static String retrievePrevisioniPalina(String codicePalina) throws UnspecifiedStopCodeException, NoInfoPalinaException {
		if(codicePalina==null || "".equals(codicePalina))
			throw new UnspecifiedStopCodeException();
		
		ArrayOfPrevisione arrayOfPrevisione = AnmWebServices.caricaPrevisioni(codicePalina);
		
		if(arrayOfPrevisione==null || arrayOfPrevisione.getPrevisione()==null || arrayOfPrevisione.getPrevisione().size()<=0)
			throw new NoInfoPalinaException("Nessuna informazione alla palina.");

		if(arrayOfPrevisione.getPrevisione().size()==1){
			Previsione p = arrayOfPrevisione.getPrevisione().get(0);
			if(p.getId()==null)
				throw new NoInfoPalinaException(p.getStato());
		}
		
		List<Previsione> previsioniFermata = arrayOfPrevisione.getPrevisione();
		
		String codLinea;
		String time;
		String min;
		
		String jsonSingolaPrevisione = "";
		String jsonListaPrevisioni = "";
		
		for(Previsione previsione : previsioniFermata){
			codLinea = previsione.getLinea().trim();
			time = previsione.getTime();
			min = previsione.getTimeMin();
			
			jsonSingolaPrevisione = "{";
			jsonSingolaPrevisione += "\"codice\":\"" + codLinea + "\"";
			jsonSingolaPrevisione += ",\"time\":\"" + time + "\"";
			jsonSingolaPrevisione += ",\"min\":\"" + min + "\"";
			jsonSingolaPrevisione += "}";

			jsonListaPrevisioni+=jsonSingolaPrevisione+",";
		}
		

		jsonListaPrevisioni="["+jsonListaPrevisioni.substring(0, jsonListaPrevisioni.length()-1)+"]";
		
		return jsonListaPrevisioni;
	}
	
	protected static String retrievePercorsoLinea(String codiceLinea) throws UnspecifiedLineCodeException, NoLineRouteException {
		if(codiceLinea==null || "".equals(codiceLinea))
			throw new UnspecifiedLineCodeException();
		
		ArrayOfPercorso arrayOfPercorso = AnmWebServices.caricaPercorsoLinea(codiceLinea);

		if(arrayOfPercorso==null || arrayOfPercorso.getPercorso()==null || arrayOfPercorso.getPercorso().size()<=0)
			throw new NoLineRouteException("Attenzione: Non e' possibile recuperare il percorso della linea richiesta");

		if(arrayOfPercorso.getPercorso().size()==1){
			Percorso p = arrayOfPercorso.getPercorso().get(0);
			if(p.getId()==null)
				throw new NoLineRouteException(p.getStato());
		}
		
		String jsonPercorsoCompleto = "";
		
		String jsonNodoPercorso = "";
		String codice = "";
		String nome  = "";
		String lat = "";
		String lon = "";
		String verso = "";
		String ord = "";
		
		for (Percorso percorso : arrayOfPercorso.getPercorso()) {
			jsonNodoPercorso = "";
			codice = percorso.getId().trim();
			nome = percorso.getNome();
			nome = nome.replaceAll("\"", "'");
			lat = percorso.getLat();
			lon = percorso.getLon();
			verso = percorso.getVerso();
			ord = percorso.getOrd();
				
			jsonNodoPercorso = "{";
			jsonNodoPercorso += "\"codice\":\"" + codice + "\"";
			jsonNodoPercorso += ",\"nome\":\"" + nome + "\"";
			jsonNodoPercorso += ",\"location\":[\"" + lat + "\",\"" + lon + "\"]";
			jsonNodoPercorso += ",\"verso\":\"" + verso + "\"";
			jsonNodoPercorso += ",\"ord\":\"" + ord + "\"";
			jsonNodoPercorso += "}";
			
			jsonPercorsoCompleto+=jsonNodoPercorso+",";
				
		
		}
			
		jsonPercorsoCompleto="["+jsonPercorsoCompleto.substring(0, jsonPercorsoCompleto.length()-1)+"]";
		return "{\"linea\":\""+codiceLinea+"\",\"percorso\":"+jsonPercorsoCompleto+"}";
	}
	
	protected static Map<String, String> retrievePoiFermateMap() throws NoLineServiceAvailableException, NoLineRouteException{
		
		// inizializzo mappa da restituire
		
		Map<String, Fermata> fermateMap = new HashMap<String, Fermata>();
		
		// 1) recupero la lista delle linee
		
		ArrayOfLinea arrayOfLinea = AnmWebServices.caricaElencoLinee();
		if(arrayOfLinea==null || arrayOfLinea.getLinea()==null || arrayOfLinea.getLinea().size()<=0)
			throw new NoLineServiceAvailableException("Attenzione: Non e' possibile recuperare le linee in questo momento.");

		
		List<Linea> elencoLinee = arrayOfLinea.getLinea();
		
		List<String> notAvailable = new ArrayList<String>();
		
		for(Linea l : elencoLinee){
			
			try{
				// 2) per ogni linea recupero il percorso
				
				ArrayOfPercorso arrayOfPercorso = AnmWebServices.caricaPercorsoLinea(l.getLinea().trim());
				
				if(arrayOfPercorso==null || arrayOfPercorso.getPercorso()==null || arrayOfPercorso.getPercorso().size()<=0)
					throw new NoLineRouteException("Attenzione: Non e' possibile recuperare il percorso della linea richiesta: "+l.getLinea());
	
				if(arrayOfPercorso.getPercorso().size()==1){
					Percorso p = arrayOfPercorso.getPercorso().get(0);
					if(p.getId()==null)
						throw new NoLineRouteException(p.getStato());
				}
				
				Fermata f = null;
				Transito t = null;
				String codiceFermata = "";
				for (Percorso percorso : arrayOfPercorso.getPercorso()) {
					
					if(percorso.getId()!=null){
						
						codiceFermata = percorso.getId().trim();
						t = new Transito(l.getLinea().trim(), percorso.getOrd(), percorso.getVerso());
	
						// 3) per ogni fermata del percorso: 
						if(fermateMap.containsKey(codiceFermata)){
							// - se è gia stata trovata, aggiungo solo la linea all'elenco dei suoi transiti
							f = fermateMap.get(codiceFermata);
							f.addTransito(t);
						}else{
							// - se è la prima volta che la trovo, inizializzo le informazioni di base e l'elenco dei transiti
							f = new Fermata(codiceFermata, percorso.getLat(), percorso.getLon());
							fermateMap.put(codiceFermata, f);
							f.addTransito(t);
						}
							
					}
					
				}
			}catch(NoLineRouteException e){
				notAvailable.add(l.getLinea());
			}
		}
		
		// 4) alla fine dell'analisi di tutte le linee, dalla struttura dati in memoria creo i JSON per ogni Fermata

		Map<String, String> fermateJsonMap = new HashMap<String, String>();
		Fermata f = null;
		for(String codiceFermata : fermateMap.keySet()){
			f=fermateMap.get(codiceFermata);
			fermateJsonMap.put(codiceFermata, f.toString());
		}
		
		System.out.print("Linee non disponibili: ");
		for(String s : notAvailable){
			System.out.print(s+";");
		}
		System.out.println();
		return fermateJsonMap;
	}
		/*
		System.out.println("INITIALIZING...");
			// recupero la lista delle linee
			
			// TODO: RECUPERARE DATI DAI FILE!!!
//			ArrayOfLinea arrayOfLinea = AnmWebServices.caricaElencoLinee();
//			List<Linea> elencoLinee = arrayOfLinea.getLinea();

			// scorro la lista delle linee e per ogni linea recupero il percorso
			
			List<String> palineRecuperate = new ArrayList<String>();
			
			String jsonListaPaline = "";
			String jsonSingolaPalina;
			
			String jsonListaLinee = "";
			String jsonSingolaLinea;
			String jsonPercorsoCompleto;
			String jsonNodoPercorso;

			
			String codice;
			String nome;
			String lat;
			String lon;
			String verso;
			String ord;		
			
			ArrayOfPercorso arrayOfPercorso;
			
			System.out.println("LINEE DA ANALIZZARE: " + elencoLinee.size());
			int i = 0;
			for(Linea l : elencoLinee){
				i++;
				jsonSingolaLinea = "";
				
				arrayOfPercorso = AnmWebServices.caricaPercorsoLinea(l.getLinea());
				
				jsonPercorsoCompleto = "";

				if(arrayOfPercorso!=null && arrayOfPercorso.getPercorso()!=null && arrayOfPercorso.getPercorso().size()>0){

					for (Percorso percorso : arrayOfPercorso.getPercorso()) {
						jsonNodoPercorso = "";
						codice = percorso.getId();
						nome = percorso.getNome();
						nome = nome.replaceAll("\"", "'");
						lat = percorso.getLat();
						lon = percorso.getLon();
						verso = percorso.getVerso();

						ord = percorso.getOrd();
						jsonNodoPercorso = "{";
						jsonNodoPercorso += "\"codice\":\"" + codice + "\"";
						jsonNodoPercorso += ",\"nome\":\"" + nome + "\"";
						jsonNodoPercorso += ",\"location\":[\"" + lat + "\",\"" + lon + "\"]";
						jsonNodoPercorso += ",\"verso\":\"" + verso + "\"";
						jsonNodoPercorso += ",\"ord\":\"" + ord + "\"";
						jsonNodoPercorso += "}";
						
						jsonPercorsoCompleto+=jsonNodoPercorso+",";
						
						// se la palina non � stata gi� inserita nel json delle fermate, la inseriamo
						
						if(!palineRecuperate.contains(codice)){
							jsonSingolaPalina = "{";
							jsonSingolaPalina += "\"codice\":\"" + codice + "\"";
							jsonSingolaPalina += ",\"nome\":\"" + nome + "\"";
							jsonSingolaPalina += ",\"location\":[\"" + lat + "\",\"" + lon + "\"]";
							jsonSingolaPalina += ",\"verso\":\"ND\"";
							jsonSingolaPalina += ",\"ord\":\"ND\"";
							jsonSingolaPalina += "}";
		
							jsonListaPaline+=jsonSingolaPalina+",";
							
							palineRecuperate.add(codice);
						}
					}
					
					jsonPercorsoCompleto="["+jsonPercorsoCompleto.substring(0, jsonPercorsoCompleto.length()-1)+"]";
					
					jsonSingolaLinea = "{\"linea\":\""+l.getLinea()+"\",\"percorso\":"+jsonPercorsoCompleto+"},";
					
				}

				System.out.println(i + " - " + "PALINE INSERITE: " + palineRecuperate.size());
				jsonListaLinee+=jsonSingolaLinea;

			}
			
			jsonListaPaline="["+jsonListaPaline.substring(0, jsonListaPaline.length()-1)+"]";

			String jsonTutteLeFermate = "{\"linea\":\"ALL\",\"percorso\":"+jsonListaPaline+"},";
			
			jsonListaLinee="["+jsonTutteLeFermate+jsonListaLinee.substring(0, jsonListaLinee.length()-1)+"]";
			
			
			this.jsonListaLinee = jsonListaLinee;
			this.jsonListaPaline = jsonListaPaline;
		}
		return null;
	}
	
	/*
	
	private String jsonListaLinee;
	private String jsonListaPaline;

	public AnmJsonServices() {
		initializeJson();
//		initializeJsonFromFile();
	}
	
	private void initializeJsonFromFile() {
		try{
			FileInputStream fileLineeInputStream = new FileInputStream("C://JSON/jsonLinee.txt");
			byte[] buffer = new byte[1024];
			String jsonListaLinee = "";
			while(fileLineeInputStream.read(buffer)>0){
				jsonListaLinee += new String(buffer);
				buffer = new byte[1024];
			}
			 
			fileLineeInputStream.close();
			
			
			FileInputStream filePalineStream = new FileInputStream("C://JSON/jsonPaline.txt");
			buffer = new byte[1024];
			String jsonListaPaline = "";
			while(filePalineStream.read(buffer)>0){
				jsonListaPaline += new String(buffer);
				buffer = new byte[1024];
			}
			
			filePalineStream.close();
			
			this.jsonListaLinee = jsonListaLinee;
			this.jsonListaPaline = jsonListaPaline;
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initializeJson() {
		System.out.println("INITIALIZING...");
		// recupero la lista delle linee
		
		// TODO: RECUPERARE DATI DAI FILE!!!
		ArrayOfLinea arrayOfLinea = AnmWebServices.caricaElencoLinee();
		List<Linea> elencoLinee = arrayOfLinea.getLinea();

		// scorro la lista delle linee e per ogni linea recupero il percorso
		
		List<String> palineRecuperate = new ArrayList<String>();
		
		String jsonListaPaline = "";
		String jsonSingolaPalina;
		
		String jsonListaLinee = "";
		String jsonSingolaLinea;
		String jsonPercorsoCompleto;
		String jsonNodoPercorso;

		
		String codice;
		String nome;
		String lat;
		String lon;
		String verso;
		String ord;		
		
		ArrayOfPercorso arrayOfPercorso;
		
		System.out.println("LINEE DA ANALIZZARE: " + elencoLinee.size());
		int i = 0;
		for(Linea l : elencoLinee){
			i++;
			jsonSingolaLinea = "";
			
			arrayOfPercorso = AnmWebServices.caricaPercorsoLinea(l.getLinea());
			
			jsonPercorsoCompleto = "";

			if(arrayOfPercorso!=null && arrayOfPercorso.getPercorso()!=null && arrayOfPercorso.getPercorso().size()>0){

				for (Percorso percorso : arrayOfPercorso.getPercorso()) {
					jsonNodoPercorso = "";
					codice = percorso.getId();
					nome = percorso.getNome();
					nome = nome.replaceAll("\"", "'");
					lat = percorso.getLat();
					lon = percorso.getLon();
					verso = percorso.getVerso();

					ord = percorso.getOrd();
					jsonNodoPercorso = "{";
					jsonNodoPercorso += "\"codice\":\"" + codice + "\"";
					jsonNodoPercorso += ",\"nome\":\"" + nome + "\"";
					jsonNodoPercorso += ",\"location\":[\"" + lat + "\",\"" + lon + "\"]";
					jsonNodoPercorso += ",\"verso\":\"" + verso + "\"";
					jsonNodoPercorso += ",\"ord\":\"" + ord + "\"";
					jsonNodoPercorso += "}";
					
					jsonPercorsoCompleto+=jsonNodoPercorso+",";
					
					// se la palina non è stata già inserita nel json delle fermate, la inseriamo
					
					if(!palineRecuperate.contains(codice)){
						jsonSingolaPalina = "{";
						jsonSingolaPalina += "\"codice\":\"" + codice + "\"";
						jsonSingolaPalina += ",\"nome\":\"" + nome + "\"";
						jsonSingolaPalina += ",\"location\":[\"" + lat + "\",\"" + lon + "\"]";
						jsonSingolaPalina += ",\"verso\":\"ND\"";
						jsonSingolaPalina += ",\"ord\":\"ND\"";
						jsonSingolaPalina += "}";
	
						jsonListaPaline+=jsonSingolaPalina+",";
						
						palineRecuperate.add(codice);
					}
				}
				
				jsonPercorsoCompleto="["+jsonPercorsoCompleto.substring(0, jsonPercorsoCompleto.length()-1)+"]";
				
				jsonSingolaLinea = "{\"linea\":\""+l.getLinea()+"\",\"percorso\":"+jsonPercorsoCompleto+"},";
				
			}

			System.out.println(i + " - " + "PALINE INSERITE: " + palineRecuperate.size());
			jsonListaLinee+=jsonSingolaLinea;

		}
		
		jsonListaPaline="["+jsonListaPaline.substring(0, jsonListaPaline.length()-1)+"]";

		String jsonTutteLeFermate = "{\"linea\":\"ALL\",\"percorso\":"+jsonListaPaline+"},";
		
		jsonListaLinee="["+jsonTutteLeFermate+jsonListaLinee.substring(0, jsonListaLinee.length()-1)+"]";
		
		
		this.jsonListaLinee = jsonListaLinee;
		this.jsonListaPaline = jsonListaPaline;
	}

	public void refreshJson(){
		initializeJson();
	}
	
	public String getJsonListaLinee(){
		return jsonListaLinee;
	}
	
	public String getJsonListaPaline(){
		return jsonListaPaline;
	}
	
	public static String getJsonPrevisioniPalina(String codicePalina){
		ArrayOfPrevisione aop = AnmWebServices.caricaPrevisioni(codicePalina);
		List<Previsione> previsioniFermata = aop.getPrevisione();
		
		String codLinea;
		String time;
		String min;
		
		String jsonSingolaPrevisione = "";
		String jsonListaPrevisioni = "";
		
		for(Previsione previsione : previsioniFermata){
			codLinea = previsione.getLinea();
			time = previsione.getTime();
			min = previsione.getTimeMin();
			
			jsonSingolaPrevisione = "{";
			jsonSingolaPrevisione += "codice:\"" + codLinea + "\"";
			jsonSingolaPrevisione += ",time:\"" + time + "\"";
			jsonSingolaPrevisione += ",min:\"" + min + "\"";
			jsonSingolaPrevisione += "}";

			jsonListaPrevisioni+=jsonSingolaPrevisione+",";
		}
		

		jsonListaPrevisioni="["+jsonListaPrevisioni.substring(0, jsonListaPrevisioni.length()-1)+"]";
		
		return jsonListaPrevisioni;
	}
	*/

}
