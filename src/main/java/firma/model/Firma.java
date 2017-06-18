package firma.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Firma {

	@Id
	@GeneratedValue
	public Long id;

	public String naziv;

	public String adresa;

	public String pib;

	@Column(unique = true, nullable = false)
	public String port; // preko ovog login
	
	public String lozinka;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "firma", cascade = CascadeType.ALL)
	@JsonIgnore
	public Set<Racun> racuni = new HashSet<Racun>();

	public Firma() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	
}
