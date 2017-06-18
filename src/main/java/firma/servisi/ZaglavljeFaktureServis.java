package firma.servisi;

import java.util.List;

import org.springframework.http.ResponseEntity;

import firma.model.ZaglavljeFakture;

public interface ZaglavljeFaktureServis {

	ResponseEntity<ZaglavljeFakture> registracijaZaglavljaFakture(ZaglavljeFakture zf);

	ResponseEntity<List<ZaglavljeFakture>> svaZaglavljaFakture();

}
