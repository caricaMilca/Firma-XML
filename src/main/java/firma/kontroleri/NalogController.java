package firma.kontroleri;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

import firma.model.Faktura;
import firma.nalog.Nalog;
import firma.servisi.NalogServis;

@RestController
@RequestMapping("/nalog")
public class NalogController {

	@Autowired
	NalogServis nalogServis;
	
	@GetMapping(path ="/posaljiNalog/{idNaloga}/{hitno}")
	public ResponseEntity<Faktura> posaljiNalog(@PathVariable("idNaloga") Long id,@PathVariable("hitno") Boolean hitno) {
		return nalogServis.posaljiNalog(id, hitno);
	}
	
	@GetMapping(path = "/sviNalozi")
	public ResponseEntity<List<Nalog>> sveNaloga() {
		return nalogServis.sviNalozi();
	}

	@GetMapping(path = "/kreirajHTMLNaloga/{id}")
	public ResponseEntity<?> kreirajHTMLNaloga(@PathVariable("id") Long id)
			throws JAXBException, IOException, TransformerException {
		return nalogServis.kreirajHTMLNaloga(id);
	}

	@GetMapping(path = "/kreirajPDFNaloga/{id}")
	public ResponseEntity<?> kreirajPDFNaloga(@PathVariable("id") Long id)
			throws JAXBException, IOException, DocumentException, TransformerException {
		return nalogServis.kreirajPDFNaloga(id);
	}
}
