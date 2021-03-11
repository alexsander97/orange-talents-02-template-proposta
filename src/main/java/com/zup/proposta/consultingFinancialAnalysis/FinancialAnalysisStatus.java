package com.zup.proposta.consultingFinancialAnalysis;

public enum FinancialAnalysisStatus {

    COM_RESTRICAO(ProposalStatus.NAO_ELEGIVEL), SEM_RESTRICAO(ProposalStatus.ELEGIVEL);

    private ProposalStatus proposalStatus;

    FinancialAnalysisStatus(ProposalStatus proposalStatus) {
        this.proposalStatus = proposalStatus;
    }

    public ProposalStatus getProposalStatus() {
        return proposalStatus;
    }
}
