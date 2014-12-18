
package anmbaseservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per PosizioneVeicolo complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="PosizioneVeicolo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="veicolo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="linea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lon" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nodor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="noddes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="capDst" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="time" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PosizioneVeicolo", propOrder = {
    "veicolo",
    "linea",
    "lat",
    "lon",
    "nodor",
    "noddes",
    "capDst",
    "time",
    "stato"
})
public class PosizioneVeicolo {

    protected String veicolo;
    protected String linea;
    protected String lat;
    protected String lon;
    protected String nodor;
    protected String noddes;
    protected String capDst;
    protected String time;
    protected String stato;

    /**
     * Recupera il valore della propriet veicolo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVeicolo() {
        return veicolo;
    }

    /**
     * Imposta il valore della propriet veicolo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVeicolo(String value) {
        this.veicolo = value;
    }

    /**
     * Recupera il valore della propriet linea.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinea() {
        return linea;
    }

    /**
     * Imposta il valore della propriet linea.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinea(String value) {
        this.linea = value;
    }

    /**
     * Recupera il valore della propriet lat.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLat() {
        return lat;
    }

    /**
     * Imposta il valore della propriet lat.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLat(String value) {
        this.lat = value;
    }

    /**
     * Recupera il valore della propriet lon.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLon() {
        return lon;
    }

    /**
     * Imposta il valore della propriet lon.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLon(String value) {
        this.lon = value;
    }

    /**
     * Recupera il valore della propriet nodor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNodor() {
        return nodor;
    }

    /**
     * Imposta il valore della propriet nodor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNodor(String value) {
        this.nodor = value;
    }

    /**
     * Recupera il valore della propriet noddes.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoddes() {
        return noddes;
    }

    /**
     * Imposta il valore della propriet noddes.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoddes(String value) {
        this.noddes = value;
    }

    /**
     * Recupera il valore della propriet capDst.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCapDst() {
        return capDst;
    }

    /**
     * Imposta il valore della propriet capDst.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCapDst(String value) {
        this.capDst = value;
    }

    /**
     * Recupera il valore della propriet time.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTime() {
        return time;
    }

    /**
     * Imposta il valore della propriet time.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTime(String value) {
        this.time = value;
    }

    /**
     * Recupera il valore della propriet stato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStato() {
        return stato;
    }

    /**
     * Imposta il valore della propriet stato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStato(String value) {
        this.stato = value;
    }

}
