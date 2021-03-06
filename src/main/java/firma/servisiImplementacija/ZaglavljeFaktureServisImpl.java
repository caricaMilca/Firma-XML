package firma.servisiImplementacija;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
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
import firma.repozitorijumi.FirmaRepozitorijum;
import firma.repozitorijumi.ZaglavljeFaktureRepozitorijum;
import firma.servisi.ZaglavljeFaktureServis;

@Service
@Transactional
public class ZaglavljeFaktureServisImpl implements ZaglavljeFaktureServis {

	@Autowired
	ZaglavljeFaktureRepozitorijum zaglavljeFaktureRepozitorijum;

	@Autowired
	FirmaRepozitorijum firmaRep;
	
	@Autowired
	HttpSession sesija;

	@Override
	public ResponseEntity<ZaglavljeFakture> registracijaZaglavljaFakture(ZaglavljeFakture zf) {
		Firma f = (Firma) sesija.getAttribute("firma");
		zf.nazivDobavljaca = f.naziv;
		zf.adresaDobavljaca = f.adresa;
		zf.pibDobavljaca = f.pib;
		zf.ukupnoRobaIUsluge = BigDecimal.valueOf(0);
		zf.ukupanRabat = BigDecimal.valueOf(0);
		zf.ukupanPorez = BigDecimal.valueOf(0);
		//zf.ukupnoRobaIUsluge = zf.vrijednostRobe.add(zf.vrijednostUsluga);
		zf.datumRacuna =  new Date(Calendar.getInstance().getTimeInMillis());
		zf.datumValute = zf.datumRacuna;
		//BigDecimal vrijednostSRabatom = new BigDecimal(500);
		//BigDecimal vrijednostSRabatom = BigDecimal.valueOf(0);
		//zf.ukupnoRobaIUsluge.add(zf.ukupnoRobaIUsluge.negate().multiply(zf.ukupanRabat.divide(new BigDecimal(100.0), 1)));
		//zf.ukupanPorez = vrijednostSRabatom.multiply(new BigDecimal(0.17));
		//zf.iznosZaUplatu = zf.ukupanPorez.add(vrijednostSRabatom);
		zf.iznosZaUplatu = BigDecimal.valueOf(0);
		zf.uplataNaRacun = f.racuni.iterator().next().brojRacuna;
		return new ResponseEntity<ZaglavljeFakture>(zaglavljeFaktureRepozitorijum.save(zf), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<ZaglavljeFakture>> svaZaglavljaFakture() {
		Firma f = (Firma) sesija.getAttribute("firma");
		List<ZaglavljeFakture> sve = zaglavljeFaktureRepozitorijum.findAll();
		List<ZaglavljeFakture> lista = new ArrayList<>();
		for(int i=0; i<sve.size(); i++){
			if(sve.get(i).pibDobavljaca.equals(f.pib))
				lista.add(sve.get(i));
		}
		return new ResponseEntity<List<ZaglavljeFakture>>(lista, HttpStatus.OK);
	}

}
