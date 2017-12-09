import { Component, OnInit } from '@angular/core';
import { MatSliderChange } from '@angular/material';

import { DependenciesRequest, PackageService } from "../../services";
import { HierarchyElement } from "../../models";

@Component({
  selector: 'app-distribution-details',
  templateUrl: './distribution-details.template.html',
  styleUrls: ['./distribution-details.style.css']
})
export class DistributionDetailsComponent implements OnInit {

  public dependencyHierarchy: HierarchyElement = { id: "0", name: 'Root Package', children: [], dependencies: [] };
  public direction: string = 'Incoming';
  public threshold: number = 70;

  constructor(private packageService: PackageService) { }

  ngOnInit() {
    this.packageService
      .getHierarchy(DependenciesRequest.ONLY_IMPLICIT)
      .subscribe( rootPackage => this.dependencyHierarchy = HierarchyElement.fromPackage(rootPackage))
  }

  public dependenciesDirectionChanged(direction: string) {
    this.direction = direction;
  }

  public onThresholdChanged(event: MatSliderChange) {
    this.threshold = event.value
  }
}
