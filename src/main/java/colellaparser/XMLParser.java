package colellaparser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import colellaparser.Produttore;

public class XMLParser {
	
	public static List<Produttore> parseCartellaProduttori(String folderExtendedName){
		List<Produttore> produttoreList = new ArrayList<Produttore>();
		Produttore prod = null;
		
		File dir = new File(folderExtendedName);
		File[] filesList = dir.listFiles();
		for (File file : filesList) {
		    if (file.isFile()) {
		    	prod = parseProduttore(folderExtendedName+"/"+file.getName());
		    	produttoreList.add(prod);
		    }
		}
		
		return produttoreList;
	}

	public static Produttore parseProduttore(String xmlFileName){
		Produttore produttore = new Produttore();
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File(xmlFileName));

			// recuperiamo gli attributi e li impostiamo nell'oggetto Produttore da restituire
			
			NodeList nl;
			Node node;
			
			// attributi singoli
			nl = document.getElementsByTagName("id");
			if(nl.getLength()>0){
				node = nl.item(0);
				produttore.setId(node.getTextContent());
			}
			
			nl = document.getElementsByTagName("denominazione");
			if(nl.getLength()>0){
				node = nl.item(0);
				produttore.setDenominazione(node.getTextContent());
			}
			
			nl = document.getElementsByTagName("descrizione");
			if(nl.getLength()>0){
				node = nl.item(0);
				produttore.setDescrizione(node.getTextContent());
			}
			
			nl = document.getElementsByTagName("dati_storici");
			if(nl.getLength()>0){
				node = nl.item(0);
				produttore.setDatiStorici(node.getTextContent());
			}
			
			nl = document.getElementsByTagName("curiosita");
			if(nl.getLength()>0){
				node = nl.item(0);
				produttore.setCuriosita(node.getTextContent());
			}
			
			nl = document.getElementsByTagName("titolare");
			if(nl.getLength()>0){
				node = nl.item(0);
				produttore.setTitolare(node.getTextContent());
			}
			
			nl = document.getElementsByTagName("website");
			if(nl.getLength()>0){
				node = nl.item(0);
				produttore.setWebSite(node.getTextContent());
			}
			
			nl = document.getElementsByTagName("facebook");
			if(nl.getLength()>0){
				node = nl.item(0);
				produttore.setFacebook(node.getTextContent());
			}
			
			nl = document.getElementsByTagName("twitter");
			if(nl.getLength()>0){
				node = nl.item(0);
				produttore.setTwitter(node.getTextContent());
			}
			
			// Attributi multipli
			nl = document.getElementsByTagName("categoria");
			if(nl.getLength()>0){
				List<String> list = new ArrayList<String>();
				for(int i = 0; i < nl.getLength(); i++){
					node = nl.item(i);
					list.add(node.getTextContent());
				}
				produttore.setCategoriaList(list);
			}
			
			nl = document.getElementsByTagName("tel_mob");
			if(nl.getLength()>0){
				List<String> list = new ArrayList<String>();
				for(int i = 0; i < nl.getLength(); i++){
					node = nl.item(i);
					list.add(node.getTextContent());
				}
				produttore.setTelMobList(list);
			}
			
			nl = document.getElementsByTagName("tel_fisso");
			if(nl.getLength()>0){
				List<String> list = new ArrayList<String>();
				for(int i = 0; i < nl.getLength(); i++){
					node = nl.item(i);
					list.add(node.getTextContent());
				}
				produttore.setTelFissoList(list);
			}

			nl = document.getElementsByTagName("email");
			if(nl.getLength()>0){
				List<String> list = new ArrayList<String>();
				for(int i = 0; i < nl.getLength(); i++){
					node = nl.item(i);
					list.add(node.getTextContent());
				}
				produttore.setEmailList(list);
			}

			nl = document.getElementsByTagName("note");
			if(nl.getLength()>0){
				List<String> list = new ArrayList<String>();
				for(int i = 0; i < nl.getLength(); i++){
					node = nl.item(i);
					list.add(node.getTextContent());
				}
				produttore.setNoteList(list);
			}

			// SEDE
			nl = document.getElementsByTagName("sede");
			if(nl.getLength()>0){
				node = nl.item(0);
				NamedNodeMap nnm = node.getAttributes();
				produttore.setSedeVia(nnm.getNamedItem("via").getTextContent());
				produttore.setSedeLon(nnm.getNamedItem("long").getTextContent());
				produttore.setSedeLat(nnm.getNamedItem("lat").getTextContent());
			}
			if(nl.getLength()>1){
				produttore.setPresentiAltreSedi(true);
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return produttore;

	}
	
	public static void main(String[] arg){
		
		
		List<Produttore> pList = XMLParser.parseCartellaProduttori("C:/XML/produttore");
		
		for(Produttore prod : pList){
			System.out.println(prod.getDenominazione());
		}
	}
}
