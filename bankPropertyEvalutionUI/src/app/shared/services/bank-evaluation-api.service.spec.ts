import { TestBed } from '@angular/core/testing';

import { BankEvaluationApiService } from './bank-evaluation-api.service';

describe('BankEvaluationApiService', () => {
  let service: BankEvaluationApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BankEvaluationApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
