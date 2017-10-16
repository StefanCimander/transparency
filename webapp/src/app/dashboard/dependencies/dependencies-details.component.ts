import { Component, OnInit } from '@angular/core';

import { HierarchyElement } from '../../models/';
import { PackageService } from '../../services/';

@Component({
    selector: 'app-dependencies-details',
    templateUrl: 'dependencies-details.template.html',
    styleUrls: ['dependencies-details.style.css'],
})
export class DependenciesDetailsComponent implements OnInit{

    public dependencyHierarchy: HierarchyElement = { id: 0, name: 'Root Package', children: [], dependencies: [] };

    constructor(private packageService: PackageService) { }

    ngOnInit() {
        this.packageService.getHierarchy().subscribe(rootPackage =>
            this.dependencyHierarchy = HierarchyElement.fromPackage(rootPackage)
        );
    }
}
