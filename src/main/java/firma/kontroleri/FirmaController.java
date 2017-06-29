package firma.kontroleri;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import firma.model.Firma;
import firma.servisi.FirmaServis;

@RestController
@RequestMapping("/firma")
public class FirmaController {

	@Autowired
	FirmaServis firmaServis;

	@Autowired
	HttpSession sesija;

	@GetMapping(path = "/login/{port}/{lozinka}")
	public ResponseEntity<Firma> login(@PathVariable("port") String port, @PathVariable("lozinka") String lozinka) {
		return firmaServis.login(port, lozinka);
	}

	@GetMapping(path = "/logout")
	public ResponseEntity<?> logout() {
		Firma f = (Firma) sesija.getAttribute("firma");
		if (f == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		sesija.invalidate();
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/sviKupci")
	public ResponseEntity<List<Firma>> sviKupci() {
		return firmaServis.sviKupci();
	}

}
