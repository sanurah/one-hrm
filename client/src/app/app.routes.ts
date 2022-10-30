import {Routes} from '@angular/router';
import {HomeComponent} from './home/home.component';
import {AuthComponent} from './auth/auth.component';
import {LoginComponent} from './login/login.component';
import {RedirectedToAuthServerGuard} from "./guard/redirected-to-auth-server.guard";
import {LoggedInGuard} from "./guard/logged-in.guard";

const routes: Routes = [
  {path: 'login', component: LoginComponent, pathMatch: 'full', canActivate: [RedirectedToAuthServerGuard]},
  {path: 'home', component: HomeComponent, pathMatch: 'full', canActivate: [LoggedInGuard]},
  {path: 'auth', component: AuthComponent, pathMatch: 'full'},
  {path: 'authorized', redirectTo: 'auth', pathMatch: 'full'},
  {path: '', redirectTo: 'home', pathMatch: 'full'},
];

export default routes;
