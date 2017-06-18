package firma.servisiImplementacija;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import firma.model.Firma;
import firma.model.ZaglavljeFakture;
import firma.repozitorijumi.ZaglavljeFaktureRepozitorijum;
import firma.servisi.ZaglavljeFaktureServis;

@Service
@Transactional
public class ZaglavljeFaktureServisImpl implements ZaglavljeFaktureServis {

	@Autowired
	ZaglavljeFaktureRepozitorijum zaglavljeFaktureRepozitorijum;
	
	@Autowired
	HttpSession sesija;
	
	@Override
	public ResponseEntity<ZaglavljeFakture> registracijaZaglavljaFakture(ZaglavljeFakture zf) {
		Firma f = (Firma) sesija.getAttribute("firma");
		zf.nazivDobavljaca = f.naziv;
		zf.adresaDobavljaca = f.adresa;
		zf.pibDobavljaca = f.pib;
		zf.ukupnoRobaIUsluge = zf.vrijednostRobe.add(zf.vrijednostUsluga);		
		BigDecimal vrijednostSRabatom = zf.ukupnoRobaIUsluge.add(zf.ukupnoRobaIUsluge.negate().multiply(zf.ukupanRabat.divide(new BigDecimal(100))));
		zf.ukupanPorez = vrijednostSRabatom.multiply(new BigDecimal(0.17));
		zf.iznosZaUplatu = zf.ukupanPorez.add(vrijednostSRabatom);
		zf.datumRacuna = (java.sql.Date) new Date();
		zf.datumValute = zf.datumRacuna;
		zf.brojRacuna = generisiRandomBroj();
		return new ResponseEntity<ZaglavljeFakture>(zaglavljeFaktureRepozitorijum.save(zf),HttpStatus.CREATED);
	}

	private String generisiRandomBroj() {
		 int Start = 1;
		 int End = 999999;
		 Random random = new Random();
		 long range = (long)End - (long)Start + 1;
		    // compute a fraction of the range, 0 <= frac < range
		 long fraction = (long)(range * random.nextDouble());
		 int randomNumber =  (int)(fraction + Start);    
		return Integer.toString(randomNumber);
	}

	@Override
	public ResponseEntity<List<ZaglavljeFakture>> svaZaglavljaFakture() {
		return new ResponseEntity<List<ZaglavljeFakture>>(zaglavljeFaktureRepozitorijum.findAll(), HttpStatus.OK);
	}

}
