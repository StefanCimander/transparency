import { Dependency, ApiDependency} from './dependency.model';

export class Feature {
  constructor (
    public id: string,
    public name: string,
    public dependencies: Dependency[]
  ) { }

  public static fromAPI (apiRepresentation: ApiFeature): Feature {
    return new Feature(
      apiRepresentation.id,
      apiRepresentation.name,
      apiRepresentation.dependencies.map(Dependency.fromAPI)
    );
  }
}

export interface ApiFeature {
  id: string;
  name: string;
  dependencies?: ApiDependency[];
}
