import { Injectable, Injector } from '@angular/core';
import { RestService }          from 'app/services/rest.service';
import { Cluster }              from "app/domains/clusters/cluster";

@Injectable()
export class ClusterService extends RestService<Cluster> {

    constructor(injector: Injector) {
        super(Cluster, Cluster.collection, injector);
    }
}