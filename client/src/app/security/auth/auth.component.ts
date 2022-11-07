import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {take} from 'rxjs/operators';
import {AuthService} from '../../service/auth.service';
import {SessionConstant} from "../../constants/session.constant";

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss'],
})
export class AuthComponent implements OnInit {

  constructor(private authService: AuthService, private activatedRoute: ActivatedRoute, private router: Router) {
    this.setAuthorizationCode();
  }

  ngOnInit(): void {
    this.authService
    .getToken(this.authService.code)
    .pipe(take(1))
    .subscribe((tokens) => {

      console.log("token:::::", tokens);

      if ((tokens as any)?.id_token) {
        sessionStorage.setItem(SessionConstant.ID_TOKEN, (tokens as any)?.id_token);
        this.authService.isLoggedIn = true;
      }

      if ((tokens as any)?.refresh_token) {
        sessionStorage.setItem(SessionConstant.REFRESH_TOKEN, (tokens as any)?.refresh_token);
      }
    });
    this.router.navigate(["/home"]).then(r => console.log("went home"));
  }

  setAuthorizationCode() {
    this.activatedRoute.queryParams.subscribe((params) => {
      if (params?.['code']) {
        this.authService.code = params['code'];
        console.log('code =====', this.authService.code);
      }
    });
  }

}
