
package anmbaseservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ArrayOfPosizioneVeicolo complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfPosizioneVeicolo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PosizioneVeicolo" type="{http://m.anm.it/srv/}PosizioneVeicolo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfPosizioneVeicolo", propOrder = {
    "posizioneVeicolo"
})
public class ArrayOfPosizioneVeicolo {

    @XmlElement(name = "PosizioneVeicolo")
    protected List<PosizioneVeicolo> posizioneVeicolo;

    /**
     * Gets the value of the posizioneVeicolo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the posizioneVeicolo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPosizioneVeicolo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PosizioneVeicolo }
     * 
     * 
     */
    public List<PosizioneVeicolo> getPosizioneVeicolo() {
        if (posizioneVeicolo == null) {
            posizioneVeicolo = new ArrayList<PosizioneVeicolo>();
        }
        return this.posizioneVeicolo;
    }

}
