import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({templateUrl: 'page-not-found.component.html'})
export class PageNotFoundComponent implements OnInit {

    constructor(
        private route: ActivatedRoute,
        private router: Router) {}

    ngOnInit() {

    }

}
