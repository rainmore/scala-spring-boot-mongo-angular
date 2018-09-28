import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';

import { Page } from '../../_models/';
import { Role } from '../../_models/users';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable()
export class RoleService {
    constructor(private http: HttpClient) { }

    private baseUrl = `${config.apiUrl}/api/core/users/roles`;

    private buildItemUrl(id: string) {
        return `${this.baseUrl}/${id}`;
    }

    getAll() {
        return this.http.get<Page<Role>>(this.baseUrl);
    }

    getById(id: string) {
        return this.http.get(this.buildItemUrl(id));
    }

    save(role: Role) {
        return this.http.post(this.baseUrl, role).pipe(catchError(this.handleError));
    }

    update(role: Role) {
        return this.http.put(this.buildItemUrl(role.id), role);
    }

    delete(role: Role) {
        return this.http.delete(this.buildItemUrl(role.id));
    }

    private handleError(error: HttpErrorResponse) {
        if (error.error instanceof ErrorEvent) {
            // A client-side or network error occurred. Handle it accordingly.
            console.error('An error occurred:', error.error.message);
        } else {
            // The backend returned an unsuccessful response code.
            // The response body may contain clues as to what went wrong,
            console.error(
                `Backend returned code ${error.status}, ` +
                `body was: ${error.error}`);
        }
        // return an observable with a user-facing error message
        return throwError(
            'Something bad happened; please try again later.');
    };
}
