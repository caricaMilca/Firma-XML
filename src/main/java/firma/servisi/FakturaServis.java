package firma.servisi;

import java.util.List;

import org.springframework.http.ResponseEntity;

import firma.model.Faktura;
import firma.model.StavkaFakture;

public interface FakturaServis {

	ResponseEntity<List<Faktura>> ulazneFakture();

	ResponseEntity<Faktura> registracijaFakture(StavkaFakture sf, Long id);
	

}
