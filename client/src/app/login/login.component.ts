import {Component, OnInit} from '@angular/core';
import {redirectUrl} from '../constants/redirect.constant';
import {FormBuilder, FormGroup} from "@angular/forms";
import {HttpService} from "../service/http.service";
import {Router} from "@angular/router";
import {HttpHeaders} from "@angular/common/http";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {

  hide: boolean = true;
  form!: FormGroup;

  constructor(private router: Router,
              private httpService: HttpService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.form = this.formBuilder.group(
      {
        username: "",
        password: ""
      }
    );
  }

  submit() {
    console.log(this.form.getRawValue());

    const headers: HttpHeaders = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Basic Y2xpZW50OnNlY3JldA==',
    });
    const options = {
      headers: headers
    }
    this.httpService.doPost("http://localhost:9000/login", this.form.getRawValue(), options).subscribe();
  }

  redirect(): void {
    window.location.href = redirectUrl();
  }
}
