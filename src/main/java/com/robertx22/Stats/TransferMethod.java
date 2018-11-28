package com.robertx22.stats;

public class TransferMethod {

    public TransferMethod(Stat conv, Stat thatBenefits) {
	this.converted = conv;
	this.statThatBenefits = thatBenefits;
    }

    public Stat converted;
    public Stat statThatBenefits;
}
