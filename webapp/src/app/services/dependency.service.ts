import { Injectable } from "@angular/core";
import { Http, Response } from "@angular/http";

import { Observable } from "rxjs/Observable";

import { API_ROUTES } from "./api-routes";
import { DependencyStatistic, DependencyDetails } from '../models';

@Injectable()
export class DependencyService {

  constructor(private http: Http) { }

  public analyse(): Observable<Response> {
    return this.http.put(API_ROUTES.dependencies.analyse, '')
  }

  public getDetails(sourceId: string, targetId: string): Observable<DependencyDetails> {
    return this.http.get(API_ROUTES.dependencies.details
      .replace(':sourceId', sourceId)
      .replace(':targetId', targetId))
      .map(res => res.json())
      .map(DependencyDetails.fromApi)
  }

  public getStatistics(): Observable<DependencyStatistic> {
    return this.http.get(API_ROUTES.dependencies.statistics)
      .map(res => res.json())
      .map(DependencyStatistic.fromApi);
  }

  public remove(): Observable<Response> {
    return this.http.delete(API_ROUTES.dependencies.remove);
  }
}
