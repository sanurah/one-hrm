import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {AuthService} from "../service/auth.service";


@Injectable({
  providedIn: 'root'
})
export class LoggedInGuard implements CanActivate {

  constructor(private authService: AuthService, private router: Router) {
  };

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {

    let canActivate: boolean = false;

    let isLoggedIn = this.authService.getLoggedIn();
    if (isLoggedIn) {
      canActivate = true;
    } else {
      this.router.navigate(["/login"]).then(() => canActivate = false);
    }
    return canActivate;
  }

}
