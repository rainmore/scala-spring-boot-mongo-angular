import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { FormControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { tap } from 'rxjs/operators';

import { RoleService } from '../../_services/users';
import { Role } from '../../_models/users';
import { Observable, throwError } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
    selector: 'app-users-roles-form',
    templateUrl: 'form.component.html'
})
export class FormComponent implements OnInit {
    form: FormGroup;
    role: Role = {id: undefined, name: undefined, active: true};


    loading = false;
    submitted = false;
    returnUrl: string;

    constructor(
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private roleService: RoleService,
        private location: Location
    ) {}

    ngOnInit() {
        this.form = new FormGroup({
            'name': new FormControl(this.role.name, [
                Validators.required,
                Validators.maxLength(100)
            ]),
            'active': new FormControl(this.role.active)
        });

        this.getRole();
    }

    onSubmit() {
        this.loading = true;
        if (this.form.valid) {
            const id = this.getId();
            this.role = {...this.role, ...this.form.value};

            if (this.role.id) {
                this.roleService.update(this.role).subscribe((response) => {
                    this.loading = false;
                });
            }
            else {
                this.roleService.save(this.role).subscribe((response) => {
                    this.loading = false;
                });
            }
        }
        else {
            this.loading = false;
        }
    }

    // convenience getter for easy access to form fields
    get f() { return this.form.controls; }

    get name() { return this.form.get('name'); }

    get isActive() { return this.form.get('isActive'); }

    getId() {
        return this.route.snapshot.paramMap.get('id');
    }

    getRole() {
        const id = this.getId();
        if (id) {
            this.roleService.getById(id).subscribe(
                (role: Role) => {
                    this.role = role;
                    this.form.patchValue(role);
                },
                error => {
                    console.log('error');
                    console.log(error);
                    // this.router.navigate(['**'])
                },
                () => {
                    console.log('completed');
                    // 'onCompleted' callback.
                    // No errors, route to new page here
                }

            );
        }
    }

    goBack(): void {
        this.location.back();
    }

}
