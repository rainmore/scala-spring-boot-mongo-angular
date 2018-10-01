import {Injectable} from '@angular/core';
import {ExternalConfigurationHandlerInterface, ExternalConfiguration} from 'angular4-hal';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class BaseRepository implements ExternalConfigurationHandlerInterface {

    getProxyUri(): string {
        return 'http://proxy.url/api/';
    }

    getRootUri(): string {
        return 'https://serviceip.tomcat:8080/APP/';
    }

    getHttp(): HttpClient {
        return this.http;
    }

    constructor(private http: HttpClient) {
    }

    getExternalConfiguration(): ExternalConfiguration {
        return null;
    }

    setExternalConfiguration(externalConfiguration: ExternalConfiguration) {
    }

    deserialize(): any {
    }

    serialize(): any {
    }
}
