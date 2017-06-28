package firma.servisiImplementacija;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import firma.model.Banka;
import firma.model.Racun;
import firma.presek.GetPresekResponse;
import firma.repozitorijumi.RacunRepozitorijum;
import firma.servisi.ZahtevServis;
import firma.zahtev.GetZahtevRequest;
import firma.zahtev.Zahtev;
import xmlTransformacije.SAXValidator;

@Service
@Transactional
public class ZahtevServisImpl implements ZahtevServis {

	@Autowired
	HttpSession sesija;

	@Autowired
	RacunRepozitorijum racunRep;

	@Autowired
	WebServiceTemplate webServiceTemplate;
	
	SAXValidator validator = new SAXValidator();

	@Override
	public GetPresekResponse posaljiZahtev(Zahtev z) {
		Racun r = racunRep.findByBrojRacuna(z.getBrojRacuna());
		Banka b = r.banka;

		GetZahtevRequest zahtev = new GetZahtevRequest();

		zahtev.setZahtev(z);
		String uri = "http://localhost:" + b.port + "/ws";
		webServiceTemplate.setDefaultUri(uri);
		
		boolean parsovano = validator.parse(zahtev, "zahtev");
		if(!parsovano){
			System.out.println("----Nije validan zahtev----");
			return null;
		}
		System.out.println("------Poslat zahtev------");
		GetPresekResponse gp = (GetPresekResponse) webServiceTemplate.marshalSendAndReceive(zahtev);

		return gp;
	}

}
