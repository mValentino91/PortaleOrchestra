package anmadvancedservices;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

	/**
	 * recupera le informazioni base, elenco delle linee, elenco dei percorsi, elenco delle fermate georeferenziate
	 */
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
					jsonNodoPercorso += "codice:\"" + codice + "\"";
					jsonNodoPercorso += ",nome:\"" + nome + "\"";
					jsonNodoPercorso += ",location:[\"" + lat + "\",\"" + lon + "\"]";
					jsonNodoPercorso += ",verso:\"" + verso + "\"";
					jsonNodoPercorso += ",ord:\"" + ord + "\"";
					jsonNodoPercorso += "}";
					
					jsonPercorsoCompleto+=jsonNodoPercorso+",";
					
					// se la palina non   stata gi  inserita nel json delle fermate, la inseriamo
					
					if(!palineRecuperate.contains(codice)){
						jsonSingolaPalina = "{";
						jsonSingolaPalina += "codice:\"" + codice + "\"";
						jsonSingolaPalina += ",nome:\"" + nome + "\"";
						jsonSingolaPalina += ",location:[\"" + lat + "\",\"" + lon + "\"]";
						jsonSingolaPalina += ",verso:\"ND\"";
						jsonSingolaPalina += ",ord:\"ND\"";
						jsonSingolaPalina += "}";
	
						jsonListaPaline+=jsonSingolaPalina+",";
						
						palineRecuperate.add(codice);
					}
				}
				
				jsonPercorsoCompleto="["+jsonPercorsoCompleto.substring(0, jsonPercorsoCompleto.length()-1)+"]";
				
				jsonSingolaLinea = "{linea:\""+l.getLinea()+"\",percorso:"+jsonPercorsoCompleto+"},";
				
			}

			System.out.println(i + " - " + "PALINE INSERITE: " + palineRecuperate.size());
			jsonListaLinee+=jsonSingolaLinea;

		}
		
		jsonListaPaline="["+jsonListaPaline.substring(0, jsonListaPaline.length()-1)+"]";

		String jsonTutteLeFermate = "{linea:\"ALL\",percorso:"+jsonListaPaline+"},";
		
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
	
	
//	/**
//	 * @deprecated
//	 * ritorna una stringa contenente un JSON delle linee comprensivo di percorso completo per ogni linea
//	 * @return
//	 */
//	public static String getElencoLinee(){
//		
//		// recupero la lista delle linee
//	
//		ArrayOfLinea arrayOfLinea = AnmWebServices.caricaElencoLinee();
//		if(arrayOfLinea == null)
//			return null; // gestire errore
//		
//		List<Linea> elencoLinee = arrayOfLinea.getLinea();
//
//		if (elencoLinee == null || elencoLinee.size()<=0)
//			return null; // gestire errore
//		
//		// scorro la lista delle linee e per ogni linea recupero il percorso
//	
//		String jsonListaLinee = "";
//		String jsonSingolaLinea;
//		String jsonPercorsoCompleto;
//		String jsonNodoPercorso;
//
//		
//		String codice;
//		String nome;
//		String lat;
//		String lon;
//		String verso;
//		String ord;		
//		
//		ArrayOfPercorso arrayOfPercorso;
//		
//		for(Linea l : elencoLinee){
//			jsonSingolaLinea = "";
//			
//			arrayOfPercorso = AnmWebServices.caricaPercorsoLinea(l.getLinea());
//			
//			jsonPercorsoCompleto = "";
//
//			if(arrayOfPercorso!=null && arrayOfPercorso.getPercorso()!=null && arrayOfPercorso.getPercorso().size()>0){
//
//				for (Percorso percorso : arrayOfPercorso.getPercorso()) {
//					jsonNodoPercorso = "";
//					codice = percorso.getId();
//					nome = percorso.getNome();
//					nome = nome.replaceAll("\"", "'");
//					lat = percorso.getLat();
//					lon = percorso.getLon();
//					verso = percorso.getVerso();
//
//					ord = percorso.getOrd();
//					jsonNodoPercorso = "{";
//					jsonNodoPercorso += "codice:\"" + codice + "\"";
//					jsonNodoPercorso += ",nome:\"" + nome + "\"";
//					jsonNodoPercorso += ",location:[\"" + lat + "\",\"" + lon + "\"]";
//					jsonNodoPercorso += ",verso:\"" + verso + "\"";
//					jsonNodoPercorso += ",ord:\"" + ord + "\"";
//					jsonNodoPercorso += "}";
//					
//					jsonPercorsoCompleto+=jsonNodoPercorso+",";
//				}
//				
//				jsonPercorsoCompleto="["+jsonPercorsoCompleto.substring(0, jsonPercorsoCompleto.length()-1)+"]";
//				
//				jsonSingolaLinea = "{linea:\""+l.getLinea()+"\",percorso:"+jsonPercorsoCompleto+"},";
//				
//			}
//			jsonListaLinee+=jsonSingolaLinea;
//
//		}
//		
//		jsonListaLinee+="["+jsonListaLinee.substring(0, jsonListaLinee.length()-1)+"]";
//		
//		return jsonListaLinee;
//	}
//
//	/**
//	 * restituisce una stringa contenente un JSON delle paline, comprese le coordinate geografiche
//	 * @return
//	 */
//	public static String getElencoPaline(){
//		// recupero la lista delle paline
//		
//		ArrayOfPalina arrayOfPalina = AnmWebServices.caricaElencoPaline();
//		
//		if(arrayOfPalina == null)
//			return null; // gestire errore
//		
//		List<Palina> elencoPaline = arrayOfPalina.getPalina();
//
//		if (elencoPaline == null || elencoPaline.size()<=0)
//			return null; // gestire errore
//		
//		// scorro la lista delle paline e per ognuna recupero le coordinate e compongo il json
//		
//		String jsonListaPaline = "";
//		String jsonSingolaPalina;
//		
//		String codicePalina;
//		String nomePalina;
//		String latPalina;
//		String lonPalina;
//		
//		ArrayOfPercorso arrayOfPercorso;
//		Percorso percorso;
//		
//		System.out.println(elencoPaline.size());
//		for (Palina p : elencoPaline){
////			System.out.println(Calendar.getInstance());
//			arrayOfPercorso = AnmWebServices.rilevaEsistenzaFermata(p.getId());
//			
//			if (arrayOfPercorso!=null && arrayOfPercorso.getPercorso()!=null && arrayOfPercorso.getPercorso().size()>0){
//				percorso = arrayOfPercorso.getPercorso().get(0);
//				
//				codicePalina = p.getId();
//				nomePalina = p.getNome();
//				latPalina = percorso.getLat();
//				lonPalina = percorso.getLon();
//				
//				jsonSingolaPalina = "{";
//				jsonSingolaPalina += "codice:\"" + codicePalina + "\"";
//				jsonSingolaPalina += ",nome:\"" + nomePalina + "\"";
//				jsonSingolaPalina += ",location:[\"" + latPalina + "\",\"" + lonPalina + "\"]";
//				jsonSingolaPalina += "}";
//
//				jsonListaPaline+=jsonSingolaPalina+",";
//			}
//			
//		}
//		
//		if(jsonListaPaline.length()>0)
//			jsonListaPaline="["+jsonListaPaline.substring(0, jsonListaPaline.length()-1)+"]";
//		else
//			jsonListaPaline="[]";
//		
//		return jsonListaPaline;
//	}

}
