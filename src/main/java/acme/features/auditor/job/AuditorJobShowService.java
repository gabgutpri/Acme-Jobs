
package acme.features.auditor.job;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.descriptors.Descriptor;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuditorJobShowService implements AbstractShowService<Auditor, Job> {
	// Internal state ---------------------------------------------------------

	@Autowired
	AuditorJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		boolean result = false;
		int jobId;

		Principal principal;
		Collection<Auditor> auditor;
		Job job;
		Collection<AuditRecord> auditRecords;

		jobId = request.getModel().getInteger("id");
		job = this.repository.findOneJobById(jobId);
		auditRecords = this.repository.findAllAuditRecord();
		auditor = auditRecords.stream().filter(x -> x.getJob().equals(job)).map(x -> x.getAuditor()).collect(Collectors.toList());
		principal = request.getPrincipal();
		for (Auditor a : auditor) {
			if (result = a.getUserAccount().getId() == principal.getAccountId()) {
				result = true;
				break;
			}
		}
		return result;
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Descriptor descriptor = entity.getDescriptor();
		request.unbind(entity, model, "title", "reference", "moreInfo", "salary", "description", "finalMode", "deadline");
		model.setAttribute("descriptor", descriptor.getDescription());
	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;

		Job result;
		int id;

		id = request.getModel().getInteger("id");

		result = this.repository.findOneJobById(id);

		return result;
	}
}
