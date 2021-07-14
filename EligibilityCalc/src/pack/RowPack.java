package pack;

import java.io.File;

public class RowPack {
	boolean is10off;
	
	String plancode;
	Date premiumDate;
	
	int policyNum;
	
	Agent agent1;
	Agent agent2;
	Agent agent3;
	
	double premiumAmt;
	
	double newPremium1;
	double newPremium2;
	double newPremium3;
	
	
	public RowPack(int policyNum, String planCode, String premiumDate, Agent agent1, Agent agent2, Agent agent3, double premiumAmt) {
		this.policyNum = policyNum;
		this.premiumDate = new Date(premiumDate);
		this.agent1 = agent1;
		this.agent2 = agent2;
		this.agent3 = agent3;
		this.premiumAmt = premiumAmt;
		if (planCode.contains("s")) {
			is10off = true;
		}
		else {
			is10off = false;
		}
	}
	
	public String toString() {
		return ("Row Object with... PolicyNum: "+policyNum+" Premium Date: "+premiumDate);
	}
}
