
package anmbaseservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="fermata" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "fermata"
})
@XmlRootElement(name = "RilevaEsistenzaFermata")
public class RilevaEsistenzaFermata {

    protected String fermata;

    /**
     * Recupera il valore della propriet  fermata.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFermata() {
        return fermata;
    }

    /**
     * Imposta il valore della propriet  fermata.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFermata(String value) {
        this.fermata = value;
    }

}
