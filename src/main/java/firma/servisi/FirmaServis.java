package firma.servisi;

import java.util.List;

import org.springframework.http.ResponseEntity;

import firma.model.Firma;


public interface FirmaServis {

	ResponseEntity<Firma> login(String port, String lozinka);

	ResponseEntity<List<Firma>> sviKupci();


}
