import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';

import { Package } from '../models';
import { API_ROUTES } from './api-routes';

@Injectable()
export class PackageService {
  constructor(private http: Http) { }

  public getAll(): Observable<Package[]> {
    return this.http.get(API_ROUTES.packages.all)
      .map(res => res.json())
      .map(packages => packages.map(Package.fromAPI));
  }

  public getWithId(id: number): Observable<Package> {
    return this.http.get(API_ROUTES.packages.withId.replace(':id', id.toString()))
      .map(res => res.json())
      .map(Package.fromAPI);
  }

  public getHierarchy(): Observable<Package> {
    return this.http.get(API_ROUTES.packages.hierarchy)
      .map(res => res.json())
      .map(Package.fromAPI);
  }
}
