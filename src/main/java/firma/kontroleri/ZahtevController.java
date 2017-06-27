package firma.kontroleri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import firma.servisi.ZahtevServis;
import firma.zahtev.Zahtev;

@RestController
@RequestMapping("/zahtevi")
public class ZahtevController {

	@Autowired
	ZahtevServis zahtevServis;

	@RequestMapping(path = "/posaljiZahtev", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> posaljiZahtev(@RequestBody Zahtev info) {
		System.out.println("firma zahtev kontroler");
		return zahtevServis.posaljiZahtev(info);
	}
}
