package firma.model;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class StavkaFakture {

	@XmlTransient
	@Id
	@GeneratedValue
	public Long id;
	
	@XmlElement(required = true)
    public BigInteger redniBroj;
	
    @XmlElement(required = true)
    public String nazivRobeIliUsluge;
    
    @XmlElement(required = true)
    public BigDecimal kolicina;
    
    @XmlElement(required = true)
    public String jedinicaMjere;
    
    @XmlElement(required = true)
    public BigDecimal jedinicnaCijena;
    
    @XmlElement(required = true)
    public BigDecimal vrijednost;
    
    @XmlElement(required = true)
    public BigDecimal procenatRabata;
    
    @XmlElement(required = true)
    public BigDecimal iznosRabata;
    
    @XmlElement(required = true)
    public BigDecimal umanjenoZaRabat;
    
    @XmlElement(required = true)
    public BigDecimal ukupanPorez;

    @ManyToOne
	public Faktura faktura;
    
	public StavkaFakture() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StavkaFakture(BigInteger redniBroj, String nazivRobeIliUsluge, BigDecimal kolicina, String jedinicaMjere,
			BigDecimal jedinicnaCijena, BigDecimal vrijednost, BigDecimal procenatRabata, BigDecimal iznosRabata,
			BigDecimal umanjenoZaRabat, BigDecimal ukupanPorez) {
		super();
		this.redniBroj = redniBroj;
		this.nazivRobeIliUsluge = nazivRobeIliUsluge;
		this.kolicina = kolicina;
		this.jedinicaMjere = jedinicaMjere;
		this.jedinicnaCijena = jedinicnaCijena;
		this.vrijednost = vrijednost;
		this.procenatRabata = procenatRabata;
		this.iznosRabata = iznosRabata;
		this.umanjenoZaRabat = umanjenoZaRabat;
		this.ukupanPorez = ukupanPorez;
	}
	
	
    
    
}
