import { Component, Input } from '@angular/core';

import { HierarchyElement } from "../../models";

@Component({
  selector: 'app-dependencies-preview',
  templateUrl: './dependencies-preview.template.html',
  styleUrls: ['./dependencies-preview.style.css']
})
export class DependenciesPreviewComponent {

  @Input() dependencyHierarchy: HierarchyElement = { id: "0", name: 'Root Package', children: [], dependencies: [] };
}
