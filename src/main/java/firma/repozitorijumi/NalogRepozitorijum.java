package firma.repozitorijumi;

import org.springframework.data.jpa.repository.JpaRepository;

import firma.nalog.Nalog;

public interface NalogRepozitorijum extends JpaRepository<Nalog, Long> {

}
