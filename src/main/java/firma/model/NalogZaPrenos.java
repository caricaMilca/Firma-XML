package firma.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.springframework.format.annotation.DateTimeFormat;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	    "duznik",
	    "povjerilac",
	    "svrhaPlacanja",
	    "datumPrimanja",
	    "datumValute",
	    "racunDuznika",
	    "racunPovjerioca",
	    "modelZaduzenja",
	    "pozivNaBrojZaduzenja",
	    "pozivNaBrojOdobrenja",
	    "modelOdobrenja",
	    "hitno",
	    "iznos",
	    "valuta"
	})
@XmlRootElement(name = "nalogZaPrenos")
@Entity
public class NalogZaPrenos {

	@XmlTransient
	@Id
	@GeneratedValue
	public Long id;

	@XmlElement
	public String duznik;

	@XmlElement
	public String povjerilac;

	@XmlElement
	public String svrhaPlacanja;

	@XmlElement(name = "datumPrimanja", required = true)
	@XmlSchemaType(name = "datumPrimanja")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "mm.dd.yyyy")
	public Date datumPrimanja;

	@XmlElement(name = "datumValute")
	@XmlSchemaType(name = "datumValute")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "mm.dd.yyyy")
	public Date datumValute;

	@XmlElement
	public String racunDuznika;

	@XmlElement
	public String racunPovjerioca;

	@XmlElement
	public Integer modelZaduzenja;

	@XmlElement
	public String pozivNaBrojZaduzenja;

	@XmlElement
	public String pozivNaBrojOdobrenja;

	@XmlElement
	public Integer modelOdobrenja;

	@XmlElement
	@Column(columnDefinition = "boolean default false", insertable = true)
	public Boolean hitno = false;

	@XmlElement
	public Double iznos;

	@XmlElement
	public String valuta;

	public NalogZaPrenos() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NalogZaPrenos(String duznik, String povjerilac, String svrhaPlacanja, Date datumPrimanja, Date datumValute,
			String racunDuznika, String racunPovjerioca, Integer modelZaduzenja, String pozivNaBrojZaduzenja,
			String pozivNaBrojOdobrenja, Integer modelOdobrenja, Boolean hitno, Double iznos) {
		super();
		this.duznik = duznik;
		this.povjerilac = povjerilac;
		this.svrhaPlacanja = svrhaPlacanja;
		this.datumPrimanja = datumPrimanja;
		this.datumValute = datumValute;
		this.racunDuznika = racunDuznika;
		this.racunPovjerioca = racunPovjerioca;
		this.modelZaduzenja = modelZaduzenja;
		this.pozivNaBrojZaduzenja = pozivNaBrojZaduzenja;
		this.pozivNaBrojOdobrenja = pozivNaBrojOdobrenja;
		this.modelOdobrenja = modelOdobrenja;
		this.hitno = hitno;
		this.iznos = iznos;
	}

}
