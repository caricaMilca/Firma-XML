package firma.kontroleri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import firma.servisi.NalogServis;

@RestController
@RequestMapping("/nalog")
public class NalogController {

	@Autowired
	NalogServis nalogServis;
	
	@GetMapping(path ="/posaljiNalog/{idNaloga}/{hitno}")
	public ResponseEntity<?> posaljiNalog(@PathVariable("idNaloga") Long id,@PathVariable("hitno") Boolean hitno) {
		return nalogServis.posaljiNalog(id, hitno);
	}
}
