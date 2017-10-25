import { Component, OnInit } from '@angular/core';

import { DependenciesRequest, PackageService } from "../../services";
import { HierarchyElement } from "../../models";

@Component({
  selector: 'app-distribution-details',
  templateUrl: './distribution-details.template.html',
  styleUrls: ['./distribution-details.style.css']
})
export class DistributionDetailsComponent implements OnInit {

  public dependencyHierarchy: HierarchyElement = { id: 0, name: 'Root Package', children: [], dependencies: [] };

  constructor(private packageService: PackageService) { }

  ngOnInit() {
    this.packageService
      .getHierarchy(DependenciesRequest.ALL_DEPENDENCIES)
      .subscribe( rootPackage => this.dependencyHierarchy = HierarchyElement.fromPackage(rootPackage))
  }
}
