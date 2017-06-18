package firma.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class ZaglavljeFakture {

	@XmlTransient
	@Id
	@GeneratedValue
	public Long id;
	
	@XmlElement(required = true)
    public String nazivDobavljaca;
	
    @XmlElement(required = true)
    public String adresaDobavljaca;
    
    @XmlElement(required = true)
    public String pibDobavljaca;
    
    @XmlElement(required = true)
    public String nazivKupca;
    
    @XmlElement(required = true)
    public String adresaKupca;
    
    @XmlElement(required = true)
    public String pibKupca;
    
    @XmlElement(required = true)
    public String brojRacuna;
    
    @XmlElement(required = true)
    @XmlSchemaType(name = "datumRacuna")
    public Date datumRacuna;
    
   
    @XmlElement(required = true)
    public BigDecimal vrijednostRobe;
    
    @XmlElement(required = true)
    public BigDecimal vrijednostUsluga;
    
    @XmlElement(required = true)
    public BigDecimal ukupnoRobaIUsluge;
    
    @XmlElement(required = true)
    public BigDecimal ukupanRabat;
    
    @XmlElement(required = true)
    public BigDecimal ukupanPorez;
    
    @XmlElement(required = true)
    public String oznakaValute;
    
    @XmlElement(required = true)
    public BigDecimal iznosZaUplatu;
    
    @XmlElement(required = true)
    public String uplataNaRacun;
    
    @XmlElement(required = true)
    @XmlSchemaType(name = "datumValute")
    public Date datumValute;
    
    @XmlTransient
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "zaglavljeFakture", cascade = CascadeType.ALL)
	@JsonIgnore
	public Set<Faktura> fakture = new HashSet<Faktura>();

	public ZaglavljeFakture() {
		super();
	}

	public ZaglavljeFakture(String nazivDobavljaca, String adresaDobavljaca, String pibDobavljaca, String nazivKupca,
			String adresaKupca, String pibKupca, String brojRacuna, Date datumRacuna, BigDecimal vrijednostRobe,
			BigDecimal vrijednostUsluga, BigDecimal ukupnoRobaIUsluge, BigDecimal ukupanRabat, BigDecimal ukupanPorez,
			String oznakaValute, BigDecimal iznosZaUplatu, String uplataNaRacun, Date datumValute) {
		super();
		this.nazivDobavljaca = nazivDobavljaca;
		this.adresaDobavljaca = adresaDobavljaca;
		this.pibDobavljaca = pibDobavljaca;
		this.nazivKupca = nazivKupca;
		this.adresaKupca = adresaKupca;
		this.pibKupca = pibKupca;
		this.brojRacuna = brojRacuna;
		this.datumRacuna = datumRacuna;
		this.vrijednostRobe = vrijednostRobe;
		this.vrijednostUsluga = vrijednostUsluga;
		this.ukupnoRobaIUsluge = ukupnoRobaIUsluge;
		this.ukupanRabat = ukupanRabat;
		this.ukupanPorez = ukupanPorez;
		this.oznakaValute = oznakaValute;
		this.iznosZaUplatu = iznosZaUplatu;
		this.uplataNaRacun = uplataNaRacun;
		this.datumValute = datumValute;
	}
	
    
    
}
