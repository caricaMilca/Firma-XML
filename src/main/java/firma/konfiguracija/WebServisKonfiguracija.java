package firma.konfiguracija;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import org.springframework.xml.xsd.commons.CommonsXsdSchemaCollection;

@EnableWs
@Configuration
public class WebServisKonfiguracija extends WsConfigurerAdapter {

	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);

		return new ServletRegistrationBean(servlet, "/ws/*");
	}
	/*@Bean(name = "nalogBean")
	public DefaultWsdl11Definition defaultWsdl11Definition(@Qualifier("nalog") XsdSchema nalog){
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("nalogPort");
		wsdl11Definition.setLocationUri("/ws");
	//	wsdl11Definition.setTargetNamespace("http://Firma.com/ws/");
	//	wsdl11Definition.setSchemaCollection(schemaCollection);
		wsdl11Definition.setSchema(nalog);
		return wsdl11Definition;
	}*/
	
	@Bean(name = "firma")
	public DefaultWsdl11Definition defaultWsdl11Definition(CommonsXsdSchemaCollection schemaCollection)
			throws Exception {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("firmaPort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("http://firma/ws/");
		wsdl11Definition.setSchemaCollection(schemaCollection);
		return wsdl11Definition;
	}

	
	@Bean
	public CommonsXsdSchemaCollection schemeCollection() {
		CommonsXsdSchemaCollection collection = new CommonsXsdSchemaCollection(
				new Resource[] {new ClassPathResource("/nalog.xsd")});
		collection.setInline(true);
		return collection;
	}

	@Bean
	Jaxb2Marshaller jaxb2Marshaller() {
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setContextPaths("firma.nalog");
		return jaxb2Marshaller;
	}

	@Bean
	public WebServiceTemplate webServiceTemplate() {

		WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
		webServiceTemplate.setMarshaller(jaxb2Marshaller());
		webServiceTemplate.setUnmarshaller(jaxb2Marshaller());
		webServiceTemplate.setDefaultUri("http://localhost:9090/ws");

		return webServiceTemplate;
	}

	@Bean
	@Qualifier("nalog")
	public XsdSchema nalog() {
		return new SimpleXsdSchema(new ClassPathResource("nalog.xsd"));
	}
}
