const initialState = {
  currentService: null, // 'loan' or 'deposit'
};

const serviceReducer = (state = initialState, action) => {
  switch (action.type) {
    case 'SET_SERVICE':
      return {
        ...state,
        currentService: action.payload,
      };
    default:
      return state;
  }
};

export default serviceReducer;