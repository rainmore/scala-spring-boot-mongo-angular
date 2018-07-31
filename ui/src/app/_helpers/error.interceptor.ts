import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import {UNAUTHORIZED, BAD_REQUEST, FORBIDDEN, NOT_FOUND} from "http-status-codes";

import { AuthenticationService } from '../_services';
import { Router } from '@angular/router';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
    constructor(
        private router: Router,
        private authenticationService: AuthenticationService
    ) {}

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(request).pipe(catchError(err => {
            let httpErrorCode = err.httpErrorCode;
            switch (httpErrorCode) {
                case UNAUTHORIZED:
                    // auto logout if 401 response returned from api
                    this.authenticationService.logout();
                    location.reload(true);
                    break;
                case NOT_FOUND:
                    this.router.navigateByUrl("**");
                    break;
            }

            const error = err.error.message || err.statusText;
            return throwError(error);
        }))
    }
}
