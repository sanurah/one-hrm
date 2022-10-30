import {HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpService} from './http.service';
import {tokenUrl} from '../constants/token.constant';

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  private code: string = '';
  private isLoggedIn: boolean = false;

  constructor(private httpService: HttpService) {
  }

  setCode(code: string): void {
    this.code = code;
  }

  getCode(): string {
    return this.code;
  }

  getLoggedIn(): boolean {
    return this.isLoggedIn;
  }

  setLoggedIn(loggedIn: boolean): void {
    this.isLoggedIn = loggedIn;
  }

  getToken(code: string): Observable<Object> {
    const headers: HttpHeaders = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Basic Y2xpZW50OnNlY3JldA==',
    });
    const options = {
      headers: headers,
    };
    return this.httpService.doPost(tokenUrl(code), null, options);
  }
}
