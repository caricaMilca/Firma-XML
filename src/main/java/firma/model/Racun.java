package firma.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Racun {

	@Id
	@GeneratedValue
	public Long id;
	
	@Column(unique=true,nullable=false)
	public String brojRacuna;
	
	@ManyToOne
	public Firma firma;
	
	@ManyToOne
	public Banka banka;
	
	@Column(columnDefinition = "boolean default false", insertable = true)
	public Boolean obracunski = false;
	
	public String valuta;
	
	public Double prometNaTeret;

	public Double prometNaKorist;

	public Double novoStanje;
	
	public Double rezervisanaSredstva;
	
	public Racun() {
		super();
	}
	
	
}
