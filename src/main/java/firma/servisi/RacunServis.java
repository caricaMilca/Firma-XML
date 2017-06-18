package firma.servisi;

import java.util.List;

import org.springframework.http.ResponseEntity;

import firma.model.Racun;

public interface RacunServis {

	ResponseEntity<List<Racun>> sviRacuniFirme();

}
