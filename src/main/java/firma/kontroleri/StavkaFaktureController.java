package firma.kontroleri;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import firma.model.StavkaFakture;
import firma.servisi.StavkaFaktureServis;

@RestController
@RequestMapping("/stavkaFaktura")
public class StavkaFaktureController {
	
	@Autowired
	StavkaFaktureServis stavkaFaktureServis;

	@GetMapping(path ="/sveStavkeFakture/{id}")
	public ResponseEntity<List<StavkaFakture>> sveStavkeFakture( @PathVariable("id") Long id) {
		return stavkaFaktureServis.sveStavkeFakture(id);
	}
}
