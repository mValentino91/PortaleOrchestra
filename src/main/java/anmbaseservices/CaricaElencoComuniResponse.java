
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
 *         &lt;element name="CaricaElencoComuniResult" type="{http://m.anm.it/srv/}ArrayOfComune" minOccurs="0"/>
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
    "caricaElencoComuniResult"
})
@XmlRootElement(name = "CaricaElencoComuniResponse")
public class CaricaElencoComuniResponse {

    @XmlElement(name = "CaricaElencoComuniResult")
    protected ArrayOfComune caricaElencoComuniResult;

    /**
     * Recupera il valore della propriet caricaElencoComuniResult.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfComune }
     *     
     */
    public ArrayOfComune getCaricaElencoComuniResult() {
        return caricaElencoComuniResult;
    }

    /**
     * Imposta il valore della propriet caricaElencoComuniResult.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfComune }
     *     
     */
    public void setCaricaElencoComuniResult(ArrayOfComune value) {
        this.caricaElencoComuniResult = value;
    }

}
