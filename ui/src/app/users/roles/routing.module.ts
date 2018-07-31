import { Routes, RouterModule } from '@angular/router';
import { NgModule } from "@angular/core";

// import { UsersRoleFormComponent } from './users-role-form.component';
import { ListComponent } from './list.component';

const routes: Routes = [
  {
    path: '',
    component: ListComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RoutingModule { }
