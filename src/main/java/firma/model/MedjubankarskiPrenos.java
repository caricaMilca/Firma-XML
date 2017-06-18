package firma.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlRootElement()
@Entity
@XmlSeeAlso({NalogZaPrenos.class, Banka.class})
public class MedjubankarskiPrenos {

	@Id
	@GeneratedValue
	public Long id;
	
	@ManyToOne
	public Banka bankaPosiljalac;
	
	@ManyToOne
	public Banka bankaPrimalac;

	@XmlElementWrapper(name="sviIzvodi")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "medjubankarskiPrenos", cascade = CascadeType.ALL)
	@JsonIgnore
	public Set<NalogZaPrenos> izvodi = new HashSet<NalogZaPrenos>();
	
	@XmlElement(name = "datum")
	@XmlSchemaType(name = "datum")
	public Date datum; 

	public Double iznos;

	@XmlElement
	@Enumerated(EnumType.STRING)
	public TipPoruke tipPoruke;
	
	public MedjubankarskiPrenos() {
		super();
	}

}
