import {HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpService} from './http.service';
import {tokenUrl} from '../constants/token.constant';

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  private _code: string = '';
  private _isLoggedIn: boolean = false;

  constructor(private httpService: HttpService) {
  }

  set code(code: string) {
    this._code = code;
  }

  get code(): string {
    return this._code;
  }

  get isLoggedIn(): boolean {
    return this._isLoggedIn;
  }

  set isLoggedIn(isLoggedIn: boolean) {
    this._isLoggedIn = isLoggedIn;
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
