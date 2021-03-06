package firma.model;

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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlRootElement()
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Faktura {

	@XmlTransient
	@Id
	@GeneratedValue
	public Long id;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "faktura", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	public Set<StavkaFakture> stavke = new HashSet<StavkaFakture>();

	@ManyToOne
	public ZaglavljeFakture zaglavljeFakture;
	
	@Enumerated(EnumType.STRING)
	public StatusFakture status = StatusFakture.NEPOSLATA;

	public Faktura() {
		super();
		// TODO Auto-generated constructor stub
	}

}
