export class Dependency {
  constructor(
    public id: string,
    public name: string,
    public type: string,
  ) { }

  public static fromAPI (apiRepresentation: ApiDependency): Dependency {
    return new Dependency(
      apiRepresentation.id,
      apiRepresentation.name,
      apiRepresentation.type,
    );
  }
}

export interface ApiDependency {
  id: string;
  name: string;
  type: string;
}
