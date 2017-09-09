import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { MdButtonModule, MdCardModule, MdIconModule, MdMenuModule, MdToolbarModule } from '@angular/material';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { AppRoutes } from './app.routes';

import { DashboardComponent, DependenciesPreviewComponent, TreemapPreviewComponent } from './dashboard';
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
    TreemapComponent,
    TreemapPreviewComponent
  ],
  imports: [
    BrowserAnimationsModule,
    BrowserModule,
    HttpModule,
    MdButtonModule,
    MdCardModule,
    MdIconModule,
    MdMenuModule,
    MdToolbarModule,
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
