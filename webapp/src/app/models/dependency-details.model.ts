import { ApiDependencySignal, DependencySignal } from './dependency-signal.model';

export class DependencyDetails {
  constructor(
    public sourceFeature: string,
    public targetFeature: string,
    public signals: DependencySignal[]
  ) { }

  public static fromApi(apiDependencyDetails: ApiDependencyDetails): DependencyDetails {
    return new DependencyDetails(
      apiDependencyDetails.sourceFeature,
      apiDependencyDetails.targetFeature,
      apiDependencyDetails.signals.map(DependencySignal.fromApi)
    )
  }
}

export interface ApiDependencyDetails {
  sourceFeature: string;
  targetFeature: string;
  signals: ApiDependencySignal[];
}
