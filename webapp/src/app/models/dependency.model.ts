export class Dependency {
  constructor(
    public id: number,
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
  id: number;
  name: string;
  type: string;
}
