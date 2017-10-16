import { Component, OnInit } from '@angular/core';

import { DependencyService } from '../../services';
import { DependencyStatistic } from '../../models';

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.template.html',
  styleUrls: ['./statistics.style.css'],
})
export class StatisticsComponent implements OnInit{

    public dependencyStatistic: DependencyStatistic = { numberOfFeatureLinks: 0, numberOfImplicitDependencies: 0 };

    constructor(private dependencyService: DependencyService) { }

    ngOnInit() {
        this.dependencyService.getStatistics().subscribe(dependencyStatistic =>
            this.dependencyStatistic = dependencyStatistic
        );
    }

}
