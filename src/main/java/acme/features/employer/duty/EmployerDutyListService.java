
package acme.features.employer.duty;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class EmployerDutyListService implements AbstractListService<Employer, Duty> {
	// Internal state ---------------------------------------------------------

	@Autowired
	EmployerDutyRepository repository;


	// AbstractListService<Employer, Duty> interface --------------------

	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "percentage");
	}

	@Override
	public Collection<Duty> findMany(final Request<Duty> request) {
		assert request != null;

		//		int idDescriptor = request.getModel().getInteger("idDescriptor");

		Collection<Duty> result;
		result = this.repository.findManyAll();
		//result = this.repository.findMany(idDescriptor);

		return result;
	}
}
