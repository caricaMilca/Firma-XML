package firma.servisi;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;

import org.springframework.http.ResponseEntity;

import com.lowagie.text.DocumentException;

import firma.model.Faktura;
import firma.nalog.Nalog;

public interface NalogServis {

	ResponseEntity<Faktura> posaljiNalog(Long id, Boolean hitno);

	ResponseEntity<?> kreirajHTMLNaloga(Long id) throws JAXBException, IOException, TransformerException;

	ResponseEntity<?> kreirajPDFNaloga(Long id)
			throws DocumentException, IOException, JAXBException, TransformerException;

	ResponseEntity<List<Nalog>> sviNalozi();
}
