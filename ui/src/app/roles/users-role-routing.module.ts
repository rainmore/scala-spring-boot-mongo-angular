import { Routes, RouterModule } from '@angular/router';
import { NgModule } from "@angular/core";

// import { UsersRoleFormComponent } from './users-role-form.component';
import { UsersRoleListComponent } from './users-role-list.component';

const routes: Routes = [
  {
    path: '',
    component: UsersRoleListComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsersRoleRoutingModule { }
