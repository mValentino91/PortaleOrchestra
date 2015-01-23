package anmadvancedservices;

import java.util.Map;

import anmadvancedservices.exceptions.AnmServicesNotAvailableException;
import anmadvancedservices.exceptions.NoInfoPalinaException;
import anmadvancedservices.exceptions.NoLineRouteException;
import anmadvancedservices.exceptions.NoLineServiceAvailableException;
import anmadvancedservices.exceptions.UnspecifiedStopCodeException;

public class AnmOrchestraPOIFermateJsonFactory {

	/**
	 * restituisce un identificativo della categoria di POI che gestisce
	 * @return
	 */
	public static String getPOICategory(){
		return "ANM_Fermate";
	}
	
	/**
	 * restituisce una mappa contenente i Json dei POI della categoria
	 * @return una Map<String, String>, la chiave è il codice del POI, il valore è il Json del POI
	 * @throws AnmServicesNotAvailableException 
	 */
	public static Map<String, String> getPOIMap() throws AnmServicesNotAvailableException{
		try {
			return AnmJsonServices.retrievePoiFermateMap();
		} catch (NoLineServiceAvailableException | NoLineRouteException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new AnmServicesNotAvailableException(e);
		}
	}
	
	/**
	 * restituisce i dati in tempo reale previsti per il POI della categoria gestita
	 * @param codicePOI codice del POI del quale si vogliono ottenere le info in tempo reale
	 * @return Json con le info richieste, specifiche del POI
	 */
	public static String getRealTimeInfo(String codicePOI){
		try {
			return AnmJsonServices.retrievePrevisioniPalina(codicePOI);
		} catch (UnspecifiedStopCodeException | NoInfoPalinaException e) {
			return "{\"error\":\""+e.getMessage()+"\"}";
		}
	}
}
