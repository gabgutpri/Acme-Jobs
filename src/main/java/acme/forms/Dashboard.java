
package acme.forms;

import java.io.Serializable;
import java.util.Collection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	private static final long	serialVersionUID	= 1L;

	//Atributes

	Integer						totalAnnouncement;
	Integer						totalCompanyRecord;
	Integer						totalInvestorRecords;

	Double						minRewardsRequest;
	Double						maxRewardsRequest;
	Double						avgRewardsRequest;
	Double						stdRewardsRequest;

	Double						minRewardsOffer;
	Double						maxRewardsOffer;
	Double						avgRewardsOffer;
	Double						stdRewardsOffer;

	Collection<Object[]>		sectorNumberCompanyRecord;
	Collection<Object[]>		sectorNumberInvestorRecord;

	//Derived atributes

	//Relationships
}
