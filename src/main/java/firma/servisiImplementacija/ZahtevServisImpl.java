package firma.servisiImplementacija;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import firma.model.Banka;
import firma.model.Racun;
import firma.repozitorijumi.RacunRepozitorijum;
import firma.servisi.ZahtevServis;
import firma.zahtev.GetZahtevRequest;
import firma.zahtev.Zahtev;

@Service
@Transactional
public class ZahtevServisImpl implements ZahtevServis {

	@Autowired
	HttpSession sesija;

	@Autowired
	RacunRepozitorijum racunRep;

	@Autowired
	WebServiceTemplate webServiceTemplate;

	@Override
	public ResponseEntity<?> posaljiZahtev(Zahtev z) {
		System.out.println("firma zahtev servis impl");
		Racun r = racunRep.findByBrojRacuna(z.getBrojRacuna());
		Banka b = r.banka;
		GetZahtevRequest zahtev = new GetZahtevRequest();
		zahtev.setZahtev(z);
		String uri = "http://localhost:" + b.port + "/ws";
		System.out.println(" uri: " + uri);
		webServiceTemplate.setDefaultUri(uri);
		webServiceTemplate.marshalSendAndReceive(zahtev);

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
