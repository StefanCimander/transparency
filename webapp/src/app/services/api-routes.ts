const API_BASE_URL = '/api';

export const API_ROUTES = {
  features: {
    all: `${API_BASE_URL}/features`,
    withId: `${API_BASE_URL}/features/:name?includeLogicalDependencies=false`,
  },
  packages: {
    all: `${API_BASE_URL}/packages`,
    withId: `${API_BASE_URL}/packages/:packageId`,
    hierarchy: `${API_BASE_URL}/packages/hierarchy`
  },
};
