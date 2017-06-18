package firma.model;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

	  public ObjectFactory() {
	    }

	    public NalogZaPrenos createAnalitikaIzvoda() {
	        return new NalogZaPrenos();
	    }

/*	    public Valuta createValuta() {
	    	return new Valuta();
	    }*/
	    
	    public StavkePlacanja createStavkePlacanja(){
	    	return new StavkePlacanja();
	    }
}
