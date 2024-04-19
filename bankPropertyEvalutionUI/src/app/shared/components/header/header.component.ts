import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { User } from '../../../core/models/user';
import { AppDataSvc } from '../../../app.data.svc';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})

export class HeaderComponent {
  user: User = new User;
  constructor(private router: Router, private authService: AuthService, private appDataSvc: AppDataSvc) { }

  ngOnInit(): void {
    this.appDataSvc.getProfileObs().subscribe(profile => {
      if (profile) {
        this.user.username = JSON.parse(localStorage.getItem("user")!);
      }
    });
  }

  OnHomeClick() {
    if (this.authService.isLoggedIn()) {
      this.router.navigateByUrl('/home');
    } else {
      this.router.navigateByUrl('/login');
    }
  }

}
