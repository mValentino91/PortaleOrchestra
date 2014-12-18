
package anmbaseservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ArrayOfPrevisione complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfPrevisione">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Previsione" type="{http://m.anm.it/srv/}Previsione" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfPrevisione", propOrder = {
    "previsione"
})
public class ArrayOfPrevisione {

    @XmlElement(name = "Previsione")
    protected List<Previsione> previsione;

    /**
     * Gets the value of the previsione property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the previsione property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPrevisione().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Previsione }
     * 
     * 
     */
    public List<Previsione> getPrevisione() {
        if (previsione == null) {
            previsione = new ArrayList<Previsione>();
        }
        return this.previsione;
    }

}
