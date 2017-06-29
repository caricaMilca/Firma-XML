package xmlTransformacije;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXValidator extends DefaultHandler {
	private static SAXParserFactory parserFactory;

	private static SchemaFactory schemaFactory;

	private static JAXBContext jaxbContext;

	static {
		parserFactory = SAXParserFactory.newInstance();
		parserFactory.setNamespaceAware(true);

		// Omogućuje validaciju u odnosu na XML šemu
		schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	}

	public boolean parse(Object object, String schemaName) {
		try {
			// Validacija u odnosu na XML šemu
			File file = new File(schemaName + ".xml");
			Schema schema = schemaFactory.newSchema(new File("src/main/resources/" + schemaName + ".xsd"));
			jaxbContext = JAXBContext.newInstance(object.getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(object, file);
			parserFactory.setSchema(schema);
			Source xmlFile = new StreamSource(file);
			Validator validator = schema.newValidator();
			validator.validate(xmlFile);

		} catch (SAXException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
