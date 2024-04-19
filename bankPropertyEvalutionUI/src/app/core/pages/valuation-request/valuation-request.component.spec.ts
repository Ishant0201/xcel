import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ValuationRequestComponent } from './valuation-request.component';

describe('ValuationRequestComponent', () => {
  let component: ValuationRequestComponent;
  let fixture: ComponentFixture<ValuationRequestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ValuationRequestComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ValuationRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
