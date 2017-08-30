import { Feature, ApiFeature } from './feature.model';

export class Package {
  constructor(
    public id: number,
    public name: string,
    public parentPackageId?: number,
  ) { }

  public static fromAPI (apiRepresentation: ApiPackage): Package {
    return new Package(
      apiRepresentation.id,
      apiRepresentation.name,
      apiRepresentation.parentPackageId,
    );
  }
}

export interface ApiPackage {
  id: number;
  name: string;
  parentPackageId?: number;
}
