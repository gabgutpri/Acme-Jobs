
package acme.features.auditor.job;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/auditor/job/")
public class AuditorJobController extends AbstractController<Auditor, Job> {
	// Internal state ---------------------------------------------------------

	@Autowired
	private AuditorJobListWrittenService	writtenService;

	@Autowired
	private AuditorJobListNotWrittenService	notWrittenService;

	@Autowired
	private AuditorJobShowService			showService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.WRITTEN, BasicCommand.LIST, this.writtenService);
		super.addCustomCommand(CustomCommand.NOT_WRITTEN, BasicCommand.LIST, this.notWrittenService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
