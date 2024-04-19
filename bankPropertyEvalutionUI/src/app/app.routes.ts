import { Routes } from '@angular/router';
import { HomeComponent } from './core/pages/home/home.component';
import { NewPropertyComponent } from './core/pages/new-property/new-property.component';
import { ValuationRequestComponent } from './core/pages/valuation-request/valuation-request.component';
import { LoginComponent } from './core/pages/login/login.component';
import { authGuard } from './core/guard/auth.guard';

export const routes: Routes = [
    {
        path: 'login',
        component: LoginComponent
    },
    {
        path: 'home',
        component: HomeComponent,
        canActivate: [authGuard]

    },
    {
        path: 'new-property',
        component: NewPropertyComponent,
        canActivate: [authGuard]
    },
    {
        path: 'valuation-request',
        component: ValuationRequestComponent,
        canActivate: [authGuard]
    },
    { path: '', redirectTo: '/login', pathMatch: 'full' },
    { path: '**', redirectTo: '/login' }
];
