
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
 *         &lt;element name="RilevaAnomalieLineaResult" type="{http://m.anm.it/srv/}ArrayOfLinea" minOccurs="0"/>
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
    "rilevaAnomalieLineaResult"
})
@XmlRootElement(name = "RilevaAnomalieLineaResponse")
public class RilevaAnomalieLineaResponse {

    @XmlElement(name = "RilevaAnomalieLineaResult")
    protected ArrayOfLinea rilevaAnomalieLineaResult;

    /**
     * Recupera il valore della propriet  rilevaAnomalieLineaResult.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfLinea }
     *     
     */
    public ArrayOfLinea getRilevaAnomalieLineaResult() {
        return rilevaAnomalieLineaResult;
    }

    /**
     * Imposta il valore della propriet  rilevaAnomalieLineaResult.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfLinea }
     *     
     */
    public void setRilevaAnomalieLineaResult(ArrayOfLinea value) {
        this.rilevaAnomalieLineaResult = value;
    }

}
