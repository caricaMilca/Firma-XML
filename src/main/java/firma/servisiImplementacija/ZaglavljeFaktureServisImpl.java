package firma.servisiImplementacija;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

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
		zf.datumRacuna =  new Date(Calendar.getInstance().getTimeInMillis());
		zf.datumValute = zf.datumRacuna;
		//BigDecimal vrijednostSRabatom = new BigDecimal(500);
		BigDecimal vrijednostSRabatom =
		zf.ukupnoRobaIUsluge.add(zf.ukupnoRobaIUsluge.negate().multiply(zf.ukupanRabat.divide(new BigDecimal(100.0), 1)));
		zf.ukupanPorez = vrijednostSRabatom.multiply(new BigDecimal(0.17));
		zf.iznosZaUplatu = zf.ukupanPorez.add(vrijednostSRabatom);
		return new ResponseEntity<ZaglavljeFakture>(zaglavljeFaktureRepozitorijum.save(zf), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<ZaglavljeFakture>> svaZaglavljaFakture() {
		return new ResponseEntity<List<ZaglavljeFakture>>(zaglavljeFaktureRepozitorijum.findAll(), HttpStatus.OK);
	}

}
