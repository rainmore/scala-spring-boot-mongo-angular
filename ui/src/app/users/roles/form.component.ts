import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

import { RoleService } from '../../_services/users';
import { Role } from '../../_models';

@Component({templateUrl: 'users-role-form.component.html'})
export class FormComponent implements OnInit {
    roleForm: FormGroup;
    role: Role = {id: '1', name: 'Admin', isActive: true}

    loading = false;
    submitted = false;
    returnUrl: string;

    constructor(
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private roleService: RoleService
    ) {}

    ngOnInit() {
        this.roleForm = new FormGroup({
            'name': new FormControl(this.role.name, [
                Validators.required,
                Validators.maxLength(100)
            ]),
            'isActive': new FormControl(this.role.isActive)
        });
    }

    onSubmit() {

    }

    // convenience getter for easy access to form fields
    get f() { return this.roleForm.controls; }

    get name() { return this.roleForm.get('name'); }

    get power() { return this.roleForm.get('isActive'); }
}
