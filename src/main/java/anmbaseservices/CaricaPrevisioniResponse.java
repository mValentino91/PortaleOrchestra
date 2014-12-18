
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
 *         &lt;element name="CaricaPrevisioniResult" type="{http://m.anm.it/srv/}ArrayOfPrevisione" minOccurs="0"/>
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
    "caricaPrevisioniResult"
})
@XmlRootElement(name = "CaricaPrevisioniResponse")
public class CaricaPrevisioniResponse {

    @XmlElement(name = "CaricaPrevisioniResult")
    protected ArrayOfPrevisione caricaPrevisioniResult;

    /**
     * Recupera il valore della propriet caricaPrevisioniResult.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfPrevisione }
     *     
     */
    public ArrayOfPrevisione getCaricaPrevisioniResult() {
        return caricaPrevisioniResult;
    }

    /**
     * Imposta il valore della propriet caricaPrevisioniResult.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfPrevisione }
     *     
     */
    public void setCaricaPrevisioniResult(ArrayOfPrevisione value) {
        this.caricaPrevisioniResult = value;
    }

}
