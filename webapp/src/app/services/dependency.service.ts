import { Injectable } from "@angular/core";
import { Http, Response } from "@angular/http";

import { Observable } from "rxjs/Observable";

import { API_ROUTES } from "./api-routes";

@Injectable()
export class DependencyService {
  constructor(private http: Http) { }

  public analyse(): Observable<Response> {
    return this.http.put(API_ROUTES.dependencies.analyse, '')
  }

  public remove(): Observable<Response> {
    return this.http.delete(API_ROUTES.dependencies.remove);
  }
}
