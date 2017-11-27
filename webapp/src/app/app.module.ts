import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { APP_ROUTES } from './app.routes';
import { MaterialModule } from './material.module';

import {
  AnalysisDetailsComponent,
  AnalysisPreviewComponent,
  DashboardComponent,
  DependenciesDetailsComponent,
  DependenciesPreviewComponent,
  DistributionDetailsComponent,
  StatisticsComponent,
  DistributionPreviewComponent,
} from './dashboard';

import {
  EdgeBundlesComponent,
  MappingGraphComponent,
  TreemapComponent
} from './visualizations';

import {
  AppSettingService,
  DependencyService,
  FeatureService,
  PackageService,
} from './services';

@NgModule({
  declarations: [
    AnalysisDetailsComponent,
    AnalysisPreviewComponent,
    AppComponent,
    EdgeBundlesComponent,
    DashboardComponent,
    DependenciesDetailsComponent,
    DependenciesPreviewComponent,
    DistributionDetailsComponent,
    MappingGraphComponent,
    StatisticsComponent,
    TreemapComponent,
    DistributionPreviewComponent
  ],
  imports: [
    BrowserAnimationsModule,
    BrowserModule,
    HttpModule,
    MaterialModule,
    RouterModule.forRoot(APP_ROUTES)
  ],
  providers: [
    AppSettingService,
    DependencyService,
    FeatureService,
    PackageService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
