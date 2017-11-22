const API_BASE_URL = '/api';

export const API_ROUTES = {
  appSettings: {
    all: `${API_BASE_URL}/app-settings`,
  },
  features: {
    all: `${API_BASE_URL}/features`,
    logicallyDependent: `${API_BASE_URL}/features/logically-depending`,
    withId: `${API_BASE_URL}/features/:name?includeLogicalDependencies=false`,
  },
  packages: {
    all: `${API_BASE_URL}/packages`,
    withId: `${API_BASE_URL}/packages/:packageId`,
    hierarchy: `${API_BASE_URL}/packages/hierarchy?dependencies=:dependenciesRequest`
  },
  dependencies: {
    remove: `${API_BASE_URL}/dependencies`,
    analyse: `${API_BASE_URL}/dependencies/analyse`,
    details: `${API_BASE_URL}/dependencies/details`,
    statistics: `${API_BASE_URL}/dependencies/statistics`
  }
};
