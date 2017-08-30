import { Component, OnInit } from '@angular/core';

import { PackageService } from './services';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Transparency';

  constructor(private packageService: PackageService) { }

  public ngOnInit() {
    this.packageService.getAll().subscribe(packages => {
      packages.forEach( pack => console.log(pack) );
    });
  }
}
