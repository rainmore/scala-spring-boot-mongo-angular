import { Injectable, Injector } from '@angular/core';
import { RestService }          from 'app/services/rest.service';
import { Region }               from "app/domains/regions/region";

@Injectable()
export class RegionsService extends RestService<Region> {

    constructor(injector: Injector) {
        super(Region, Region.collection, injector);
    }
}