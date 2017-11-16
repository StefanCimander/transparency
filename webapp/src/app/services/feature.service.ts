
import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';

import { Feature } from '../models';
import { API_ROUTES } from './api-routes';

@Injectable()
export class FeatureService {

  constructor(private http: Http) { }

  /**
   * Gets all features that have logical dependencies.
   *
   * @return {Observable<Feature[]>}
   */
  public getLogicallyDepending(): Observable<Feature[]> {
    return this.http.get(API_ROUTES.features.logicallyDependent)
      .map(res => res.json())
      .map(features => features.map(Feature.fromAPI));
  }
}
