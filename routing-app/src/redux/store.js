import { createStore } from 'redux';
import serviceReducer from './serviceReducer';

const store = createStore(serviceReducer);

export default store;