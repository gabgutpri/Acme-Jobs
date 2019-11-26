
package acme.features.employer.duty;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.dutys.Duty;
import acme.entities.requests.Request_;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerDutyRepository extends AbstractRepository {

	@Query("select d from Duty d where d.id =?1")
	Request_ findOnebyId(int id);

	@Query("select d from Duty d")
	Collection<Duty> findManyAll();

}