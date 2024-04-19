import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { InitiatorDetails, PropertyEvaluation } from '../../core/models/property-evaluation.model';

@Injectable({
  providedIn: 'root'
})
export class BankEvaluationApiService {
  private apiBaseUrl = 'http://127.0.0.1:8080/api';

  constructor(private http: HttpClient) { }

  getAllPropertyEvaluation(initiatorName: string, initatorBusinessUnit: string): Observable<PropertyEvaluation[]> {
    const apiUrl = `${this.apiBaseUrl}/property-evaluation/getAllProperties?initatorName=${initiatorName}&initatorBusinessUnit=${initatorBusinessUnit}`;
    return this.http.get<PropertyEvaluation[]>(apiUrl);
  }

  getInitiatorDetails(initatorBusinessUnit: string): Observable<InitiatorDetails> {
    const apiUrl = `${this.apiBaseUrl}/initiator/getInitiatorByName?initatorBusinessUnit=${initatorBusinessUnit}`;
    return this.http.get<InitiatorDetails>(apiUrl);
  }

  addNewPropertyEvaluation(initatorBusinessUnit: string, payload: PropertyEvaluation): Observable<any> {
    const apiUrl = `${this.apiBaseUrl}/property-evaluation/create?initatorBusinessUnit=${initatorBusinessUnit}`;
    return this.http.post<any>(apiUrl, payload)
  }

  getCommentsByUser(initatorBusinessUnit: string): Observable<any> {
    const apiUrl = `${this.apiBaseUrl}/comments?initiatorBusinessUnit=${initatorBusinessUnit}`;
    return this.http.get<any>(apiUrl)
  }
}