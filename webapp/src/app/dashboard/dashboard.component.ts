import { Component, OnInit } from '@angular/core';

import { HierarchyElement } from '../models/';
import { PackageService, DependenciesRequest } from '../services';

@Component({
    selector: 'app-dashboard',
    templateUrl: './dashboard.template.html',
    styleUrls: ['./dashboard.style.css'],
})
export class DashboardComponent implements OnInit {

    public dependencyHierarchy: HierarchyElement = { id: 0, name: 'Root Package', children: [], dependencies: [] };

    constructor(private packageService: PackageService) { }

    ngOnInit() {
        this.packageService.getHierarchy(DependenciesRequest.ALL_DEPENDENCIES).subscribe( rootPackage =>
            this.dependencyHierarchy = HierarchyElement.fromPackage(rootPackage)
        );
    }
}
