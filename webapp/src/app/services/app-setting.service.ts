import { Injectable } from "@angular/core";
import { Http, Response } from "@angular/http";

import { Observable } from "rxjs/Observable";

import { API_ROUTES } from "./api-routes";

import { AppSetting } from "../models/";

@Injectable()
export class AppSettingService {
  constructor(private http: Http) { }

  public getAll(): Observable<Response> {
    return this.http.get(API_ROUTES.appSettings.all)
      .map(res => res.json())
      .map(packages => packages.map(AppSetting.fromAPI));
  }
}
