package firma.servisiImplementacija;

import java.math.BigInteger;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import firma.model.Faktura;
import firma.model.Firma;
import firma.nalog.Nalog;
import firma.repozitorijumi.FakturaRepozitorijum;
import firma.servisi.NalogServis;

public class NalogServisImpl implements NalogServis {

	@Autowired
	FakturaRepozitorijum fakturaRepozitorijum;
	
	@Autowired
	HttpSession sesija;
	
	@Override
	public ResponseEntity<?> posaljiNalog(Long id) {
		Faktura f = fakturaRepozitorijum.findOne(id);
		Firma r = (Firma) sesija.getAttribute("firma");
		Nalog n = new Nalog("123", f.zaglavljeFakture.nazivKupca, "neka", r.naziv, f.zaglavljeFakture.uplataNaRacun, BigInteger.valueOf(97L), "11111111111111111111", "nekidokneodradimoracune", BigInteger.valueOf(97L), "22222222222222222222", f.zaglavljeFakture.iznosZaUplatu, f.zaglavljeFakture.oznakaValute, false);
		
		return null;
	}

}
