package firma.servisiImplementacija;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import firma.model.Faktura;
import firma.model.StavkaFakture;
import firma.repozitorijumi.FakturaRepozitorijum;
import firma.repozitorijumi.ZaglavljeFaktureRepozitorijum;
import firma.servisi.FakturaServis;

@Service
@Transactional
public class FakturaServisImpl implements FakturaServis {

	@Autowired
	FakturaRepozitorijum fakturaRepozitorijum;

	
	@Autowired
	ZaglavljeFaktureRepozitorijum zaglavljeFaktureRepozitorijum;
	
	@Override
	public ResponseEntity<List<Faktura>> ulazneFakture() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ResponseEntity<Faktura> registracijaFakture(StavkaFakture sf, Long id) {
		Faktura f = fakturaRepozitorijum.findByZaglavljeFakture(zaglavljeFaktureRepozitorijum.findOne(id));
		if(f == null){
			f = new Faktura();
			f.zaglavljeFakture = zaglavljeFaktureRepozitorijum.findOne(id);
		}
		sf.vrijednost = sf.jedinicnaCijena.multiply(sf.kolicina);
		sf.iznosRabata = sf.procenatRabata.multiply(sf.vrijednost);
		sf.umanjenoZaRabat = sf.vrijednost.add(sf.iznosRabata.negate());
		sf.ukupanPorez = sf.umanjenoZaRabat.multiply(new BigDecimal(0.19));
		f.stavke.add(sf);
		return new ResponseEntity<Faktura>(fakturaRepozitorijum.save(f), HttpStatus.CREATED);
	}

}
