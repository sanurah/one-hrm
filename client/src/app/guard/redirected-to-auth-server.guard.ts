import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot} from '@angular/router';
import {redirectUrl} from "../constants/redirect.constant";
import {SessionConstant} from "../constants/session.constant";


@Injectable({
  providedIn: 'root'
})
export class RedirectedToAuthServerGuard implements CanActivate {

  constructor() {
  };

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {

    let canActivate: boolean = false;

    let isRedirected = sessionStorage.getItem(SessionConstant.REDIRECTED);
    if (isRedirected) {
      canActivate = true;
    } else {
      sessionStorage.setItem(SessionConstant.REDIRECTED, "true");
      window.location.href = redirectUrl();
    }
    return canActivate;
  }

}
