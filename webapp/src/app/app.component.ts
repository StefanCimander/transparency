import { Component, OnInit } from '@angular/core';

import { PackageService } from './services';
import {HierarchyElement} from "./models/hierarchy-element.model";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  public dependencyHierarchy: HierarchyElement = { id: 0, name: 'All Features', children: [], dependencies: [] };

  constructor(private packageService: PackageService) { }

  public ngOnInit (): void {
    this.packageService.getHierarchy().subscribe(pack => {
      this.dependencyHierarchy = HierarchyElement.fromPackage(pack);
    });
  }
}
