package firma.repozitorijumi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import firma.model.Firma;

public interface FirmaRepozitorijum extends JpaRepository<Firma, Long> {

	Firma findByPortAndLozinka(String port, String lozinka);

	Firma findByPib(String pibDobavljaca);

	@Query("select f from Firma f where id!=?1")
	List<Firma> findAllExceptOwn(Long id);

}
