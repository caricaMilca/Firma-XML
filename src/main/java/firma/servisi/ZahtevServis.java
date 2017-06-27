package firma.servisi;

import org.springframework.http.ResponseEntity;

import firma.zahtev.Zahtev;

public interface ZahtevServis {

	ResponseEntity<?> posaljiZahtev(Zahtev z);
}
