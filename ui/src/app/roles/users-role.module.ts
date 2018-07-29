import { NgModule }      from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule }    from '@angular/forms';

import { fakeBackendProvider } from '../_helpers';

import { UsersRoleListComponent }  from './users-role-list.component';
// import { UsersRoleFormComponent }  from './users-role-form.component';
import { UsersRoleRoutingModule } from './users-role-routing.module';

import { JwtInterceptor, ErrorInterceptor } from '../_helpers';
import { RoleService } from '../_services/users';

@NgModule({
    imports: [
        CommonModule,
        ReactiveFormsModule,
        UsersRoleRoutingModule
    ],
    declarations: [
        UsersRoleListComponent
        // UsersRoleFormComponent,
    ],
    providers: [
        // AuthGuard,
        // AlertService,
        // AuthenticationService,
        RoleService
        // { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
        // { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
        //
        // // provider used to create fake backend
        // fakeBackendProvider
    ]
})
export class UsersRoleModule { }