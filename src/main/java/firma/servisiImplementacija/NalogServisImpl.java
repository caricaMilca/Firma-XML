package firma.servisiImplementacija;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;

import firma.model.Faktura;
import firma.model.Firma;
import firma.model.StatusFakture;
import firma.model.ZaglavljeFakture;
import firma.nalog.GetNalogRequest;
import firma.nalog.GetNalogResponse;
import firma.nalog.Nalog;
import firma.repozitorijumi.FakturaRepozitorijum;
import firma.repozitorijumi.NalogRepozitorijum;
import firma.servisi.NalogServis;
import xmlTransformacije.SAXValidator;

@Service
@Transactional
public class NalogServisImpl implements NalogServis {

	@Autowired
	HttpSession sesija;

	@Autowired
	FakturaRepozitorijum fakturaRepozitorijum;

	@Autowired
	NalogRepozitorijum nalogRepozitorijum;

	@Autowired
	WebServiceTemplate webServiceTemplate;

	SAXValidator validator = new SAXValidator();

	@Override
	public ResponseEntity<Faktura> posaljiNalog(Long id, Boolean hitno) {
		Faktura fak = fakturaRepozitorijum.findOne(id);
		ZaglavljeFakture zf = fak.zaglavljeFakture;
		Firma f = (Firma) sesija.getAttribute("firma");
		String idPoruke = UUID.randomUUID().toString();
		Nalog n = new Nalog(idPoruke, zf.nazivKupca, "svrha placanja", f.naziv, zf.datumRacuna, zf.datumValute,
				f.racuni.iterator().next().brojRacuna, BigInteger.valueOf(97L), "11111111111111111111",
				zf.uplataNaRacun, BigInteger.valueOf(97L), "22222222222222222222", zf.iznosZaUplatu, zf.oznakaValute,
				hitno);
		if (n.getIznos().compareTo(BigDecimal.valueOf(250000L)) > 1)
			n.setHitno(true);
		GetNalogRequest nalogZahtjev = new GetNalogRequest();
		nalogZahtjev.setNalog(n);
		String uri = "http://localhost:" + f.racuni.iterator().next().banka.port + "/ws";
		
		boolean parsovano = validator.parse(nalogZahtjev, "nalog");
<<<<<<< HEAD
		System.out.println("parsovano +   " + parsovano);
		if (!parsovano)
=======
		if(!parsovano)
>>>>>>> 7b0de61a7b96005824e60e0aee49d707616fb1b2
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		webServiceTemplate.setDefaultUri(uri);
<<<<<<< HEAD
		GetNalogResponse nr = (GetNalogResponse) webServiceTemplate.marshalSendAndReceive(nalogZahtjev);
		if (nr == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		fak.status = StatusFakture.POSLATA;
		nalogRepozitorijum.save(n);
		return new ResponseEntity<Faktura>(fakturaRepozitorijum.save(fak), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<List<Nalog>> sviNalozi() {
		return new ResponseEntity<List<Nalog>>(nalogRepozitorijum.findAll(), HttpStatus.OK);
	}

	
	@Override
	public ResponseEntity<?> kreirajHTMLNaloga(Long id) throws JAXBException, IOException, TransformerException {
		Nalog n = nalogRepozitorijum.findOne(id);
		kreirajFajl(n);
		TransformerFactory tFactory = TransformerFactory.newInstance();

		Source xslDoc = new StreamSource("src/main/resources/static/nalog.xsl");
		Source xmlDoc = new StreamSource("src/main/resources/static/nalog.xml");

		String outputFileName = "src/main/resources/static/nalog.html";
		OutputStream htmlFile = new FileOutputStream(outputFileName);

		Transformer transformer = tFactory.newTransformer(xslDoc);
		transformer.transform(xmlDoc, new StreamResult(htmlFile));
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	private File kreirajFajl(Nalog f) throws JAXBException, IOException {
		File file = new File("src/main/resources/static/nalog.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(Nalog.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(f, file);
		Path path = Paths.get("src/main/resources/static/nalog.xml");
		List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
		String stylesheet = "<?xml-stylesheet type=\"text/xsl\" href=\"nalog.xsl\"?>";
		lines.add(1, stylesheet);
		Files.write(path, lines, StandardCharsets.UTF_8);

		return file;
	}

	@Override
	public ResponseEntity<?> kreirajPDFNaloga(Long id)
			throws DocumentException, IOException, JAXBException, TransformerException {
		kreirajHTMLNaloga(id);
		String url = new File("src/main/resources/static/nalog.html").toURI().toURL().toString();
		System.out.println("" + url);
		String HTML_TO_PDF = "src/main/resources/static/nalog.pdf";
		OutputStream os = new FileOutputStream(HTML_TO_PDF);
		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocument(url);
		renderer.layout();
		renderer.createPDF(os);
		renderer.finishPDF();
		os.close();
		return new ResponseEntity<>(HttpStatus.CREATED);
=======
		System.out.println("-----Poslat nalog-----");
		webServiceTemplate.marshalSendAndReceive(nalogZahtjev);
		return new ResponseEntity<>(HttpStatus.OK) ;
>>>>>>> 7b0de61a7b96005824e60e0aee49d707616fb1b2
	}

}
