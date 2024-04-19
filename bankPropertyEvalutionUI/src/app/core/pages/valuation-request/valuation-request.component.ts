import { Component } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { BankEvaluationApiService } from '../../../shared/services/bank-evaluation-api.service';
import { AppDataSvc } from '../../../app.data.svc';

@Component({
  selector: 'app-valuation-request',
  standalone: true,
  imports: [MatTableModule],
  templateUrl: './valuation-request.component.html',
  styleUrl: './valuation-request.component.scss'
})

export class ValuationRequestComponent {
  user: string = '';
  displayedColumns: string[] = ['referenceNo', 'receivedOn', 'borrowerName', 'fosRef', 'createdOn', 'modifiedOn', 'actions'];
  dataSource: any;

  constructor(private bankEvaluationApiService: BankEvaluationApiService, private appDataSvc: AppDataSvc) { }

  ngOnInit() {
    this.appDataSvc.getProfileObs().subscribe(profile => {
      if (profile) {
        this.user = JSON.parse(localStorage.getItem("user")!);
      }
    }, error => {
      console.error('Error fetching user profile:', error);
    });

    this.getAllPropertyEvaluation();
  }

  getAllPropertyEvaluation(): void {
    this.bankEvaluationApiService.getAllPropertyEvaluation("Ishant", this.user).subscribe(data => {
      this.dataSource = data;
    });
  }

  formatDate(inputDate: string): string {
    const date = new Date(inputDate);

    const formattedDate =
      ("0" + date.getDate()).slice(-2) + "/" +
      ("0" + (date.getMonth() + 1)).slice(-2) + "/" +
      date.getFullYear() + " " +
      ("0" + date.getHours()).slice(-2) + ":" +
      ("0" + date.getMinutes()).slice(-2);

    return formattedDate;
  }

  onButtonClick(e: any) {
    console.log("Button clicked", e);

  }
}
