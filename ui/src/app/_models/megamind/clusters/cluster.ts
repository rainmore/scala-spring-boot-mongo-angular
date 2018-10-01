import { Resource } from 'angular4-hal';
import { Region } from '../regions/region';

/**
 * Model to represent a cluster.
 */
export class Cluster extends Resource {
    static collection: string = 'clusters';

    /**
     * Resource link keys
     */
    static links = {
        region: 'region'
    };

    region: Region;
    id: string;
    name: string;
    createdAt: Date;
}
