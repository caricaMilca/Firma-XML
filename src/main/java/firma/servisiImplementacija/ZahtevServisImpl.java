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
	public GetPresekResponse posaljiZahtev(Zahtev z) {
		System.out.println("firma zahtev servis impl");
		Racun r = racunRep.findByBrojRacuna(z.getBrojRacuna());
		Banka b = r.banka;
		System.out.println(z.getDatumZahteva());
		GetZahtevRequest zahtev = new GetZahtevRequest();

		zahtev.setZahtev(z);
		System.out.println(zahtev.getZahtev().getDatumZahteva());
		String uri = "http://localhost:" + b.port + "/ws";
		System.out.println(" uri: " + uri);
		webServiceTemplate.setDefaultUri(uri);
		GetPresekResponse gp = (GetPresekResponse) webServiceTemplate.marshalSendAndReceive(zahtev);

		return gp;
	}

}
