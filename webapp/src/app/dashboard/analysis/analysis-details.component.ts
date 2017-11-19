import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';

import { Feature } from '../../models';

@Component({
  selector: 'app-analysis-details',
  templateUrl: 'analysis-details.template.html',
  styleUrls: ['analysis-details.style.css']
})
export class AnalysisDetailsComponent implements OnInit {

  private source: Feature;
  private target: Feature;

  constructor(
    private route: ActivatedRoute,
  ) { }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      console.log(params);
      // TODO: Make service request with the two feature ids.
    });
  }

}
