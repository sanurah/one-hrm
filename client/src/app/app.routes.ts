import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AuthComponent } from './auth/auth.component';

const routes: Routes = [
    { path: 'home', component: HomeComponent, pathMatch: 'full'},
    { path: 'auth', component: AuthComponent, pathMatch: 'full'},
    { path: 'authorized', redirectTo:'auth', pathMatch: 'full'},
    { path: 'login', redirectTo:'auth', pathMatch: 'full'},
    { path: '', redirectTo:'auth', pathMatch: 'full'}
];

export default routes;