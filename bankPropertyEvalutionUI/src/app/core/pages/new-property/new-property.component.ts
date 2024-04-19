import { Component } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BankEvaluationApiService } from '../../../shared/services/bank-evaluation-api.service';
import { InitiatorDetails } from '../../models/property-evaluation.model';
import { AppDataSvc } from '../../../app.data.svc';
import { MatTableModule } from '@angular/material/table';
import { FileDropComponent } from "../file-drop/file-drop.component";
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-property',
  standalone: true,
  templateUrl: './new-property.component.html',
  styleUrl: './new-property.component.scss',
  imports: [FormsModule, ReactiveFormsModule, MatTableModule, FileDropComponent]
})
export class NewPropertyComponent {
  user: string = '';
  initiatorDetails: InitiatorDetails | null = null;
  commentTableDataSource: any;
  displayedColumns: string[] = ['date', 'initiatorBusinessUnit', 'comment'];
  documentTableDataSource: any[] = [];
  displayedColumnsDocTable: string[] = ['docType', 'fileName', 'size', 'uploadedBy', 'uploadedOn', 'deletteFile']

  selectedDocumentType: string = '';
  requestInput: any;
  initiatorContactNo: number = 0;
  typeOfFacility: string = '';
  constructor(private bankEvaluationApiService: BankEvaluationApiService, private appDataSvc: AppDataSvc, private router: Router) { }

  ngOnInit() {
    this.appDataSvc.getProfileObs().subscribe(profile => {
      if (profile) {
        this.user = JSON.parse(localStorage.getItem("user")!);
        this.getInitiatorDetails();
      }
    }, error => {
      console.error('Error fetching user profile:', error);
    });

    this.requestInput = {
      facilityDetails: {
        typeOfFacility: "",
        category: "",
        purpose: "",
        termInMonths: 0,
        currency: "",
        amount: 0
      },
      propertyValuation: {
        fosReference: "",
        typeOfEvaluation: ""
      },
      borrowerDetails: {
        mainBorrower: {
          customerNumber: "",
          customerName: "",
          contactNumber: 0,
          email: "",
          address: ""
        },
        jointBorrowers: {
          customerNumber: "",
          customerName: "",
          contactNumber: 0,
          email: "",
          address: ""
        }
      },
      comment: ""
    };

    this.getCommentByUser();
  }

  getInitiatorDetails() {
    this.bankEvaluationApiService.getInitiatorDetails(this.user).subscribe(data => {
      if (data) {
        this.initiatorDetails = data;
      } else {
        console.warn('No initiator details found.');
      }
    }, error => {
      console.error('Error fetching initiator details:', error);
    });
  }

  getCommentByUser() {
    this.bankEvaluationApiService.getCommentsByUser(this.user).subscribe(data => {
      if (data) {
        this.commentTableDataSource = data;
      } else {
        console.warn('No comments found.');
      }
    }, error => {
      console.error('Error fetching comments:', error);
    });
  }

  onSubmit() {
    if (this.requestInput != null) {
      this.bankEvaluationApiService.addNewPropertyEvaluation(this.user, this.requestInput).subscribe(res => {
        if (res) {
          this.router.navigateByUrl('/login');
        }
      })
    }


  }

  onFileDetails(fileDetail: any) {
    console.log(fileDetail);
    const modifiedFileDetail = {
      ...fileDetail,
      docType: this.selectedDocumentType
    }
    console.log(modifiedFileDetail);
    this.documentTableDataSource = [...this.documentTableDataSource, modifiedFileDetail];

    this.selectedDocumentType = '';
  }

  deleteFile(fileName: string) {
    this.documentTableDataSource = this.documentTableDataSource.filter((file: any) => file.fileName !== fileName);
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

}
