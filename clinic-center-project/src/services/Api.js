import axios from 'axios';

let AUTH_TOKEN = "Bearer " + localStorage.getItem("User-token");
let config = {
	baseURL: "http://localhost:8080/api",
	headers: {
		'Content-Type': 'application/json'
	}
};

const httpClient = axios.create(config);
httpClient.defaults.headers.common['Authorization'] = AUTH_TOKEN;

// Token interceptor
// const authInterceptor = config => {
//     return config;
// }

// const loggerInterceptor = config => {
//     return config;
// }

/** Adding the response interceptors */
httpClient.interceptors.response.use(
	response => {
		/** TODO: Add any response interceptors */
		return response;
	},
	error => {
		/** TODO: Do something with response error */
		return Promise.reject(error);
	}
);

export { httpClient };