export class DependencyStatistic {
    constructor(
        public numberOfFeatureLinks: number,
        public numberOfImplicitDependencies: number,
    ) { }

    public static fromApi(apiDependencyStatistic: ApiDependencyStatistic): DependencyStatistic {
        return new DependencyStatistic(
            apiDependencyStatistic.featureLinks,
            apiDependencyStatistic.implicitFeatureDependencies
        );
    }
}

export interface ApiDependencyStatistic {
    featureLinks: number;
    implicitFeatureDependencies: number;
}
