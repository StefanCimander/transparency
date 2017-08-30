import { Dependency, ApiDependency} from './dependency.model';

export class Feature {
  constructor (
    public id: number,
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
  id: number;
  name: string;
  dependencies?: ApiDependency[];
}
