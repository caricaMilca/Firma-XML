package firma.servisi;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;

import org.springframework.http.ResponseEntity;

import com.lowagie.text.DocumentException;

import firma.model.Faktura;
import firma.model.StavkaFakture;

public interface FakturaServis {

	ResponseEntity<List<Faktura>> ulazneFakture();

	ResponseEntity<Faktura> registracijaFakture(StavkaFakture sf, Long id);

	ResponseEntity<List<Faktura>> sveFakture();

	ResponseEntity<?> slanjeFakture(Long id);

	ResponseEntity<?> primiFakturu(Faktura f);

	ResponseEntity<?> kreirajHTMLFakture(Long id) throws JAXBException, IOException, TransformerException;

	ResponseEntity<?> kreirajPDFFakture(Long id) throws DocumentException, IOException, JAXBException, TransformerException;
	

}
