import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';

import { DependencyDetails } from '../../models';
import { DependencyService } from '../../services';

@Component({
  selector: 'app-analysis-details',
  templateUrl: 'analysis-details.template.html',
  styleUrls: ['analysis-details.style.css']
})
export class AnalysisDetailsComponent implements OnInit {

  private details: DependencyDetails;

  constructor(
    private route: ActivatedRoute,
    private dependencyService: DependencyService,
  ) { }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.dependencyService.getDetails(params.sourceId, params.targetId)
        .subscribe(details => {
          console.log(details);
          this.details = details
        });
    });
  }

}
