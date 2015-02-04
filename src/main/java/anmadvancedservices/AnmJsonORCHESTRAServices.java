package anmadvancedservices;

import java.util.List;

import anmbaseservices.ArrayOfPrevisione;
import anmbaseservices.Previsione;

/**
 * Fornisce i servizi principali per utilizzare le API di ANM restituendo i risultati in formato JSON 
 * @author barnap
 *
 */
@Deprecated
public class AnmJsonORCHESTRAServices {

	private static String jsonListaLinee = "";
	
	public static String getJsonListaLinee(){
		return jsonListaLinee;
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
	
	
}