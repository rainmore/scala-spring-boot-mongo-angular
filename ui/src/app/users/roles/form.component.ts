import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

import { RoleService } from '../../_services/users';
import { Role } from '../../_models/users';

@Component({
    selector: 'app-users-roles-form',
    templateUrl: 'form.component.html'
})
export class FormComponent implements OnInit {
    roleForm: FormGroup;
    role: Role = new Role;

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
        this.loading = true;
        console.log(this.roleForm.valid);
        console.log(this.roleForm.value);
        if (this.roleForm.valid) {
            this.roleService.save(this.roleForm.value).subscribe((response) => {
                this.loading = false;
                console.log(response);
            })
        }
        else {
            this.loading = false;
        }
    }

    // convenience getter for easy access to form fields
    get f() { return this.roleForm.controls; }

    get name() { return this.roleForm.get('name'); }

    get isActive() { return this.roleForm.get('isActive'); }
}
