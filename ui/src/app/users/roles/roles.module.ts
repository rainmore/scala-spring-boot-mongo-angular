import { NgModule }      from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule }    from '@angular/forms';
import { HTTP_INTERCEPTORS } from '@angular/common/http';

import { fakeBackendProvider } from '../../_helpers';
import { JwtInterceptor, ErrorInterceptor } from '../../_helpers';

import { PageNotFoundComponent } from '../../errors';
import { ListComponent }  from './list.component';
import { FormComponent }  from './form.component';
import { RoutingModule } from './routing.module';

import { RoleService } from '../../_services/users';


@NgModule({
    imports: [
        CommonModule,
        ReactiveFormsModule,
        RoutingModule
    ],
    declarations: [
        FormComponent,
        ListComponent,
        PageNotFoundComponent
    ],
    providers: [
        // AuthGuard,
        // AlertService,
        // AuthenticationService,
        RoleService,
        // { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
        { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true }
        //
        // // provider used to create fake backend
        // fakeBackendProvider
    ]
})
export class RolesModule { }
