package firma.repozitorijumi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import firma.model.Firma;
import firma.model.Racun;

public interface RacunRepozitorijum extends JpaRepository<Racun, Long> {

	List<Racun> findByFirma(Firma attribute);
	
	Racun findByBrojRacuna(String brojRacuna);

}
