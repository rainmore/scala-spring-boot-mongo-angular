import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

import { RoleService } from '../../_services/users';
import { Role } from '../../_models/users/role';
import { Page } from '../../_models';

@Component({
    selector: 'app-users-roles',
    templateUrl: 'list.component.html'
})
export class ListComponent implements OnInit {

    page: Page<Role> = new Page();

    selectedRole: Role;

    constructor(
        private router: Router,
        private roleService: RoleService) {}


    ngOnInit() {
        this.load()
    }

    onSelect(role: Role): void {
        this.selectedRole = role;
    }

    public load() {
        this.roleService.getAll().subscribe((data:  Page<Role>) => {
            this.page  =  data;
            console.log(data);
        });
    }
}
