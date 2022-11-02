import {Component, OnInit} from '@angular/core';
import {HttpHeaders} from "@angular/common/http";
import {take} from "rxjs/operators";
import {HttpService} from "../service/http.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  demoInfo!: Object;

  constructor(private httpService: HttpService) {
  }

  ngOnInit(): void {
    this.getDemoInformation();
  }

  private getDemoInformation() {
    const token = sessionStorage.getItem('id_token');
    const bearerToken = `Bearer ${token}`;
    const options = {
      headers: new HttpHeaders({'Authorization': bearerToken}),
      responseType: 'text/plain'
    };
    console.log("token is here", token);

    this.httpService.doGet("http://localhost:8080/api/v1/user/4", options).pipe(take(1)).subscribe((content) => {
      this.demoInfo = content;
    })
  }
}
