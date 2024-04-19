import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class AppDataSvc {
    private profileObs$: BehaviorSubject<any> = new BehaviorSubject(null);

    getProfileObs(): Observable<any> {
        return this.profileObs$.asObservable();
    }

    setProfileObs(setpr: any) {
        this.profileObs$.next(setpr);
    }
}
