import { Component, Input } from '@angular/core';

import { HierarchyElement } from '../../models';

@Component({
  selector: 'app-treemap-preview',
  templateUrl: './treemap-preview.component.html',
  styleUrls: ['./treemap-preview.component.css']
})
export class TreemapPreviewComponent {

  @Input() dependencyHierarchy: HierarchyElement = { id: 0, name: 'Root Package', children: [], dependencies: [] };

}
