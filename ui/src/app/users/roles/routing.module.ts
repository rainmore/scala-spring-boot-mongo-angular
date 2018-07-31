import { Routes, RouterModule } from '@angular/router';
import { NgModule } from "@angular/core";

import { FormComponent } from './form.component';
import { ListComponent } from './list.component';
import { PageNotFoundComponent } from '../../errors';

const routes: Routes = [
    {
        path: '',
        component: ListComponent
    },
    {
        path: 'add',
        component: FormComponent
    },
    {
        path: ':id',
        component: FormComponent
    },
    {
        path: '**',
        component: PageNotFoundComponent
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class RoutingModule {
}
