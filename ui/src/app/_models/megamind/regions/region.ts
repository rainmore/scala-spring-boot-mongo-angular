import { Resource } from 'angular4-hal';

/**
 * Model to represent an AWS region.
 */
export class Region extends Resource {
    static collection: string = 'regions';

    id: string;
    name: string;
    createdAt: Date;
    displayName: string;
}
