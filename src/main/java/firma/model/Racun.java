package firma.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Racun {

	@Id
	@GeneratedValue
	public Long id;
	
	@Column(unique=true,nullable=false)
	public String brojRacuna;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "mm.dd.yyyy")
	public Date datumOtvaranja;
	
	@ManyToOne
	public Firma firma;
	
	@ManyToOne
	public Banka banka;
	
	@Column(columnDefinition = "boolean default false", insertable = true)
	public Boolean obracunski = false;
	
	public String valuta;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "racun", cascade = CascadeType.ALL)
	@JsonIgnore
	public Set<DnevnoStanjeRacuna> dnevnaStanja = new HashSet<DnevnoStanjeRacuna>();
	
	public Racun() {
		super();
	}
	
	
}
