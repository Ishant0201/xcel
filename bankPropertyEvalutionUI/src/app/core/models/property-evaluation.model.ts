export interface PropertyEvaluation {
  id: number;
  initiatorDetails: InitiatorDetails;
  referenceNo: string;
  receivedOn: string;
  createdOn: string;
  modifiedOn: string;
  createdBy: string;
  facilityDetails: FacilityDetails;
  propertyValuation: PropertyValuation;
  borrowerDetails: BorrowerDetails;
}

export interface InitiatorDetails {
  initiatorName: string;
  initiatorBusinessUnit: string;
  initiatorContactNo: number;
}

export interface FacilityDetails {
  typeOfFacility: string;
  category: string;
  purpose: string;
  termInMonths: number;
  currency: string;
  amount: number;
}

export interface PropertyValuation {
  fosReference: string;
  typeOfEvaluation: string;
}

export interface BorrowerDetails {
  mainBorrower: MainBorrower;
}

export interface MainBorrower {
  customerNumber: string;
  customerName: string;
  contactNumber: number;
  email: string;
  address: string;
}
