package anmadvancedservices;

import anmadvancedservices.exceptions.NoInfoPalinaException;
import anmadvancedservices.exceptions.NoLineRouteException;
import anmadvancedservices.exceptions.NoTransitingBusException;
import anmadvancedservices.exceptions.UnspecifiedLineCodeException;
import anmadvancedservices.exceptions.UnspecifiedStopCodeException;
import anmadvancedservices.exceptions.WrongStopCodeException;


/**
 * Fornisce i servizi principali per utilizzare le API di ANM restituendo i risultati in formato JSON 
 * @author barnap
 *
 */
public class AnmJsonIBMServices {

	
	/**
	 * restituisce un JSON contenente le informazioni relative alla fermata specificata, alle linee che vi transitano e alle previsioni relative
	 * il metodo richiama diversi web services ANM, per cui il tempo di esecuzione puo' risentirne 
	 * @param codicePalina
	 * @return
	 */
	public static String getJsonAllInfoPalina(String codicePalina){

		try {
			String basicInfo = AnmJsonServices.retrieveBasicInfoPalina(codicePalina);
			String transiti = AnmJsonServices.retrieveTransitiPalina(codicePalina);
			String previsioni = AnmJsonServices.retrievePrevisioniPalina(codicePalina);
			
			String extendedInfo = basicInfo.substring(0, basicInfo.length()-1);
			extendedInfo += ",\"transiti\":"+transiti;
			extendedInfo += ",\"previsioni\":"+previsioni;
			extendedInfo += "}";
			
			return extendedInfo;
		} catch (UnspecifiedStopCodeException | WrongStopCodeException | NoTransitingBusException | NoInfoPalinaException e) {
			return "{\"error\":\""+e.getMessage()+"\"}";
		}
	}
	
	/**
	 * restituisce un JSON contenente le informazioni relative alla fermata specificata
	 * @param codicePalina
	 * @return
	 */
	public static String getJsonBasicInfoPalina(String codicePalina){

		try {
			return AnmJsonServices.retrieveBasicInfoPalina(codicePalina);
		} catch (UnspecifiedStopCodeException | WrongStopCodeException e) {
			return "{\"error\":\""+e.getMessage()+"\"}";
		}
		
	}

	/**
	 * restituisce un JSON contenente i codici delle linee che transitano per la fermata specificata
	 * @param codicePalina
	 * @return
	 */
	public static String getJsonTransitiPalina(String codicePalina){
		try {
			return AnmJsonServices.retrieveTransitiPalina(codicePalina);
		} catch (UnspecifiedStopCodeException | NoTransitingBusException e) {
			return "{\"error\":\""+e.getMessage()+"\"}";
		}
	}

	/**
	 * restituisce un JSON contenente le previsioni per le linee in arrivo sulla fermata specificata
	 * @param codicePalina
	 * @return
	 */
	public static String getJsonPrevisioniPalina(String codicePalina){
		try {
			return AnmJsonServices.retrievePrevisioniPalina(codicePalina);
		} catch (UnspecifiedStopCodeException | NoInfoPalinaException e) {
			return "{\"error\":\""+e.getMessage()+"\"}";
		}
	}
	
	
	/**
	 * restituisce un JSON contenente il percorso della linea specificata
	 * @param codiceLinea
	 * @return
	 */
	public static String getJsonPercorsoLinea(String codiceLinea){
		try {
			return AnmJsonServices.retrievePercorsoLinea(codiceLinea);
		} catch (UnspecifiedLineCodeException | NoLineRouteException e) {
			return "{\"error\":\""+e.getMessage()+"\"}";
		}
	}
	
}
