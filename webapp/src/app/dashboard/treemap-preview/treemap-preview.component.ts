import { Component, OnInit } from '@angular/core';

import { HierarchyElement } from '../../models';
import { PackageService } from '../../services';

@Component({
  selector: 'app-treemap-preview',
  templateUrl: './treemap-preview.component.html',
  styleUrls: ['./treemap-preview.component.css']
})
export class TreemapPreviewComponent implements OnInit {

  public dependencyHierarchy: HierarchyElement = { id: 0, name: 'Root Package', children: [], dependencies: [] };

  constructor(private packageService: PackageService) { }

  public ngOnInit() {
    this.packageService.getHierarchy().subscribe(rootPackage =>
      this.dependencyHierarchy = HierarchyElement.fromPackage(rootPackage)
    );
  }

}
