package firma.repozitorijumi;

import org.springframework.data.jpa.repository.JpaRepository;

import firma.model.ZaglavljeFakture;

public interface ZaglavljeFaktureRepozitorijum extends JpaRepository<ZaglavljeFakture, Long> {

}
