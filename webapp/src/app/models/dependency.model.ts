export class Dependency {
  constructor(
    public id: number,
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
  id: number;
  name: string;
  type: string;
}
