package anmadvancedservices;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import anmadvancedservices.exceptions.AnmServicesNotAvailableException;

public class Prova {
	public static void main(String[] args) {
//		ArrayOfLinea arrL = AnmWebServices.rilevaAnomalieLinea("151");
//		Linea l = arrL.getLinea().get(0);
//		System.out.println(l.getLinea()+" - "+ l.getStato());
//		
//		Percorso ferm = AnmWebServices.rilevaEsistenzaFermata("2018").getPercorso().get(0);
//		System.out.println(ferm.getId());
//
//		
//		ArrayOfPrevisione aop = AnmWebServices.caricaPrevisioni("2018");
//		
//		for(Previsione p : aop.getPrevisione()){
//			System.out.println(p.getLinea());
//		}
//		
//		ArrayOfPercorso aopp = AnmWebServices.caricaFermateStrada("umberto", "napoli");
//		for(Percorso p : aopp.getPercorso()){
//			System.out.println(p.getNome());
//		}
//		
//		json = AnmJsonServices.getElencoLinee();
//		
//		System.out.println(AnmJsonServices.getJsonPrevisioniPalina("1166"));
//		
//		
/*		String jsonLinee;
		String jsonPaline;
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("hh:mm:ss");
		
		System.out.println("START" + dateFormatter.format(Calendar.getInstance().getTime()));
		
		AnmJsonServices serv = new AnmJsonServices();
		
		System.out.println("INITIALIZED" + dateFormatter.format(Calendar.getInstance().getTime()));

		jsonLinee = serv.getJsonListaLinee();
		jsonPaline = serv.getJsonListaPaline();
		*/
//		System.out.println(jsonLinee);
//		System.out.println(jsonPaline);
	
		
/*		try{
			FileOutputStream fileLineeStream = new FileOutputStream("C://JSON/jsonLinee.txt");
			fileLineeStream.write(jsonLinee.getBytes());
			 
			fileLineeStream.flush();
			fileLineeStream.close();
			
			
			FileOutputStream filPalineStream = new FileOutputStream("C://JSON/jsonPaline.txt");
			filPalineStream.write(jsonPaline.getBytes());
			 
			filPalineStream.flush();
			filPalineStream.close();
			System.out.println("SAVED ON FILE " + dateFormatter.format(Calendar.getInstance().getTime()));

		}catch (IOException e) {
			e.printStackTrace();
		}
		*/
		
		
		
//		JsonParser parser = new JsonParser();
//		JsonElement js = parser.parse(json);
//		
//		System.out.println(js.getClass());
/*
		String jsonPercorso = "";
		SimpleDateFormat dateFormatter = new SimpleDateFormat("hh:mm:ss");
		
		System.out.println("START" + dateFormatter.format(Calendar.getInstance().getTime()));
		
		jsonPercorso = AnmJsonIBMServices.getJsonPercorsoLinea("132");
		
		System.out.println("END" + dateFormatter.format(Calendar.getInstance().getTime()));
		System.out.println(jsonPercorso);
		
		String s = AnmJsonIBMServices.getJsonPrevisioniPalina("1166");
		System.out.println(s);
		
		s = AnmJsonIBMServices.getJsonTransitiPalina("1166");
		System.out.println(s);
		
		s = AnmJsonIBMServices.getJsonBasicInfoPalina("1166");
		System.out.println(s);
		
		System.out.println("START" + dateFormatter.format(Calendar.getInstance().getTime()));
		
		s = AnmJsonIBMServices.getJsonAllInfoPalina("1166");
		
		System.out.println("END" + dateFormatter.format(Calendar.getInstance().getTime()));
		
		System.out.println(s);
		*/
		
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("hh:mm:ss");
		
		System.out.println(dateFormatter.format(Calendar.getInstance().getTime())+" - START");
		
		String poiCategory = AnmOrchestraPOIFermateJsonFactory.getPOICategory();
		
		System.out.println(dateFormatter.format(Calendar.getInstance().getTime()) + " - POICATEGORY: " + poiCategory);
		
		String jsonPalina = AnmOrchestraPOIFermateJsonFactory.getRealTimeInfo("1166");
		
		System.out.println(dateFormatter.format(Calendar.getInstance().getTime()) + " - POICATEGORY: " + jsonPalina);
		
		System.out.println(dateFormatter.format(Calendar.getInstance().getTime()) + " - Recupero info su tutti i POI" );
		
		Map<String, String> infoAllPoi;
		try {
			infoAllPoi = AnmOrchestraPOIFermateJsonFactory.getPOIMap();
			System.out.println(dateFormatter.format(Calendar.getInstance().getTime()) + " - Recuperati "+ infoAllPoi.keySet().size() +" POI" );
		
			System.out.println(infoAllPoi.get("1166"));
		
		} catch (AnmServicesNotAvailableException e) {
			System.out.println(dateFormatter.format(Calendar.getInstance().getTime()) + " - ERRORE SERVIZI ANM" );
                        System.out.printf("prova");
		}

		
	}
}
