import {Routes} from '@angular/router';
import {HomeComponent} from './home/home.component';
import {AuthComponent} from './security/auth/auth.component';
import {RedirectedToAuthServerGuard} from "./security/guard/redirected-to-auth-server.guard";
import {redirectUrl} from "./constants/redirect.constant";

const routes: Routes = [
  {
    path: 'login',
    component: RedirectedToAuthServerGuard,
    pathMatch: 'full',
    canActivate: [RedirectedToAuthServerGuard],
    data: {
      authServerUrl: redirectUrl()
    }
  },
  {path: 'home', component: HomeComponent, pathMatch: 'full'},
  {path: 'auth', component: AuthComponent, pathMatch: 'full'},
  {path: 'authorized', redirectTo: 'auth', pathMatch: 'full'},
  {path: '', redirectTo: 'home', pathMatch: 'full'},
];

export default routes;
