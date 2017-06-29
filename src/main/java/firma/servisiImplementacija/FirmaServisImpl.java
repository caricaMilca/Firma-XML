package firma.servisiImplementacija;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import firma.model.Firma;
import firma.repozitorijumi.FakturaRepozitorijum;
import firma.repozitorijumi.FirmaRepozitorijum;
import firma.servisi.FirmaServis;

@Service
@Transactional
public class FirmaServisImpl implements FirmaServis {

	@Autowired
	FirmaRepozitorijum firmaRepozitorijum;

	@Autowired
	HttpSession sesija;

	@Autowired
	FakturaRepozitorijum fakturaRepozitorijum;

	@Override
	public ResponseEntity<Firma> login(String port, String lozinka) {
		Firma f = firmaRepozitorijum.findByPortAndLozinka(port, lozinka);
		if (f == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		sesija.setAttribute("firma", f);
		return new ResponseEntity<Firma>(f, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Firma>> sviKupci() {
		Firma firma = (Firma) sesija.getAttribute("firma");
		List<Firma> firme = firmaRepozitorijum.findAllExceptOwn(firma.id);
		return new ResponseEntity<List<Firma>>(firme, HttpStatus.OK);
	}

}
