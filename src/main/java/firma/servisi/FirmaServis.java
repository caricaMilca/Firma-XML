package firma.servisi;

import org.springframework.http.ResponseEntity;

import firma.model.Firma;


public interface FirmaServis {

	ResponseEntity<Firma> login(String port, String lozinka);


}
