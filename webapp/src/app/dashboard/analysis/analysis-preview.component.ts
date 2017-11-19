import { Component, OnInit } from '@angular/core';

import { Feature } from '../../models';
import { FeatureService } from '../../services';
import { Router } from '@angular/router';

@Component({
  selector: 'app-analysis-preview',
  templateUrl: 'analysis-preview.template.html',
  styleUrls: ['analysis-preview.style.css']
})
export class AnalysisPreviewComponent implements OnInit {

  public logicallyDepending: Feature[] = [];

  public source: Feature;
  public target: Feature;

  constructor(
    private featureService: FeatureService,
    private router: Router,
  ) { }

  /**
   * @memberOf OnInit
   */
  public ngOnInit() {
    this.featureService.getLogicallyDepending()
      .subscribe(features => this.logicallyDepending = features);
  }

  public navigateToDetails() {
    this.router.navigate(['/analysis'], { queryParams: {
      sourceId: this.source.id,
      targetId: this.target.id,
    }});
  }
}
