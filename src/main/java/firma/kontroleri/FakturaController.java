package firma.kontroleri;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;
import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

import firma.model.Faktura;
import firma.model.StavkaFakture;
import firma.servisi.FakturaServis;

@RestController
@RequestMapping("/faktura")
public class FakturaController {

	@Autowired
	FakturaServis fakturaServis;

	@PostMapping(path = "/registracijaFakture/{idZaglavlja}")
	public ResponseEntity<Faktura> registracijaFakture(@Valid @RequestBody StavkaFakture sf,
			@PathVariable("idZaglavlja") Long id) {
		return fakturaServis.registracijaFakture(sf, id);
	}

	@GetMapping(path = "/ulazneFakture")
	public ResponseEntity<List<Faktura>> ulazneFakture() {
		return fakturaServis.ulazneFakture();
	}

	@GetMapping(path = "/sveFakture")
	public ResponseEntity<List<Faktura>> sveFakture() {
		return fakturaServis.sveFakture();
	}

	@PostMapping(path = "/slanjeFakture/{id}")
	public ResponseEntity<?> slanjeFakture(@PathVariable("id") Long id) {
		return fakturaServis.slanjeFakture(id);
	}

	@PostMapping(path = "/primiFakturu")
	public ResponseEntity<?> primiFakturu(@Valid @RequestBody Faktura f) {
		return fakturaServis.primiFakturu(f);
	}

	@GetMapping(path = "/kreirajHTMLFakture/{id}")
	public ResponseEntity<?> kreirajHTMLFakture(@PathVariable("id") Long id)
			throws JAXBException, IOException, TransformerException {
		return fakturaServis.kreirajHTMLFakture(id);
	}

	@GetMapping(path = "/kreirajPDFFakture/{id}")
	public ResponseEntity<?> kreirajPDFFakture(@PathVariable("id") Long id)
			throws JAXBException, IOException, DocumentException, TransformerException {
		return fakturaServis.kreirajPDFFakture(id);
	}
}
