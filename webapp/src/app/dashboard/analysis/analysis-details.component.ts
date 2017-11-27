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

  public details: DependencyDetails;
  public features: string[];
  public receivingFunctions: string[];
  public sendingFunctions: string[];

  constructor(private route: ActivatedRoute, private dependencyService: DependencyService) { }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.dependencyService.getDetails(params.sourceId, params.targetId).subscribe(details => {
        this.details = details;
        this.features = this.findFeatures();
        this.receivingFunctions = this.findDistinctReceivingFunctions();
        this.sendingFunctions = this.findDistinctSendingFunctions();
      });
    });
  }

  private findFeatures(): string[] {
    return [this.details.sourceFeature, this.details.targetFeature];
  }

  private findDistinctReceivingFunctions(): string[] {
    let distinctFunctions: string[] = [];
    this.details.signals.forEach( signal => {
      if (distinctFunctions.indexOf(signal.receivingFunction) < 0) {
        distinctFunctions.push(signal.receivingFunction)
      }
    });
    return distinctFunctions;
  }

  private findDistinctSendingFunctions(): string[] {
    let distinctFunctions: string[] = [];
    this.details.signals.forEach( signal => {
      if (distinctFunctions.indexOf(signal.sendingFunction) < 0) {
        distinctFunctions.push(signal.sendingFunction)
      }
    });
    return distinctFunctions;
  }
}
