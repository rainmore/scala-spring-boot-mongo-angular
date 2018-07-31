class Sort {
    sorted: boolean;
    unsorted: boolean;
}

class Offset {
    pageSize: number;
    pageNumber: number;
    paged: boolean;
    unpaged: boolean
}

class TotalPages {

}

export class Pageable {
    sort?: Sort;
    offset?: Offset;
}

export class Page<T> {
    content: T[];
    pageable?: Pageable;
    totalElements: number;
    last: boolean;
    size: number;
    number: number;
    first: boolean;
    numberOfElements: number;
    sort?: Sort;
}


