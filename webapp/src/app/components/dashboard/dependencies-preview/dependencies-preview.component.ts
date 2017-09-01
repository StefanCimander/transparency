import { Component, OnInit } from '@angular/core';

import { HierarchyElement } from "../../../models";
import { PackageService } from "../../../services";

@Component({
  selector: 'app-dependencies-preview',
  templateUrl: './dependencies-preview.component.html',
  styleUrls: ['./dependencies-preview.component.css']
})
export class DependenciesPreviewComponent implements OnInit {

  public dependencyHierarchy: HierarchyElement = { id: 0, name: 'Root Package', children: [], dependencies: [] };

  constructor(private packageService: PackageService) { }

  public ngOnInit() {
    this.packageService.getHierarchy().subscribe(rootPackage =>
      this.dependencyHierarchy = HierarchyElement.fromPackage(rootPackage)
    );
  }
}
