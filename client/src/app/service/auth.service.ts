import {HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpService} from './http.service';
import {tokenUrl} from '../constants/token';
import {Buffer} from 'buffer';

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  private code: string = '';

  constructor(private httpService: HttpService) {
  }

  setCode(code: string) {
    this.code = code;
  }

  getCode() {
    return this.code;
  }

  getToken(code: string): Observable<Object> {
    const clientId = 'client';
    const clientSecret = 'secret';
    const basicAuth = 'Basic ' + Buffer.from(`${clientId}:${clientSecret}`);
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
