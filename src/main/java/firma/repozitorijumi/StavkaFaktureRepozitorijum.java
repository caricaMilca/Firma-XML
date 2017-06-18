package firma.repozitorijumi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import firma.model.Faktura;
import firma.model.StavkaFakture;

public interface StavkaFaktureRepozitorijum extends JpaRepository<StavkaFakture, Long> {

	List<StavkaFakture> findByFaktura(Faktura findOne);

}
