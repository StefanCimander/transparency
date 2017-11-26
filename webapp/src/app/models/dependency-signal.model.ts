export class DependencySignal {
  constructor(
    public receivingFunction: string,
    public sendingFunction: string,
    public signalName: string,
  ) { }

  public static fromApi(apiDependencySignal: ApiDependencySignal): DependencySignal {
    return new DependencySignal(
      apiDependencySignal.receivingFunction,
      apiDependencySignal.sendingFunction,
      apiDependencySignal.signalName,
    );
  }
}

export interface ApiDependencySignal {
  receivingFunction: string;
  sendingFunction: string;
  signalName: string
}
