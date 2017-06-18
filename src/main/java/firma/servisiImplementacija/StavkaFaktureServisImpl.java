package firma.servisiImplementacija;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import firma.model.StavkaFakture;
import firma.repozitorijumi.FakturaRepozitorijum;
import firma.repozitorijumi.StavkaFaktureRepozitorijum;
import firma.repozitorijumi.ZaglavljeFaktureRepozitorijum;
import firma.servisi.StavkaFaktureServis;

@Service
@Transactional
public class StavkaFaktureServisImpl implements StavkaFaktureServis {

	@Autowired
	FakturaRepozitorijum fakturaRepozitorijum;
	
	@Autowired
	StavkaFaktureRepozitorijum stavkaFaktureRepozitorijum;
	
	@Autowired
	ZaglavljeFaktureRepozitorijum zaglavljeFaktureRepozitorijum;
	

	@Override
	public ResponseEntity<List<StavkaFakture>> sveStavkeFakture(Long id) {
		return new ResponseEntity<List<StavkaFakture>>(stavkaFaktureRepozitorijum.findByFaktura(fakturaRepozitorijum.findOne(id)), HttpStatus.OK);
	}

}
