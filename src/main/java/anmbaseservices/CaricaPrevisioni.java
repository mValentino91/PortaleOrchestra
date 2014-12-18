
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
 *         &lt;element name="Palina" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "palina"
})
@XmlRootElement(name = "CaricaPrevisioni")
public class CaricaPrevisioni {

    @XmlElement(name = "Palina")
    protected String palina;

    /**
     * Recupera il valore della propriet palina.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPalina() {
        return palina;
    }

    /**
     * Imposta il valore della propriet palina.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPalina(String value) {
        this.palina = value;
    }

}
