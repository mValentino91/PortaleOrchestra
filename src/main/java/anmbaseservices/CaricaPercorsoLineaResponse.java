
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
 *         &lt;element name="CaricaPercorsoLineaResult" type="{http://m.anm.it/srv/}ArrayOfPercorso" minOccurs="0"/>
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
    "caricaPercorsoLineaResult"
})
@XmlRootElement(name = "CaricaPercorsoLineaResponse")
public class CaricaPercorsoLineaResponse {

    @XmlElement(name = "CaricaPercorsoLineaResult")
    protected ArrayOfPercorso caricaPercorsoLineaResult;

    /**
     * Recupera il valore della propriet caricaPercorsoLineaResult.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfPercorso }
     *     
     */
    public ArrayOfPercorso getCaricaPercorsoLineaResult() {
        return caricaPercorsoLineaResult;
    }

    /**
     * Imposta il valore della propriet caricaPercorsoLineaResult.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfPercorso }
     *     
     */
    public void setCaricaPercorsoLineaResult(ArrayOfPercorso value) {
        this.caricaPercorsoLineaResult = value;
    }

}
