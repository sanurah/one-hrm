import {Routes} from '@angular/router';
import {HomeComponent} from './home/home.component';
import {AuthComponent} from './auth/auth.component';
import {LoginComponent} from './login/login.component';

const routes: Routes = [
  {path: 'login', component: LoginComponent, pathMatch: 'full'},
  {path: 'home', component: HomeComponent, pathMatch: 'full'},
  {path: 'auth', component: AuthComponent, pathMatch: 'full'},
  {path: 'authorized', redirectTo: 'auth', pathMatch: 'full'},
  {path: '', redirectTo: 'home', pathMatch: 'full'},
];

export default routes;
