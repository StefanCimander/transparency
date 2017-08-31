import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';

import { PackageService } from './services';
import { EdgeBundlesComponent } from './components';

@NgModule({
  declarations: [
    AppComponent,
    EdgeBundlesComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
  ],
  providers: [PackageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
