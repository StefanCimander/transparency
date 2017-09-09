export class AppSetting {
  constructor(
    public name: string,
    public value: string,
  ) { }

  public static fromAPI(apiAppSetting: ApiAppSetting): AppSetting {
    return new AppSetting(
      apiAppSetting.name,
      apiAppSetting.value,
    )
  }
}

export interface ApiAppSetting {
  name: string;
  value: string;
}
