export class Dependency {
  constructor(
    public id: string,
    public name: string,
    public type: string,
  ) { }

  public static fromAPI (apiDependency: ApiDependency): Dependency {
    return new Dependency(
      apiDependency.id,
      apiDependency.name,
      apiDependency.type,
    );
  }
}

export interface ApiDependency {
  id: string;
  name: string;
  type: string;
}
