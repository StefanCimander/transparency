import { Component, Input } from '@angular/core';

import { HierarchyElement } from '../../models';

@Component({
  selector: 'app-distribution-preview',
  templateUrl: './distribution-preview.template.html',
  styleUrls: ['./distribution-preview.style.css']
})
export class DistributionPreviewComponent {

  @Input() dependencyHierarchy: HierarchyElement = { id: "0", name: 'Root Package', children: [], dependencies: [] };

}
