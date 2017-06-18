package firma.kontroleri;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import firma.model.Faktura;
import firma.model.StavkaFakture;
import firma.servisi.FakturaServis;

@RestController
@RequestMapping("/faktura")
public class FakturaController {

	@Autowired
	FakturaServis fakturaServis;
	
	
	@PostMapping(path = "/registracijaFakture/{idZaglavlja}")
	public ResponseEntity<Faktura> registracijaFakture(@Valid @RequestBody StavkaFakture sf , @PathVariable("idZaglavlja") Long id) {
		return fakturaServis.registracijaFakture(sf, id);
	}
	
	
	@GetMapping(path ="/ulazneFakture")
	public ResponseEntity<List<Faktura>> ulazneFakture() {
		return fakturaServis.ulazneFakture();
	}
	
}
