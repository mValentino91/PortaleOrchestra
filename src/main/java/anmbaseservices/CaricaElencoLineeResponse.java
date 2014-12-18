
package anmbaseservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CaricaElencoLineeResult" type="{http://m.anm.it/srv/}ArrayOfLinea" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "caricaElencoLineeResult"
})
@XmlRootElement(name = "CaricaElencoLineeResponse")
public class CaricaElencoLineeResponse {

    @XmlElement(name = "CaricaElencoLineeResult")
    protected ArrayOfLinea caricaElencoLineeResult;

    /**
     * Recupera il valore della propriet caricaElencoLineeResult.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfLinea }
     *     
     */
    public ArrayOfLinea getCaricaElencoLineeResult() {
        return caricaElencoLineeResult;
    }

    /**
     * Imposta il valore della propriet caricaElencoLineeResult.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfLinea }
     *     
     */
    public void setCaricaElencoLineeResult(ArrayOfLinea value) {
        this.caricaElencoLineeResult = value;
    }

}
