package firma.servisi;

import firma.presek.GetPresekResponse;
import firma.zahtev.Zahtev;

public interface ZahtevServis {

	GetPresekResponse posaljiZahtev(Zahtev z);
}
