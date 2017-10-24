import { Component, OnInit } from '@angular/core';
import { MatSliderChange } from "@angular/material";

import { HierarchyElement } from '../../models/';
import { PackageService, DependenciesRequest } from '../../services/';

@Component({
  selector: 'app-dependencies-details',
  templateUrl: 'dependencies-details.template.html',
  styleUrls: ['dependencies-details.style.css'],
})
export class DependenciesDetailsComponent implements OnInit {
  private selectedDependenciesRequest: DependenciesRequest = DependenciesRequest.ALL_DEPENDENCIES;

  public dependencyHierarchy: HierarchyElement = { id: 0, name: 'Root Package', children: [], dependencies: [] };
  public beta = 0.7;

  constructor(private packageService: PackageService) { }

  ngOnInit() {
    this.loadHierarchyWithDependenciesRequest(this.selectedDependenciesRequest);
  }

  public dependenciesRequestChanged(dependenciesRequestName: String) {
    const request = this.dependenciesRequestForName(dependenciesRequestName)
    this.loadHierarchyWithDependenciesRequest(request)
  }

  public onBetaValueChange(event: MatSliderChange) {
    this.beta = event.value;
  }

  private loadHierarchyWithDependenciesRequest(dependenciesRequest: DependenciesRequest) {
    this.packageService.getHierarchy(dependenciesRequest).subscribe(rootPackage =>
      this.dependencyHierarchy = HierarchyElement.fromPackage(rootPackage)
    );
  }

  private dependenciesRequestForName(name: String): DependenciesRequest {
    switch (name) {
      case "All Dependencies": return DependenciesRequest.ALL_DEPENDENCIES;
      case "Only Explicit": return DependenciesRequest.ONLY_EXPLICIT;
      case "Only Implicit": return DependenciesRequest.ONLY_IMPLICIT;
      case "Conformal": return DependenciesRequest.CONFORMAL;
      case "Non Conformal": return DependenciesRequest.NON_CONFORMAL;
    }
  }
}
