import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

import { RoleService } from '../../_services/users';
import { Role } from '../../_models/users/role';

console.log('It works here');
@Component({
    selector: 'app-users-roles',
    templateUrl: 'list.component.html'
})
export class ListComponent implements OnInit {

    roles: Role[] = [
        {id: '1', name: 'Admin', isActive: true},
        {id: '2', name: 'User', isActive: true}
    ];

    selectedRole: Role;

    loading = false;
    submitted = false;
    returnUrl: string;

    constructor(
        private router: Router,
        private roleService: RoleService) {}


    ngOnInit() {

    }

    onSelect(role: Role): void {
        this.selectedRole = role;
    }

}
