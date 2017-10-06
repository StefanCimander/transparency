import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';

import { MaterialModule } from './material.module';

import { AppComponent } from './app.component';
import { AppRoutes } from './app.routes';

import { DashboardComponent, DependenciesPreviewComponent, StatisticsComponent, TreemapPreviewComponent } from './dashboard';
import { DependenciesComponent } from './dependencies/';
import { EdgeBundlesComponent, TreemapComponent } from './visualizations';

import { AppSettingService, DependencyService, PackageService } from './services';

@NgModule({
  declarations: [
    AppComponent,
    EdgeBundlesComponent,
    DashboardComponent,
    DependenciesComponent,
    DependenciesPreviewComponent,
    StatisticsComponent,
    TreemapComponent,
    TreemapPreviewComponent
  ],
  imports: [
    BrowserAnimationsModule,
    BrowserModule,
    HttpModule,
    MaterialModule,
    RouterModule.forRoot(AppRoutes)
  ],
  providers: [
    AppSettingService,
    DependencyService,
    PackageService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
