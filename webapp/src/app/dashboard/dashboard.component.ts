import { Component, OnInit } from '@angular/core';

import { HierarchyElement } from '../models/';
import { PackageService } from '../services';

@Component({
    selector: 'app-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {

    public dependencyHierarchy: HierarchyElement = { id: 0, name: 'Root Package', children: [], dependencies: [] };

    constructor(private packageService: PackageService) { }

    ngOnInit() {
        this.packageService.getHierarchy().subscribe( rootPackage =>
        this.dependencyHierarchy = HierarchyElement.fromPackage(rootPackage)
        );
    }
}
