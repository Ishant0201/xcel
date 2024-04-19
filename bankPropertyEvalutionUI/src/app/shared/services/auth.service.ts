// auth.service.ts

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { AppDataSvc } from '../../app.data.svc';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loggedIn = false;
  private loginEndpoint = 'http://127.0.0.1:8080/login';

  constructor(private http: HttpClient, private appDataSvc: AppDataSvc) { }

  login(username: string, password: string): Observable<boolean> {

    return this.http.post<any>(this.loginEndpoint, { username, password }).pipe(
      map(response => {
        if (response && response.token) {

          localStorage.setItem('token', response.token);
          localStorage.setItem('user', JSON.stringify(username));

          this.appDataSvc.setProfileObs(true);
          this.loggedIn = true;

          return true;
        } else {
          return false;
        }
      })
    );
  }

  isLoggedIn(): boolean {
    return this.loggedIn;
  }

  logout(): void {
    localStorage.removeItem('token'); // Clear token from local storage
    this.loggedIn = false;
  }
}