package firma.servisi;

import java.util.List;

import org.springframework.http.ResponseEntity;

import firma.model.StavkaFakture;

public interface StavkaFaktureServis {

	ResponseEntity<List<StavkaFakture>> sveStavkeFakture(Long id);

}
