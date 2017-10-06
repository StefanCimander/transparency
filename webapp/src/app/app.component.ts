import { Component, OnInit } from '@angular/core';

import { AppSettingService, DependencyService } from "./services";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

    public implicitDependenciesStatus: String = "deleted";

    constructor(
        private appSettingService: AppSettingService,
        private dependencyService: DependencyService
    ) { }

    ngOnInit() {
        this.appSettingService.getAll().subscribe(appSettings =>
        this.implicitDependenciesStatus = appSettings[0].value
        );
    }

    public findDependenciesButtonClicked() {
        this.dependencyService.analyse().subscribe();
    }

    public clearDependenciesButtonClicked() {
        this.dependencyService.remove().subscribe();
    }
}
