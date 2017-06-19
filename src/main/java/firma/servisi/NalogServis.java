package firma.servisi;

import org.springframework.http.ResponseEntity;

public interface NalogServis {

	ResponseEntity<?> posaljiNalog(Long id);

}
