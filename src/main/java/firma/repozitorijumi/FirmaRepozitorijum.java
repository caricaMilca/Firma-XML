package firma.repozitorijumi;

import org.springframework.data.jpa.repository.JpaRepository;

import firma.model.Firma;

public interface FirmaRepozitorijum extends JpaRepository<Firma, Long> {

	Firma findByPortAndLozinka(String port, String lozinka);

}
