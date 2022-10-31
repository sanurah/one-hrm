import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {take} from 'rxjs/operators';
import {AuthService} from '../service/auth.service';
import {SessionConstant} from "../constants/session.constant";
import {HttpService} from "../service/http.service";
import {HttpHeaders} from "@angular/common/http";

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss'],
})
export class AuthComponent implements OnInit {

  constructor(private authService: AuthService, private activatedRoute: ActivatedRoute, private httpService: HttpService) {
    this.setAuthorizationCode();
  }

  ngOnInit(): void {
    this.authService
    .getToken(this.authService.getCode())
    .pipe(take(1))
    .subscribe((tokens) => {

      console.log("token:::::", tokens);

      if ((tokens as any)?.id_token) {
        sessionStorage.setItem(SessionConstant.ID_TOKEN, (tokens as any)?.id_token);
        this.authService.setLoggedIn(true);
      }

      if ((tokens as any)?.refresh_token) {
        sessionStorage.setItem(SessionConstant.REFRESH_TOKEN, (tokens as any)?.refresh_token);
      }
    });
  }

  setAuthorizationCode() {
    this.activatedRoute.queryParams.subscribe((params) => {
      if (params?.['code']) {
        this.authService.setCode(params['code']);
        console.log('code =====', this.authService.getCode());
        this.getDemoInformation();
      }
    });
  }

  private getDemoInformation() {
    const token = sessionStorage.getItem('id_token');
    const bearerToken = `Bearer ${token}`;
    const options = {
      headers: new HttpHeaders({'Authorization': bearerToken}),
      responseType: 'text/plain'
    };

    this.httpService.doGet("http://localhost:8080/api/v1/user/4", options).pipe(take(1)).subscribe((content) => {
      console.log("yooo", content);
    })
  }

}
