import {Component, OnInit} from '@angular/core';
import {AuthService} from "../service/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  constructor(private authService: AuthService, private router: Router) {
  }

  ngOnInit(): void {
  }

  account() {
    const employeeId = sessionStorage.getItem("");
    this.router.navigate(["/employee"]).then(r => console.log("navigate login"));
  }

  logout(): void {
    this.authService.revokeToken();
    this.router.navigate(["/login"]).then(r => console.log("navigate login"));
  }
}
