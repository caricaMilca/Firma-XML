package firma.repozitorijumi;

import org.springframework.data.jpa.repository.JpaRepository;

import firma.model.Faktura;
import firma.model.ZaglavljeFakture;

public interface FakturaRepozitorijum extends JpaRepository<Faktura, Long> {

	Faktura findByZaglavljeFakture(ZaglavljeFakture findOne);

}
