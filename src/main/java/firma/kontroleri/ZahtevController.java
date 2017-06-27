package firma.kontroleri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import firma.presek.Presek;
import firma.servisi.ZahtevServis;
import firma.zahtev.Zahtev;

@RestController
@RequestMapping("/zahtevi")
public class ZahtevController {

	@Autowired
	ZahtevServis zahtevServis;

	@RequestMapping(path = "/posaljiZahtev", method = RequestMethod.POST)
	@ResponseBody
	public Presek posaljiZahtev(@RequestBody Zahtev info) {
		Presek p = zahtevServis.posaljiZahtev(info).getPresek();
		System.out.println(p.getStavkaPreseka().size() + "  " + p.getStavkaPreseka().get(0).getIznos());
		return p;
	}
}
