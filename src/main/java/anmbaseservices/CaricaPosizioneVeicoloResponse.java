
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
 *         &lt;element name="CaricaPosizioneVeicoloResult" type="{http://m.anm.it/srv/}ArrayOfPosizioneVeicolo" minOccurs="0"/>
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
    "caricaPosizioneVeicoloResult"
})
@XmlRootElement(name = "CaricaPosizioneVeicoloResponse")
public class CaricaPosizioneVeicoloResponse {

    @XmlElement(name = "CaricaPosizioneVeicoloResult")
    protected ArrayOfPosizioneVeicolo caricaPosizioneVeicoloResult;

    /**
     * Recupera il valore della propriet caricaPosizioneVeicoloResult.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfPosizioneVeicolo }
     *     
     */
    public ArrayOfPosizioneVeicolo getCaricaPosizioneVeicoloResult() {
        return caricaPosizioneVeicoloResult;
    }

    /**
     * Imposta il valore della propriet caricaPosizioneVeicoloResult.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfPosizioneVeicolo }
     *     
     */
    public void setCaricaPosizioneVeicoloResult(ArrayOfPosizioneVeicolo value) {
        this.caricaPosizioneVeicoloResult = value;
    }

}
