
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
 *         &lt;element name="CaricaElencoPalineResult" type="{http://m.anm.it/srv/}ArrayOfPalina" minOccurs="0"/>
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
    "caricaElencoPalineResult"
})
@XmlRootElement(name = "CaricaElencoPalineResponse")
public class CaricaElencoPalineResponse {

    @XmlElement(name = "CaricaElencoPalineResult")
    protected ArrayOfPalina caricaElencoPalineResult;

    /**
     * Recupera il valore della propriet caricaElencoPalineResult.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfPalina }
     *     
     */
    public ArrayOfPalina getCaricaElencoPalineResult() {
        return caricaElencoPalineResult;
    }

    /**
     * Imposta il valore della propriet caricaElencoPalineResult.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfPalina }
     *     
     */
    public void setCaricaElencoPalineResult(ArrayOfPalina value) {
        this.caricaElencoPalineResult = value;
    }

}
