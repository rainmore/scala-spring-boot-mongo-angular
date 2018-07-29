import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Role } from '../../_models/users/role';

@Injectable()
export class RoleService {
    constructor(private http: HttpClient) { }

    private baseUrl = `${config.apiUrl}/api/core/users/roles`;
    private emptyUrl = `${this.baseUrl}/empty`;

    private buildItemUrl(id: string) {
        return `${this.baseUrl}/` + id;
    }

    getAll() {
        return this.http.get<Role[]>(this.baseUrl);
    }

    getById(id: string) {
        return this.http.get(this.buildItemUrl(id));
    }

    empty() {
        return this.http.get(this.emptyUrl);
    }

    save(role: Role) {
        return this.http.post(this.baseUrl, role);
    }

    update(role: Role) {
        return this.http.put(this.buildItemUrl(role.id), role);
    }

    delete(role: Role) {
        return this.http.delete(this.buildItemUrl(role.id));
    }
}