import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {take} from 'rxjs/operators';
import {AuthService} from '../service/auth.service';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss'],
})
export class AuthComponent implements OnInit {

  constructor(private authService: AuthService, private activatedRoute: ActivatedRoute) {
    this.setAuthorizationCode();
  }

  ngOnInit(): void {
    this.authService
    .getToken(this.authService.getCode())
    .pipe(take(1))
    .subscribe((tokens) => {

      console.log("token:::::", tokens);

      if ((tokens as any)?.id_token) {
        sessionStorage.setItem('id_token', (tokens as any)?.id_token);
      }

      if ((tokens as any)?.refresh_token) {
        sessionStorage.setItem('refresh_token', (tokens as any)?.refresh_token);
      }
    });
  }

  setAuthorizationCode() {
    this.activatedRoute.queryParams.subscribe((params) => {
      if (params?.['code']) {
        this.authService.setCode(params['code']);
        console.log('code =====', this.authService.getCode());
      }
    });
  }

}
