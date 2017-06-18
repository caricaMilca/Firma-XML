package firma.kontroleri;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import firma.model.ZaglavljeFakture;
import firma.servisi.ZaglavljeFaktureServis;

@RestController
@RequestMapping("/zaglavljeFakutre")
public class ZaglavljeFaktureController {

	@Autowired
	ZaglavljeFaktureServis zaglavljeFaktureServis;

	@PostMapping(path = "/registracijaZaglavljaFakture")
	public ResponseEntity<ZaglavljeFakture> registracijaZaglavljaFakture(@Valid @RequestBody ZaglavljeFakture zf) {
		return zaglavljeFaktureServis.registracijaZaglavljaFakture(zf);
	}// sa sesije uzmi podakte o dobavljacu
	//uradi metodu za sve racune firme

	@GetMapping(path ="/svaZaglavljaFakture")
	public ResponseEntity<List<ZaglavljeFakture>> svaZaglavljaFakture() {
		return zaglavljeFaktureServis.svaZaglavljaFakture();
	} // ako su sva zaglavalja = 0, onda ga samo redirektuj na dodavanje zaglavlja
}
