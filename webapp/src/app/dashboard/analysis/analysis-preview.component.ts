import { Component, OnInit } from '@angular/core';

import { Feature } from '../../models';
import { FeatureService } from '../../services';

@Component({
  selector: 'app-analysis-preview',
  templateUrl: 'analysis-preview.template.html',
  styleUrls: ['analysis-preview.style.css']
})
export class AnalysisPreviewComponent implements OnInit {

  public logicallyDepending: Feature[] = [];

  public source: Feature;
  public target: Feature;

  constructor(private featureService: FeatureService) { }

  /**
   * @memberOf OnInit
   */
  public ngOnInit() {
    this.featureService.getLogicallyDepending()
      .subscribe(features => this.logicallyDepending = features);
  }
}
