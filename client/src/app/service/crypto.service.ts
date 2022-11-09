import {Injectable} from '@angular/core';
import {SessionConstant} from "../constants/session.constant";

@Injectable({
  providedIn: 'root'
})
export class CryptoService {

  constructor() {
  }

  static generateCodeVerifier() {
    const codeVerifier = self.btoa(self.crypto.getRandomValues(new Int8Array(16)).toString())
    .replace(/=/g, '')
    .replace(/\+/g, '-')
    .replace(/\//g, '_');

    sessionStorage.setItem(SessionConstant.CODE_VERIFIER, codeVerifier);
    console.log("code-verifier:: ", codeVerifier);
    return codeVerifier;
  }

  static async generateCodeChallenge(): Promise<string> {
    const codeVerifier = sessionStorage.getItem(SessionConstant.CODE_VERIFIER);
    const msgBuffer = new TextEncoder().encode(codeVerifier || '');
    const hashBuffer = await crypto.subtle.digest('SHA-256', msgBuffer);
    const hashArray = Array.from(new Uint8Array(hashBuffer));
    return hashArray.map(b => b.toString(16).padStart(2, '0')).join('');
  }
}
