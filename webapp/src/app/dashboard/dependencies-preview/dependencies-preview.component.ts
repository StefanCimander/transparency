import { Component, Input } from '@angular/core';

import { HierarchyElement } from "../../models";

@Component({
  selector: 'app-dependencies-preview',
  templateUrl: './dependencies-preview.component.html',
  styleUrls: ['./dependencies-preview.component.css']
})
export class DependenciesPreviewComponent {

  @Input() dependencyHierarchy: HierarchyElement = { id: 0, name: 'Root Package', children: [], dependencies: [] };
}
