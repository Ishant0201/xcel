import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../../shared/services/auth.service';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn = (route, state) => {
  const authService: AuthService = inject(AuthService);
  const router = new Router();

  if (authService.isLoggedIn()) {
    return true;
  } else {
    router.navigateByUrl('/login');
    return false;
  }
};
