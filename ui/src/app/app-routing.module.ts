import { Routes, RouterModule } from '@angular/router';

// import { HomeComponent } from './home';
// import { LoginComponent } from './login';
// import { RegisterComponent } from './register';
// import { AuthGuard } from './_guards';
import { NgModule } from '@angular/core';

const routes: Routes = [
    // { path: '', component: HomeComponent/*, canActivate: [AuthGuard] */},
    // { path: 'roles', component: UsersRoleModule},
    {
        path: 'users-roles',
        loadChildren: './roles/users-role.module#UsersRoleModule'
    },
    // { path: 'login', component: LoginComponent },
    // { path: 'register', component: RegisterComponent },

    // otherwise redirect to home
    { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule],
  providers: []
})
export class AppRoutingModule { }
