import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { MdToolbarModule, MdCardModule, MdButtonModule } from '@angular/material';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { AppRoutes } from './app.routes';

import { PackageService } from './services';
import { EdgeBundlesComponent } from './components';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { DependenciesComponent } from './components/dependencies/dependencies.component';
import { DependenciesPreviewComponent } from './components/dashboard/dependencies-preview/dependencies-preview.component';

@NgModule({
  declarations: [
    AppComponent,
    EdgeBundlesComponent,
    DashboardComponent,
    DependenciesComponent,
    DependenciesPreviewComponent
  ],
  imports: [
    BrowserAnimationsModule,
    BrowserModule,
    HttpModule,
    MdToolbarModule,
    MdCardModule,
    MdButtonModule,
    RouterModule.forRoot(AppRoutes)
  ],
  providers: [PackageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
