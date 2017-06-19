package firma.servisiImplementacija;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
import org.springframework.web.client.RestTemplate;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;

import firma.model.Faktura;
import firma.model.Firma;
import firma.model.StavkaFakture;
import firma.repozitorijumi.FakturaRepozitorijum;
import firma.repozitorijumi.FirmaRepozitorijum;
import firma.repozitorijumi.ZaglavljeFaktureRepozitorijum;
import firma.servisi.FakturaServis;

@Service
@Transactional
public class FakturaServisImpl implements FakturaServis {

	@Autowired
	FakturaRepozitorijum fakturaRepozitorijum;

	@Autowired
	FirmaRepozitorijum firmaRepozitorijum;

	@Autowired
	HttpSession sesija;

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
		if (f == null) {
			f = new Faktura();
			f.zaglavljeFakture = zaglavljeFaktureRepozitorijum.findOne(id);
		}
		sf.vrijednost = sf.jedinicnaCijena.multiply(sf.kolicina);
		sf.iznosRabata = sf.vrijednost.multiply(sf.procenatRabata.divide(new BigDecimal(100)));
		sf.umanjenoZaRabat = sf.vrijednost.add(sf.iznosRabata.negate());
		sf.ukupanPorez = sf.umanjenoZaRabat.multiply(new BigDecimal(0.17));
		f.stavke.add(sf);
		sf.faktura = f;
		return new ResponseEntity<Faktura>(fakturaRepozitorijum.save(f), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<Faktura>> sveFakture() {
		return new ResponseEntity<List<Faktura>>(fakturaRepozitorijum.findAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> slanjeFakture(Long id) {
		Faktura f = fakturaRepozitorijum.findOne(id);
		Firma m = firmaRepozitorijum.findByPib(f.zaglavljeFakture.pibDobavljaca);
		final String putanja = "http://localhost:" + m.port + "/faktura/primiFakturu";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(putanja, f, Faktura.class);
		fakturaRepozitorijum.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> primiFakturu(Faktura f) {
		fakturaRepozitorijum.save(f);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<?> kreirajHTMLFakture(Long id) throws JAXBException, IOException, TransformerException {
		Faktura f = fakturaRepozitorijum.findOne(id);
		File fajl= kreirajFajl(f);
		TransformerFactory tFactory = TransformerFactory.newInstance();

        Source xslDoc = new StreamSource("faktura.xsl");
        Source xmlDoc = new StreamSource("faktura.xml");

        String outputFileName = "faktura.html";
        OutputStream htmlFile = new FileOutputStream(outputFileName);

        Transformer transformer = tFactory.newTransformer(xslDoc);
        transformer.transform(xmlDoc, new StreamResult(htmlFile));
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	private File kreirajFajl(Faktura f) throws JAXBException, IOException{
		File file = new File("faktura.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(Faktura.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(f, file);
		Path path = Paths.get("faktura.xml");
		List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
		String stylesheet = "<?xml-stylesheet type=\"text/xsl\" href=\"faktura.xsl\"?>";
		lines.add(1, stylesheet);
		Files.write(path, lines, StandardCharsets.UTF_8);
		
		return file;
	}

	@Override
	public ResponseEntity<?> kreirajPDFFakture(Long id) throws DocumentException, IOException, JAXBException, TransformerException {
		kreirajHTMLFakture(id);
        String url = new File("faktura.html").toURI().toURL().toString();
        System.out.println(""+url);
        String HTML_TO_PDF = "ConvertedFile.pdf";
        OutputStream os = new FileOutputStream(HTML_TO_PDF);       
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocument(url);      
        renderer.layout();
        renderer.createPDF(os);        
        os.close();
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
