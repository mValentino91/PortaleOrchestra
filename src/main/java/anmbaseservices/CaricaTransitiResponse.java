
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
 *         &lt;element name="CaricaTransitiResult" type="{http://m.anm.it/srv/}ArrayOfLinea" minOccurs="0"/>
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
    "caricaTransitiResult"
})
@XmlRootElement(name = "CaricaTransitiResponse")
public class CaricaTransitiResponse {

    @XmlElement(name = "CaricaTransitiResult")
    protected ArrayOfLinea caricaTransitiResult;

    /**
     * Recupera il valore della propriet caricaTransitiResult.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfLinea }
     *     
     */
    public ArrayOfLinea getCaricaTransitiResult() {
        return caricaTransitiResult;
    }

    /**
     * Imposta il valore della propriet caricaTransitiResult.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfLinea }
     *     
     */
    public void setCaricaTransitiResult(ArrayOfLinea value) {
        this.caricaTransitiResult = value;
    }

}
