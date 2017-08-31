import { Feature, ApiFeature } from './feature.model';

export class Package {
  constructor(
    public id: number,
    public name: string,
    public children: Package[],
    public features: Feature[],
    public parentPackageId?: number,
  ) { }

  public static fromAPI (apiRepresentation: ApiPackage): Package {
    let children = apiRepresentation.childPackages != undefined ? apiRepresentation.childPackages.map(Package.fromAPI) : [];
    let features = apiRepresentation.features != undefined ? apiRepresentation.features.map(Feature.fromAPI) : [];
    return new Package(
      apiRepresentation.id,
      apiRepresentation.name,
      children,
      features,
      apiRepresentation.parentPackageId,
    );
  }
}

export interface ApiPackage {
  id: number;
  name: string;
  childPackages?: ApiPackage[];
  features?: ApiFeature[];
  parentPackageId?: number;
}
