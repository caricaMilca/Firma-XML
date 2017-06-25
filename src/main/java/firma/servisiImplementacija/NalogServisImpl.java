package firma.servisiImplementacija;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.client.core.WebServiceTemplate;

import firma.model.Faktura;
import firma.model.Firma;
import firma.model.ZaglavljeFakture;
import firma.nalog.GetNalogRequest;
import firma.nalog.Nalog;
import firma.repozitorijumi.FakturaRepozitorijum;
import firma.servisi.NalogServis;

@Service
@Transactional
public class NalogServisImpl implements NalogServis {

	@Autowired
	HttpSession sesija;

	@Autowired
	FakturaRepozitorijum fakturaRepozitorijum;
	
	@Autowired
	WebServiceTemplate webServiceTemplate;
	
	@Override
	public ResponseEntity<?> posaljiNalog(Long id, Boolean hitno) {
		Faktura fak = fakturaRepozitorijum.findOne(id);
		ZaglavljeFakture zf = fak.zaglavljeFakture;
		Firma f = (Firma) sesija.getAttribute("firma");
		String idPoruke = UUID.randomUUID().toString();
		Nalog n = new Nalog(idPoruke, zf.nazivKupca, "svrha placanja", f.naziv, zf.datumRacuna, zf.datumValute,
				f.racuni.iterator().next().brojRacuna, BigInteger.valueOf(97L), "11111111111111111111",
				zf.uplataNaRacun, BigInteger.valueOf(97L), "2222222222222222222222", zf.iznosZaUplatu, zf.oznakaValute,
				hitno);
		if (n.getIznos().compareTo(BigDecimal.valueOf(250000L)) > 1)
			n.setHitno(true);
		GetNalogRequest nalogZahtjev = new GetNalogRequest();
		nalogZahtjev.setNalog(n);

		String uri = "http://localhost:" + f.racuni.iterator().next().banka.port + "/ws";
		System.out.println(uri + " aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		webServiceTemplate.setDefaultUri(uri);
		webServiceTemplate.marshalSendAndReceive(nalogZahtjev);
		return null;
	}

}
