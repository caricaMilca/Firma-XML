package firma.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	    "analitikaIzvoda"
	})
@XmlRootElement(name = "StavkePlacanja")
public class StavkePlacanja {

	  @XmlElement(name = "analitikaIzvoda", required = true)
	  public List<NalogZaPrenos> analitikaIzvoda = new ArrayList<NalogZaPrenos>();

	public StavkePlacanja() {
		super();
		// TODO Auto-generated constructor stub
	}
	  
	  
}
