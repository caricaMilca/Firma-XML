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
public class DnevnoStanjeRacuna {

	@Id
	@GeneratedValue
	public Long id;

	public Double prethodnoStanje;

	public Double prometNaTeret;

	public Double prometNaKorist;

	public Double novoStanje;

	@Column(columnDefinition = "boolean default false", insertable = true)
	public Boolean rezervisano = false;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "mm.dd.yyyy")
	public Date datumPrometa;

	@ManyToOne
	public Racun racun;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dnevnoStanjeRacuna", cascade = CascadeType.ALL)
	@JsonIgnore
	public Set<NalogZaPrenos> izvodi = new HashSet<NalogZaPrenos>();

	public DnevnoStanjeRacuna() {
		super();
	}

	public DnevnoStanjeRacuna(Double prethodnoStanje, Double prometNaTeret, Double prometNaKorist, Double novoStanje,
			Date datumPrometa) {
		super();
		this.prethodnoStanje = prethodnoStanje;
		this.prometNaTeret = prometNaTeret;
		this.prometNaKorist = prometNaKorist;
		this.novoStanje = novoStanje;
		this.datumPrometa = datumPrometa;
	}

}
