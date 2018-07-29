import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

import { RoleService } from '../_services/users';
console.log('It works here');
@Component({templateUrl: 'users-role-list.component.html'})
export class UsersRoleListComponent implements OnInit {
    loading = false;
    submitted = false;
    returnUrl: string;

    constructor(
        private router: Router,
        private roleService: RoleService) {}


    ngOnInit() {

    }

}
