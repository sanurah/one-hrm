import {Component, OnInit} from '@angular/core';
import {redirectUrl} from '../constants/redirect';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  constructor() {
  }

  ngOnInit(): void {
  }

  redirect(): void {
    window.location.href = redirectUrl();
  }
}
