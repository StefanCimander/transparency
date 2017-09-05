import { Feature, Dependency, Package } from './';

export class HierarchyElement {
  constructor(
    public id: number,
    public name: string,
    public children: HierarchyElement[],
    public dependencies: HierarchyElement[],
  ) { }

  public static fromPackage(pack: Package): HierarchyElement {
    let children = pack.children != undefined ? pack.children.map(HierarchyElement.fromPackage) : [];
    let features = pack.features != undefined ? pack.features.map(HierarchyElement.fromFeature) : [];
    return new HierarchyElement(pack.id, pack.name, children.concat(features), []);
  }

  private static fromFeature(feature: Feature): HierarchyElement {
    let dependencies = feature.dependencies != undefined ? feature.dependencies.map(HierarchyElement.fromDependency) : [];
    return new HierarchyElement(feature.id, feature.name, [], dependencies);
  }

  private static fromDependency(dependency: Dependency): HierarchyElement {
    return new HierarchyElement(dependency.id, dependency.name, [], []);
  }
}
